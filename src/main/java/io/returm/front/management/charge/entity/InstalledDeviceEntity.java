package io.returm.front.management.charge.entity;

import io.returm.front.common.web.entity.AbstractPage;

public class InstalledDeviceEntity extends AbstractPage {

    private String deviceSeq;
    private String rewardSeq;
    private String deviceId;
    private String googleId;

    public String getDeviceSeq() {
        return deviceSeq;
    }

    public void setDeviceSeq(String deviceSeq) {
        this.deviceSeq = deviceSeq;
    }

    public String getRewardSeq() {
        return rewardSeq;
    }

    public void setRewardSeq(String rewardSeq) {
        this.rewardSeq = rewardSeq;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }
}
