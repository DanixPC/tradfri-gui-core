package com.marcguillem.tradfriguicore.Services.TradfriDiscover;

import com.marcguillem.tradfriguicore.Services.Network.INetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;

@Service
public class TradfriDiscoverServiceImpl implements ITradfriDiscoverService {

    @Autowired
    private INetworkService networkService;

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
                System.out.println(host + " is reachable" + " // NAME: " + InetAddress.getByName(host).getHostName());
            }
            System.out.println(host);
        }
        return null;
    }
}
