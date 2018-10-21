package io.returm.front.management.common.entity;

import io.returm.front.common.web.entity.AbstractPage;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class FileEntity extends AbstractPage {


    /**
     * 파일등록순번
     */
    @NotEmpty
    private String fileSq                                   = null;


    /**
     * 파일크기
     */
    @NotEmpty
    private String fileSize                                 = null;


    /**
     * 파일종류
     */
    @NotEmpty
    @Size(max = 100)
    private String fileExt                                  = null;


    /**
     * 파일위치
     */
    @NotEmpty
    @Size(max = 1024)
    private String filePath                                 = null;


    /**
     * 핸들파일명
     */
    @NotEmpty
    @Size(max = 512)
    private String handleFileNm                             = null;


    /**
     * 원본파일명
     */
    @NotEmpty
    @Size(max = 1024)
    private String orgFileNm                                = null;


    /**
     * ALT_TEXT
     */
    @Size(max = 1024)
    private String altText                                  = null;


    /**
     * 삭제여부
     */
    @NotEmpty
    private String delYn                                    = null;





    public String getFileSq() {
        return this.fileSq;
    }
    public void setFileSq(String fileSq) {
        this.fileSq = fileSq;
    }


    public String getFileSize() {
        return this.fileSize;
    }
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }


    public String getFileExt() {
        return this.fileExt;
    }
    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }


    public String getFilePath() {
        return this.filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public String getHandleFileNm() {
        return this.handleFileNm;
    }
    public void setHandleFileNm(String handleFileNm) {
        this.handleFileNm = handleFileNm;
    }


    public String getOrgFileNm() {
        return this.orgFileNm;
    }
    public void setOrgFileNm(String orgFileNm) {
        this.orgFileNm = orgFileNm;
    }


    public String getAltText() {
        return this.altText;
    }
    public void setAltText(String altText) {
        this.altText = altText;
    }


    public String getDelYn() {
        return this.delYn;
    }
    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }


}
