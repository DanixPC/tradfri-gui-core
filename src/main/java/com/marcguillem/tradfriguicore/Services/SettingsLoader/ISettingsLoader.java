package com.marcguillem.tradfriguicore.Services.SettingsLoader;

import com.marcguillem.tradfriguicore.Models.SettingsModel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ISettingsLoader {

    SettingsModel getSettings();
    Object getSpecificSetting(String setting) throws IOException, InvocationTargetException, IllegalAccessException;
    SettingsModel setSettings(SettingsModel settingsModel) throws IOException;
    Boolean createSettingsFile(SettingsModel settingsModel) throws IOException;
}
