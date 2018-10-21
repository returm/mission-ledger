package io.returm.front.management.charge.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IgaworksCallbackResEntity {

    @JsonProperty("Result")
    private Boolean Result;

    @JsonProperty("ResultCode")
    private Integer ResultCode;

    @JsonProperty("ResultMsg")
    private String ResultMsg;

    public Boolean getResult() {
        return Result;
    }

    public void setResult(Boolean result) {
        Result = result;
    }

    public Integer getResultCode() {
        return ResultCode;
    }

    public void setResultCode(Integer resultCode) {
        ResultCode = resultCode;
    }

    public String getResultMsg() {
        return ResultMsg;
    }

    public void setResultMsg(String resultMsg) {
        ResultMsg = resultMsg;
    }
}
