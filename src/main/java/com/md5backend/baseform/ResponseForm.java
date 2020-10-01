package com.md5backend.baseform;


public class ResponseForm<T> {
    private Number status = 0;
    private T      data;
    private Number timestamp;
    private String message;

    public ResponseForm() {
        this.message = "";
        this.timestamp = System.currentTimeMillis();
    }

    public Number getStatus() {
        return status;
    }

    public void setStatus(Number status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Number getDatetime() {
        return timestamp;
    }

    public void setDatetime(Number datetime) {
        this.timestamp = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
