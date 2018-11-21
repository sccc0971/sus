package com.hikvision.sus.api.bo;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DevicePacketInfoBO {

    @NotNull(message = "currentPacket不能为空")
    @Valid
    private DevicePacket currentPacket;

    public DevicePacket getCurrentPacket() {
        return currentPacket;
    }

    public void setCurrentPacket(DevicePacket currentPacket) {
        this.currentPacket = currentPacket;
    }

    public class DevicePacket {
        @NotEmpty(message = "identityKey不能为空")
        private String identityKey;
        private String devType;
        @NotEmpty(message = "version不能为空")
        private String version;
        private String buildDate;
        @NotEmpty(message = "packetType不能为空")
        private String packetType;

        public String getIdentityKey() {
            return identityKey;
        }

        public void setIdentityKey(String identityKey) {
            this.identityKey = identityKey;
        }

        public String getPacketType() {
            return packetType;
        }

        public void setPacketType(String packetType) {
            this.packetType = packetType;
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
    }
}
