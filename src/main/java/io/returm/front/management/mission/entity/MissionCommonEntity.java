package io.returm.front.management.mission.entity;

import io.returm.common.entity.AbstractPage;

public class MissionCommonEntity extends AbstractPage {

    private String commonSeq;
    private String contents;

    public String getCommonSeq() {
        return commonSeq;
    }

    public void setCommonSeq(String commonSeq) {
        this.commonSeq = commonSeq;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
