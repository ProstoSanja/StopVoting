package com.thatguyalex.model;

public class ProcessData {

    public ProcessData(String action, String payload) {
        setAction(action);
        setPayload(payload);
    }

    private String action;
    private String payload;

    public String getAction() {
        return action;
    }

    public String getPayload() {
        return  payload;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
