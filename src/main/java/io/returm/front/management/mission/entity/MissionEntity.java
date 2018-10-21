package io.returm.front.management.mission.entity;

import io.returm.front.common.web.entity.AbstractPage;
import io.returm.front.management.common.entity.FileEntity;

public class MissionEntity extends AbstractPage {

    private String missionSeq;
    private String missionTypeCd;
    private String missionSubTypeCd;
    private String title;
    private String contents;
    private String linkUrl;
    private String rewardPoint;
    private String imageFileSq;
    private String imageFilePath;
    private String imageFullFilePath;
    private String delYn;

    private FileEntity fileEntity;
    private String count;

    public String getMissionSeq() {
        return missionSeq;
    }

    public void setMissionSeq(String missionSeq) {
        this.missionSeq = missionSeq;
    }

    public String getMissionTypeCd() {
        return missionTypeCd;
    }

    public void setMissionTypeCd(String missionTypeCd) {
        this.missionTypeCd = missionTypeCd;
    }

    public String getMissionSubTypeCd() {
        return missionSubTypeCd;
    }

    public void setMissionSubTypeCd(String missionSubTypeCd) {
        this.missionSubTypeCd = missionSubTypeCd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(String rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public String getImageFileSq() {
        return imageFileSq;
    }

    public void setImageFileSq(String imageFileSq) {
        this.imageFileSq = imageFileSq;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public String getImageFullFilePath() {
        return imageFullFilePath;
    }

    public void setImageFullFilePath(String imageFullFilePath) {
        this.imageFullFilePath = imageFullFilePath;
    }

    public FileEntity getFileEntity() {
        return fileEntity;
    }

    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
