package com.hikvision.sus.api.bo;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpgradeInfoBO {

    @NotNull(message = "upgradeInfo不能为空")
    @Valid
    private UpgradeInfo upgradeInfo;

    public UpgradeInfo getUpgradeInfo() {
        return upgradeInfo;
    }

    public void setUpgradeInfo(UpgradeInfo upgradeInfo) {
        this.upgradeInfo = upgradeInfo;
    }

    public static class UpgradeInfo {
        @NotEmpty(message = "packetUrl不能为空")
        private String packetUrl;
        @NotEmpty(message = "identityKey不能为空")
        private String identityKey;

        public String getIdentityKey() {
            return identityKey;
        }

        public void setIdentityKey(String identityKey) {
            this.identityKey = identityKey;
        }

        public String getPacketUrl() {
            return packetUrl;
        }

        public void setPacketUrl(String packetUrl) {
            this.packetUrl = packetUrl;
        }
    }
}
