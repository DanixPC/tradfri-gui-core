package com.marcguillem.tradfriguicore.Services.TradfriDiscover;

import com.marcguillem.tradfriguicore.Models.SettingsModel;

import java.io.IOException;

public interface ITradfriDiscoverService {

    String discoverTradfriIp() throws IOException;
    SettingsModel getAndSaveIdentityAndKey() throws IOException;
}
