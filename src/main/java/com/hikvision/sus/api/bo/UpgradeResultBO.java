package com.hikvision.sus.api.bo;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpgradeResultBO {

    @NotEmpty(message = "ipAddress不能为空")
    private String ipAddress;
    private String ipv6Address;
    private Integer portNo;
    private String protocolType;
    private String macAddress;
    private Integer channelID;
    private String dateTime;
    private Integer activePostCount;
    private String eventType;
    private String eventState;
    private String eventDescription;
    @NotNull(message = "onlineUpgradeStatus不能为空")
    @Valid
    private OnlineUpgradeStatus onlineUpgradeStatus;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    public Integer getPortNo() {
        return portNo;
    }

    public void setPortNo(Integer portNo) {
        this.portNo = portNo;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Integer getChannelID() {
        return channelID;
    }

    public void setChannelID(Integer channelID) {
        this.channelID = channelID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getActivePostCount() {
        return activePostCount;
    }

    public void setActivePostCount(Integer activePostCount) {
        this.activePostCount = activePostCount;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventState() {
        return eventState;
    }

    public void setEventState(String eventState) {
        this.eventState = eventState;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public OnlineUpgradeStatus getOnlineUpgradeStatus() {
        return onlineUpgradeStatus;
    }

    public void setOnlineUpgradeStatus(OnlineUpgradeStatus onlineUpgradeStatus) {
        this.onlineUpgradeStatus = onlineUpgradeStatus;
    }

    public class OnlineUpgradeStatus {
        @NotEmpty(message = "identityKey不能为空")
        private String identityKey;
        @NotEmpty(message = "status不能为空")
        private String status;

        public String getIdentityKey() {
            return identityKey;
        }

        public void setIdentityKey(String identityKey) {
            this.identityKey = identityKey;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
