package io.returm.front.management.charge.entity;

public class AppallCallbackReqEntity {

    private String user_id;         /* 해당 앱 유저식별자 */
    private String point;           /* 유저에게 적립된 포인트 */
    private String point_offer;     /* 제휴사에 적립된 금액(원단위) */
    private String device;          /* 참여한 장치종류 */
    private String ad_name;         /* 참여완료한 광고명 */
    private String seq_id;          /* 적립 고유 ID */

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPoint_offer() {
        return point_offer;
    }

    public void setPoint_offer(String point_offer) {
        this.point_offer = point_offer;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getSeq_id() {
        return seq_id;
    }

    public void setSeq_id(String seq_id) {
        this.seq_id = seq_id;
    }
}
