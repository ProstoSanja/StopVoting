package com.thatguyalex.model;

public class ProcessData {

    public ProcessData(String action, Object payload) {
        setAction(action);
        setPayload(payload);
    }

    private String action;
    private Object payload;

    public String getAction() {
        return action;
    }

    public Object getPayload() {
        return payload;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public static class AuthData {

        public AuthData(String name, boolean voted) {
            this.name = name;
            this.voted = voted;
        }

        private String name;
        private boolean voted;

        public String getName() {
            return name;
        }

        public boolean getVoted() {
            return voted;
        }

    }

}
