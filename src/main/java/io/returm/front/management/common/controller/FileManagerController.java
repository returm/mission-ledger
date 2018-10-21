package io.returm.front.management.common.controller;

import io.returm.front.common.util.FilePathUtil;
import io.returm.front.management.common.entity.FileEntity;
import io.returm.front.management.common.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/file")
public class FileManagerController {
	
	/** Logger */
    private static final Logger logger = LoggerFactory.getLogger(FileManagerController.class);

    private final String PREFIX = "common/file/";

    /** 다운로드 버퍼 크기 */
    private static final int BUFFER_SIZE = 8192; // 8kb

    /** 문자 인코딩 */
    private static final String CHARSET = "utf-8";

	/** File Service */
    @Autowired
    private FileService service;

    
    /**
     * File upload string.
     *
     * @param fileCd
     * @return the string
     */
    @RequestMapping(value = "/download/{fileCd}/{handleFileNm:.+}", method = RequestMethod.GET)
    public void fileDownload(@PathVariable("fileCd") String fileCd,
    						@PathVariable("handleFileNm") String handleFileNm,
            				HttpServletRequest request,
            				HttpServletResponse response) throws ServletException, IOException {

    	FileEntity entity = new FileEntity();
    	entity.setHandleFileNm(handleFileNm);
    	entity = service.get(entity);

    	FilePathUtil.PATH_TYPE filePathType = FilePathUtil.PATH_TYPE.valueOf(fileCd);
    	String filePath = FilePathUtil.getFullPath(filePathType, entity.getFilePath());



    	String mimetype = request.getSession().getServletContext().getMimeType(handleFileNm);

        InputStream is = null;

        try {
          is = new FileInputStream(filePath);
          download(request, response, is, entity.getOrgFileNm(), Long.parseLong(entity.getFileSize()), mimetype);
        }catch (Exception e) {
//			logger.error(e.getMessage(),e);
        } finally {
          try {
            is.close();
          } catch (Exception ex) {
          }
        }
    }
    
    
	/**
	 * 해당 입력 스트림으로부터 오는 데이터를 다운로드 한다.
	 *
	 * @param request
	 * @param response
	 * @param is
	 *            입력 스트림
	 * @param filename
	 *            파일 이름
	 * @param filesize
	 *            파일 크기
	 * @param mimetype
	 *            MIME 타입 지정
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response, InputStream is,
			String filename, long filesize, String mimetype) throws ServletException, IOException {
		String mime = mimetype;

		if (mimetype == null || mimetype.length() == 0) {
			mime = "application/octet-stream;";
		}

		byte[] buffer = new byte[BUFFER_SIZE];

		response.setContentType(mime + "; charset=" + CHARSET);

		// 아래 부분에서 euc-kr 을 utf-8 로 바꾸거나 URLEncoding을 안하거나 등의 테스트를
		// 해서 한글이 정상적으로 다운로드 되는 것으로 지정한다.
		String userAgent = request.getHeader("User-Agent");

		// attachment; 가 붙으면 IE의 경우 무조건 다운로드창이 뜬다. 상황에 따라 써야한다.
		if (userAgent != null && userAgent.indexOf("MSIE 5.5") > -1) { // MS IE
																		// 5.5
																		// 이하
			response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(filename, "UTF-8") + ";");
		} else if (userAgent != null && userAgent.indexOf("MSIE") > -1) { // MS
																			// IE
																			// (보통은
																			// 6.x
																			// 이상
																			// 가정)
			response.setHeader("Content-Disposition",
					"attachment; filename=" + URLEncoder.encode(filename, "UTF-8") + ";");
		} else { // 모질라나 오페라
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(filename.getBytes(CHARSET), "latin1") + ";");
		}

		// 파일 사이즈가 정확하지 않을때는 아예 지정하지 않는다.
		if (filesize > 0) {
			response.setHeader("Content-Length", "" + filesize);
		}

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
			fin = new BufferedInputStream(is);
			outs = new BufferedOutputStream(response.getOutputStream());
			int read = 0;

			while ((read = fin.read(buffer)) != -1) {
				outs.write(buffer, 0, read);
			}
		} catch (IOException ex) {
			// Tomcat ClientAbortException을 잡아서 무시하도록 처리해주는게 좋다.
			logger.error(ex.getMessage(),ex);
		} finally {
			try {
				outs.close();
			} catch (Exception ex1) {
			}

			try {
				fin.close();
			} catch (Exception ex2) {

			}
		} // end of try/catch
	}
}
