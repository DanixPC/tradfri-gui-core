package com.marcguillem.tradfriguicore.Services.Starter;

import com.marcguillem.tradfriguicore.Models.ResponseMessageModel;
import com.marcguillem.tradfriguicore.Models.SettingsModel;

import java.io.IOException;

public interface IStarterService {

    ResponseMessageModel checkStartUp() throws Exception;
    Boolean setStartupSettings(SettingsModel settingsModel) throws IOException;
}
