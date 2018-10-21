package io.returm.front.management.charge.entity;

import io.returm.front.common.web.entity.AbstractPage;

public class RewardTemporarilyEntity extends AbstractPage {

    private String tempSeq;
    private String missionSeq;
    private String walletAddrTo;
    private String sendYn;

    public String getTempSeq() {
        return tempSeq;
    }

    public void setTempSeq(String tempSeq) {
        this.tempSeq = tempSeq;
    }

    public String getMissionSeq() {
        return missionSeq;
    }

    public void setMissionSeq(String missionSeq) {
        this.missionSeq = missionSeq;
    }

    public String getSendYn() {
        return sendYn;
    }

    public void setSendYn(String sendYn) {
        this.sendYn = sendYn;
    }

    public String getWalletAddrTo() {
        return walletAddrTo;
    }

    public void setWalletAddrTo(String walletAddrTo) {
        this.walletAddrTo = walletAddrTo;
    }
}
