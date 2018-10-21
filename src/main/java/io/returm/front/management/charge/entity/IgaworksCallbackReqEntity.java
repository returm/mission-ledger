package io.returm.front.management.charge.entity;

public class IgaworksCallbackReqEntity {

    private String signed_value;
    private String usn;                 /* 리워드를 지급받을 아이디 */
    private String reward_key;          /* 캠페인 완료시마다 발급되는 유니크한 리워드 트랜잭션 식별값 */
    private String quantity;            /* 리워드 수량 */
    private String campaign_key;        /* 캠페인별 부여된 값 */

    public String getSigned_value() {
        return signed_value;
    }

    public void setSigned_value(String signed_value) {
        this.signed_value = signed_value;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getReward_key() {
        return reward_key;
    }

    public void setReward_key(String reward_key) {
        this.reward_key = reward_key;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCampaign_key() {
        return campaign_key;
    }

    public void setCampaign_key(String campaign_key) {
        this.campaign_key = campaign_key;
    }
}
