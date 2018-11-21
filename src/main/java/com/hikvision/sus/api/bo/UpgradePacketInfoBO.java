package com.hikvision.sus.api.bo;

import java.util.ArrayList;
import java.util.List;

public class UpgradePacketInfoBO {

    private List<UpgradePacketInfo> upgradePacketList;

    public List<UpgradePacketInfo> getUpgradePacketList() {
        if (upgradePacketList == null) {
            upgradePacketList = new ArrayList<>();
        }
        return upgradePacketList;
    }

    public static class UpgradePacketInfo {
        private UpgradePacket upgradePacket;

        public UpgradePacket getUpgradePacket() {
            return upgradePacket;
        }

        public void setUpgradePacket(UpgradePacket upgradePacket) {
            this.upgradePacket = upgradePacket;
        }
    }

    public static class UpgradePacket {
        private Integer id;
        private String devType;
        private String version;
        private String buildDate;
        private String packetUrl;
        private String packetType;
        private String md5;
        private Boolean recommendUpgrade;

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getPacketType() {
            return packetType;
        }

        public void setPacketType(String packetType) {
            this.packetType = packetType;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDevType() {
            return devType;
        }

        public void setDevType(String devType) {
            this.devType = devType;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getBuildDate() {
            return buildDate;
        }

        public void setBuildDate(String buildDate) {
            this.buildDate = buildDate;
        }

        public String getPacketUrl() {
            return packetUrl;
        }

        public void setPacketUrl(String packetUrl) {
            this.packetUrl = packetUrl;
        }

        public Boolean getRecommendUpgrade() {
            return recommendUpgrade;
        }

        public void setRecommendUpgrade(Boolean recommendUpgrade) {
            this.recommendUpgrade = recommendUpgrade;
        }
    }
}
