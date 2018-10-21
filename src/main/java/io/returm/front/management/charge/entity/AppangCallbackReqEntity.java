package io.returm.front.management.charge.entity;

public class AppangCallbackReqEntity {

    private String seqId;                   /* 적립 고유ID (적립금 중복지급을 방지를 위해 사용) */
    private String userDate;                /* 회원 정의 데이터 */
    private String price;                   /* 광고 단가 (매체 수익금) */
    private String reward;                  /* 리워드 금액 (오퍼월에서 참여한 경우에만 값이 있음) */
    private String adId;                    /* 광고 ID */
    private String adKey;                   /* 32자리 광고 KEY */
    private String adName;                  /* 광고명 */
    private String adType;                  /* 광고구분 (CPI, CPE, CPA, CPC) */
    private String userAdid;                /* 사용자 기기 36자리 광고 ID (Android: ADID, iOS: IDFA) */
    private String userIp;                  /* 사용자 IP 주소 */

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getUserDate() {
        return userDate;
    }

    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdKey() {
        return adKey;
    }

    public void setAdKey(String adKey) {
        this.adKey = adKey;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getUserAdid() {
        return userAdid;
    }

    public void setUserAdid(String userAdid) {
        this.userAdid = userAdid;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }
}
