package io.returm.front.management.common.entity;

public class AppFileResEntity {

    private String appVerCode;
    private String appVerName;
    private String appUpdate;
    private String appMustUpdate;
    private String downloadUrl;

    public String getAppVerCode() {
        return appVerCode;
    }

    public void setAppVerCode(String appVerCode) {
        this.appVerCode = appVerCode;
    }

    public String getAppVerName() {
        return appVerName;
    }

    public void setAppVerName(String appVerName) {
        this.appVerName = appVerName;
    }

    public String getAppUpdate() {
        return appUpdate;
    }

    public void setAppUpdate(String appUpdate) {
        this.appUpdate = appUpdate;
    }

    public String getAppMustUpdate() {
        return appMustUpdate;
    }

    public void setAppMustUpdate(String appMustUpdate) {
        this.appMustUpdate = appMustUpdate;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
