package io.returm.front.management.charge.entity;

public class TnkFactoryCallbackReqEntity {

    private String seq_id;      /* 포인트 지급에 대한 고유한 ID 값이다. URL이 반복적으로 호출되더라도 이 값을 사용하여 중복지급여부를 확인할 수 있다. */
    private String pay_pnt;     /* 사용자에게 지급되어야 할 포인트 값이다. */
    private String md_user_nm;  /* 게시앱에서 사용자 식별을 하기 위하여 전달되는 값이다. 이 값을 받기 위해서는 매체앱내에서 setUserName() API를 사용하여 사용자 식별 값을 설정하여야 한다. */
    private String md_chk;      /* 전달된 값이 유효한지 여부를 판단하기 위하여 제공된다. 이 값은 app_key + md_user_nm + seq_id 의 MD5 Hash 값이다. app_key 값은 앱 등록시 부여된 값으로 Tnk 사이트에서 확인할 수 있다. */
    private String app_id;      /* 사용자가 참여한 광고앱의 고유 ID 값이다. */

    public String getSeq_id() {
        return seq_id;
    }

    public void setSeq_id(String seq_id) {
        this.seq_id = seq_id;
    }

    public String getPay_pnt() {
        return pay_pnt;
    }

    public void setPay_pnt(String pay_pnt) {
        this.pay_pnt = pay_pnt;
    }

    public String getMd_user_nm() {
        return md_user_nm;
    }

    public void setMd_user_nm(String md_user_nm) {
        this.md_user_nm = md_user_nm;
    }

    public String getMd_chk() {
        return md_chk;
    }

    public void setMd_chk(String md_chk) {
        this.md_chk = md_chk;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }
}
