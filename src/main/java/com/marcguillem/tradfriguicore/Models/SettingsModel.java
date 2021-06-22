package com.marcguillem.tradfriguicore.Models;

public class SettingsModel {

    private String tradfriIp;
    private String tradfriSecurityCode;
    private String identity;
    private String key;

    public SettingsModel(String tradfriIp, String tradfriSecurityCode, String identity, String key) {
        this.tradfriIp = tradfriIp;
        this.tradfriSecurityCode = tradfriSecurityCode;
        this.identity = identity;
        this.key = key;
    }

    public SettingsModel() {
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
