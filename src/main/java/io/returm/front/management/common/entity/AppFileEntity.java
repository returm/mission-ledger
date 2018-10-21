package io.returm.front.management.common.entity;

import io.returm.front.common.web.entity.AbstractPage;

public class AppFileEntity extends AbstractPage {

    private String appFileSq;
    private String appFileVerCode;
    private String appFileVer;
    private String appFilePkgNm;
    private String appFileNm;
    private String appFileLoc;
    private String appFileSz;
    private String appFileDistStartDt;
    private String appFileDistEndDt;
    private String appFileRegistDt;
    private String appMustUpdate;

    public String getAppFileSq() {
        return appFileSq;
    }

    public void setAppFileSq(String appFileSq) {
        this.appFileSq = appFileSq;
    }

    public String getAppFileVerCode() {
        return appFileVerCode;
    }

    public void setAppFileVerCode(String appFileVerCode) {
        this.appFileVerCode = appFileVerCode;
    }

    public String getAppFileVer() {
        return appFileVer;
    }

    public void setAppFileVer(String appFileVer) {
        this.appFileVer = appFileVer;
    }

    public String getAppFilePkgNm() {
        return appFilePkgNm;
    }

    public void setAppFilePkgNm(String appFilePkgNm) {
        this.appFilePkgNm = appFilePkgNm;
    }

    public String getAppFileNm() {
        return appFileNm;
    }

    public void setAppFileNm(String appFileNm) {
        this.appFileNm = appFileNm;
    }

    public String getAppFileLoc() {
        return appFileLoc;
    }

    public void setAppFileLoc(String appFileLoc) {
        this.appFileLoc = appFileLoc;
    }

    public String getAppFileSz() {
        return appFileSz;
    }

    public void setAppFileSz(String appFileSz) {
        this.appFileSz = appFileSz;
    }

    public String getAppFileDistStartDt() {
        return appFileDistStartDt;
    }

    public void setAppFileDistStartDt(String appFileDistStartDt) {
        this.appFileDistStartDt = appFileDistStartDt;
    }

    public String getAppFileDistEndDt() {
        return appFileDistEndDt;
    }

    public void setAppFileDistEndDt(String appFileDistEndDt) {
        this.appFileDistEndDt = appFileDistEndDt;
    }

    public String getAppFileRegistDt() {
        return appFileRegistDt;
    }

    public void setAppFileRegistDt(String appFileRegistDt) {
        this.appFileRegistDt = appFileRegistDt;
    }

    public String getAppMustUpdate() {
        return appMustUpdate;
    }

    public void setAppMustUpdate(String appMustUpdate) {
        this.appMustUpdate = appMustUpdate;
    }
}
