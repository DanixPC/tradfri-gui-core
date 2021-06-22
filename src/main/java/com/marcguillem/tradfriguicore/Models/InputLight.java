package com.marcguillem.tradfriguicore.Models;

public class InputLight {

    private Integer lightId;
    private Integer brightness;
    private Boolean onOff;
    private String hexColor;

    public InputLight(Integer lightId, Integer brightness, Boolean onOff, String hexColor) {
        this.lightId = lightId;
        this.brightness = brightness;
        this.onOff = onOff;
        this.hexColor = hexColor;
    }

    public InputLight() {
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public Boolean getOnOff() {
        return onOff;
    }

    public void setOnOff(Boolean onOff) {
        this.onOff = onOff;
    }

    public Integer getLightId() {
        return lightId;
    }

    public void setLightId(Integer lightId) {
        this.lightId = lightId;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }
}
