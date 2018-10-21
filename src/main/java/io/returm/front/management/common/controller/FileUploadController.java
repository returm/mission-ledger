package io.returm.front.management.common.controller;

import io.returm.front.common.util.Config;
import io.returm.front.common.util.FilePathUtil;
import io.returm.front.constants.Constants;
import io.returm.front.management.common.entity.FileEntity;
import io.returm.front.management.common.service.FileService;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/common")
public class FileUploadController {

    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    /** File Service */
    @Autowired
    private FileService service;


    /**
     * File upload string.
     *
     * @param fileCd
     * @param attachFile
     * @param session the session
     * @return the string
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String imageUpload(@RequestParam(value = "fileCd", required = true) String fileCd,
                              @RequestParam(value = "upload", required = true) MultipartFile attachFile,
                              HttpSession session,
                              HttpServletRequest request) {

        JSONObject object = new JSONObject();
        object.put("result", false);

        if( !attachFile.isEmpty() ) {

            try {

                FilePathUtil.PATH_TYPE filePathType = FilePathUtil.PATH_TYPE.valueOf(fileCd);
                if(filePathType==null){
                    object.put("resultMsg", "fileCd를 확인해주세요.");
                    return object.toString();
                }

                //이미지 파일경우 이미지 파일 체크
                if(filePathType.toString().endsWith("_IMAGE")){
                    try{
                        BufferedImage bimg = ImageIO.read(attachFile.getInputStream());
                    }catch(IOException ioe){
                        object.put("resultMsg", "이미지 파일이 아닙니다.");
                        return object.toString();
                    }
                }


                FilePathUtil pathUtil = FilePathUtil.getInstance(filePathType);
                FileUtils.forceMkdir(new File(pathUtil.getFullPath()));
                String fileExt = attachFile.getOriginalFilename().substring(attachFile.getOriginalFilename().lastIndexOf("."));
                pathUtil.setFileName(filePathType.name() + "_" + DateTime.now().toString("HHmmssSSS") + fileExt);

                File file = new File(pathUtil.getFullPath());
                attachFile.transferTo(file);


                // data
                FileEntity fileEntity = new FileEntity();
                fileEntity.setFileSize(String.valueOf(attachFile.getSize()));
                fileEntity.setFileExt(fileExt);
                fileEntity.setFilePath(pathUtil.getDBPath());
                fileEntity.setHandleFileNm(pathUtil.getFileName());
                fileEntity.setOrgFileNm(attachFile.getOriginalFilename());
                fileEntity.setAltText(attachFile.getOriginalFilename());
                fileEntity.setDelYn("N");
                fileEntity.setLoginIDInSession("SYSTEM");
                service.insert(fileEntity);


                String contextPath = "";
                if(!StringUtils.equals("/", request.getContextPath())){
                    contextPath = request.getContextPath();
                }

                object.put("result", true);
                object.put("fileSq", fileEntity.getFileSq());
                object.put("orgFileNm", fileEntity.getOrgFileNm());
                object.put("handleFileNm", fileEntity.getHandleFileNm());
                object.put("fullUrlPath", FilePathUtil.getFullPath(filePathType, pathUtil.getDBPath()));
                object.put("fileCd", fileCd);
//                object.put("downloadUrl", FilePathUtil.getFullUrlPath(FilePathUtil.PATH_TYPE.MISSION_IMAGE, fileEntity.getFilePath()));

            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }

        }

        return object.toString();
    }
}
