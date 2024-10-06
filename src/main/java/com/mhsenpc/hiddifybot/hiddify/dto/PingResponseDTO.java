package com.mhsenpc.hiddifybot.hiddify.dto;

public class PingResponseDTO {
    private String msg;    // For 200 OK response
    private String message; // For error responses like 401 and 404
    private Object detail;  // For error responses that may include a detail object

    // Getters and Setters
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "PingResponseDTO{" +
                "msg='" + msg + '\'' +
                ", message='" + message + '\'' +
                ", detail=" + detail +
                '}';
    }
}
