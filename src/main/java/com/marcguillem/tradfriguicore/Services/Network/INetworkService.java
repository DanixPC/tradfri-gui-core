package com.marcguillem.tradfriguicore.Services.Network;

import java.net.SocketException;
import java.net.UnknownHostException;

public interface INetworkService {

    String getLocalIp() throws UnknownHostException, SocketException;

}
