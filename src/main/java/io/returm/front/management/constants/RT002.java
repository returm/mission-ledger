package io.returm.front.management.constants;

public enum RT002 {

    RETURM_INSTALL("100"),
    RETURM_REVIEW("200"),
    RETURM_SHARE("300");

    private String code;

    RT002(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
