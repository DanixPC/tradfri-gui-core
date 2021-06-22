package com.marcguillem.tradfriguicore.Models;

public class InputBlind {

    private Integer blindId;
    private Integer blindPosition;

    public InputBlind(Integer blindId, Integer blindPosition) {
        this.blindId = blindId;
        this.blindPosition = blindPosition;
    }

    public InputBlind() {
    }

    public Integer getBlindId() {
        return blindId;
    }

    public void setBlindId(Integer blindId) {
        this.blindId = blindId;
    }

    public Integer getBlindPosition() {
        return blindPosition;
    }

    public void setBlindPosition(Integer blindPosition) {
        this.blindPosition = blindPosition;
    }
}
