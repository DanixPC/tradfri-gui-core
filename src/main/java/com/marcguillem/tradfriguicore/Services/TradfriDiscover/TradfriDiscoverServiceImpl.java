package com.marcguillem.tradfriguicore.Services.TradfriDiscover;

import com.marcguillem.tradfriguicore.Models.SettingsModel;
import com.marcguillem.tradfriguicore.Services.Network.INetworkService;
import com.marcguillem.tradfriguicore.Services.SettingsLoader.ISettingsLoader;
import nl.stijngroenen.tradfri.device.Gateway;
import nl.stijngroenen.tradfri.util.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

@Service
public class TradfriDiscoverServiceImpl implements ITradfriDiscoverService {

    @Autowired
    private INetworkService networkService;

    @Autowired
    private ISettingsLoader settingsLoader;

    @Override
    public String discoverTradfriIp() throws IOException {
        String subnet;
        String[] splittedLocalIp;
        try {
            splittedLocalIp = networkService.getLocalIp().split("\\.");
            subnet = splittedLocalIp[0] + "." + splittedLocalIp[1] + "." + splittedLocalIp[2];
            return this.checkHosts(subnet);
        } finally {
            subnet = null;
            splittedLocalIp = null;
        }
    }

    private String checkHosts(String subnet) throws IOException {
        int timeout = 1000;
        for (int i = 1; i < 255; i++) {
            String host = subnet + "." + i;
            if (InetAddress.getByName(host).isReachable(timeout)) {
                if (InetAddress.getByName(host).getHostName().startsWith("GW")) {
                    return host;
                }
            }
        }
        return null;
    }

    public SettingsModel getAndSaveIdentityAndKey() throws IOException {
        SettingsModel settingsModel;
        Gateway gateway;
        Credentials credentials;
        try {
            settingsModel = this.settingsLoader.getSettings();
            gateway = new Gateway(settingsModel.getTradfriIp());
            credentials = gateway.connect(settingsModel.getTradfriSecurityCode());
            settingsModel.setIdentity(credentials.getIdentity());
            settingsModel.setKey(credentials.getKey());
            return this.settingsLoader.setSettings(settingsModel);
        } finally {
            settingsModel = null;
            gateway = null;
            credentials = null;
        }
    }
}
