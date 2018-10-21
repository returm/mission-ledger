package io.returm.front.management.constants;

public enum RT001 {

    AD_MISSION("100"),
    RETURM_MISSION("105"),
    SHOPPING_MISSION("110"),
    SHARE_MISSION("120"),
    RUN_MISSION("130"),
    VIDEO_MISSION("140"),
    REVIEW_MISSION("150"),
    KNOWLEDGE_MISSION("160"),
    PARTICIPATION_MISSION("170"),
    SUBSCRIBE_MISSION("180"),
    ETC_MISSION("900");

    private String code;

    RT001(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
