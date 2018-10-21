package io.returm.front.management.mission.entity;

import io.returm.front.common.web.entity.AbstractPage;

public class RecomMissionEntity extends AbstractPage {

    private String recomSeq;
    private String missionSeq;
    private String useYn;

    public String getRecomSeq() {
        return recomSeq;
    }

    public void setRecomSeq(String recomSeq) {
        this.recomSeq = recomSeq;
    }

    public String getMissionSeq() {
        return missionSeq;
    }

    public void setMissionSeq(String missionSeq) {
        this.missionSeq = missionSeq;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
}
