package com.hikvision.sus.api.assist;

public enum RespStatusEnum {

    OK(1, "OK", "ok", 0x1, "成功"),
    MethodNotAllowed(4, "Invalid Operation", "methodNotAllowed", 0x40000004, "HTTP方法不允许"),
    NoFiles(4, "Invalid Operation", "noFiles", 0x40001011, "文件不存在"),
    MessageParametersLack(6, "Invalid Content", "MessageParametersLack", 0x60000019, "报文参数缺少"),
    BadParameters(6, "Invalid Content", "badParameters", 0x60000001, "参数错误"),
    BeyondARGSRangeLimit(6, "Invalid Content", "beyondARGSRangeLimit", 0x60000025, "超出参数范围限制"),
    UNKNOWN_ERROR(0, "unknown error", "unknown error", 0x00000000, "未知错误");

    private Integer statusCode;
    private String statusString;
    private String subStatusCode;
    private Integer errorCode;
    private String errorMsg;

    RespStatusEnum(Integer statusCode, String statusString, String subStatusCode, Integer errorCode, String errorMsg) {
        this.statusCode = statusCode;
        this.statusString = statusString;
        this.subStatusCode = subStatusCode;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
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
