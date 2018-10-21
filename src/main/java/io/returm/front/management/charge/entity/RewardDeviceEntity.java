package io.returm.front.management.charge.entity;

import io.returm.front.common.web.entity.AbstractPage;

public class RewardDeviceEntity extends AbstractPage {

    private String infoSeq;
    private String missionSeq;
    private String walletAddr;
    private String deviceId;
    private String termDays;

    public String getInfoSeq() {
        return infoSeq;
    }

    public void setInfoSeq(String infoSeq) {
        this.infoSeq = infoSeq;
    }

    public String getMissionSeq() {
        return missionSeq;
    }

    public void setMissionSeq(String missionSeq) {
        this.missionSeq = missionSeq;
    }

    public String getWalletAddr() {
        return walletAddr;
    }

    public void setWalletAddr(String walletAddr) {
        this.walletAddr = walletAddr;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTermDays() {
        return termDays;
    }

    public void setTermDays(String termDays) {
        this.termDays = termDays;
    }
}
