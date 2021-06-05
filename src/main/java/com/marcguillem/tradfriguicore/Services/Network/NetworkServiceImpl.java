package com.marcguillem.tradfriguicore.Services.Network;

import org.springframework.stereotype.Service;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

@Service
public class NetworkServiceImpl implements INetworkService {

    @Override
    public String getLocalIp() throws UnknownHostException, SocketException {
        String ip;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        }
        return ip;
    }
}
