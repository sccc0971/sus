package com.hikvision.sus.api.assist;

import java.util.List;

/**
 * 返回数据类
 */
public class RespStatus {

    private String requestURL;//请求URL,可选
    private Integer statusCode;//状态码，
    private String statusString;//状态描述
    private String subStatusCode;//子状态码
    private Integer errorCode;//错误码
    private String errorMsg;//错误描述
    private Integer id;//一般是在添加操作时返回
    private AttachInfo AttachInfo;//附加信息

    public RespStatus(RespStatusEnum respStatusEnum) {
        this.statusCode = respStatusEnum.getStatusCode();
        this.statusString = respStatusEnum.getStatusString();
        this.subStatusCode = respStatusEnum.getSubStatusCode();
        this.errorCode = respStatusEnum.getErrorCode();
        this.errorMsg = respStatusEnum.getErrorMsg();
    }

    public static RespStatus genStatus(RespStatusEnum respStatusEnum) {
        return new RespStatus(respStatusEnum);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RespStatus.AttachInfo getAttachInfo() {
        return AttachInfo;
    }

    public void setAttachInfo(RespStatus.AttachInfo attachInfo) {
        AttachInfo = attachInfo;
    }

    private class AttachInfo {
        private List<StatusObj> StatusList;

        public List<StatusObj> getStatusList() {
            return StatusList;
        }

        public void setStatusList(List<StatusObj> statusList) {
            StatusList = statusList;
        }
    }

    private class StatusObj {
        private Status Status;

        public RespStatus.Status getStatus() {
            return Status;
        }

        public void setStatus(RespStatus.Status status) {
            Status = status;
        }
    }

    private class Status {
        private Integer id;
        private Integer statusCode;
        private String statusString;
        private String subStatusCode;
        private Integer errorCode;
        private String errorMsg;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusString() {
            return statusString;
        }

        public void setStatusString(String statusString) {
            this.statusString = statusString;
        }

        public String getSubStatusCode() {
            return subStatusCode;
        }

        public void setSubStatusCode(String subStatusCode) {
            this.subStatusCode = subStatusCode;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public String getSubStatusCode() {
        return subStatusCode;
    }

    public void setSubStatusCode(String subStatusCode) {
        this.subStatusCode = subStatusCode;
    }

}
