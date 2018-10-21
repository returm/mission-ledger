package io.returm.front.management.common.entity;

import io.returm.front.common.web.entity.AbstractPage;

public class AdminWalletEntity extends AbstractPage {

    private String walletSeq;
    private String walletAddr;

    public String getWalletSeq() {
        return walletSeq;
    }

    public void setWalletSeq(String walletSeq) {
        this.walletSeq = walletSeq;
    }

    public String getWalletAddr() {
        return walletAddr;
    }

    public void setWalletAddr(String walletAddr) {
        this.walletAddr = walletAddr;
    }
}
