package io.returm.front.management.charge.entity;

import io.returm.front.common.web.entity.AbstractPage;

public class RewardEntity extends AbstractPage {

    private String rewardSeq;
    private String remoteSeq;
    private String type;
    private String subType;
    private String missionSeq;
    private String walletAddrFrom;
    private String walletAddrTo;
    private String contents;
    private String point;
    private String result;

    public String getRewardSeq() {
        return rewardSeq;
    }

    public void setRewardSeq(String rewardSeq) {
        this.rewardSeq = rewardSeq;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getRemoteSeq() {
        return remoteSeq;
    }

    public void setRemoteSeq(String remoteSeq) {
        this.remoteSeq = remoteSeq;
    }

    public String getWalletAddrFrom() {
        return walletAddrFrom;
    }

    public void setWalletAddrFrom(String walletAddrFrom) {
        this.walletAddrFrom = walletAddrFrom;
    }

    public String getWalletAddrTo() {
        return walletAddrTo;
    }

    public void setWalletAddrTo(String walletAddrTo) {
        this.walletAddrTo = walletAddrTo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMissionSeq() {
        return missionSeq;
    }

    public void setMissionSeq(String missionSeq) {
        this.missionSeq = missionSeq;
    }
}
