package com.marcguillem.tradfriguicore.Models;

public class SettingsModel {

    private String tradfriIp;
    private String tradfriSecurityCode;

    public SettingsModel(String tradfriIp, String tradfriSecurityCode) {
        this.tradfriIp = tradfriIp;
        this.tradfriSecurityCode = tradfriSecurityCode;
    }

    public SettingsModel() {
    }

    public String getTradfriIp() {
        return tradfriIp;
    }

    public void setTradfriIp(String tradfriIp) {
        this.tradfriIp = tradfriIp;
    }

    public String getTradfriSecurityCode() {
        return tradfriSecurityCode;
    }

    public void setTradfriSecurityCode(String tradfriSecurityCode) {
        this.tradfriSecurityCode = tradfriSecurityCode;
    }
}
