package com.marcguillem.tradfriguicore.Services.Tradfri;

import nl.stijngroenen.tradfri.device.Device;

import java.io.IOException;
import java.util.List;

public interface ITradfriService {
    Boolean loadSettingsInTradfriClass() throws IOException;
    Boolean initializeTradfriGateway();
    List<Device> getAllDevices();
    List<String> getHexColours() throws IllegalAccessException;
}
