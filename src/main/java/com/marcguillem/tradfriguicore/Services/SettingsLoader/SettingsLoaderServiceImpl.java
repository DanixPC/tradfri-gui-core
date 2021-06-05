package com.marcguillem.tradfriguicore.Services.SettingsLoader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.marcguillem.tradfriguicore.Models.SettingsModel;
import com.marcguillem.tradfriguicore.Services.ObjectMapperUtils.ObjectMapperUtils;
import com.marcguillem.tradfriguicore.Services.ReflectionService.IReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Service
public class SettingsLoaderServiceImpl implements ISettingsLoader {

    @Autowired
    private IReflectionService reflectionService;

    public SettingsModel getSettings() {
        try {
            return new Gson().fromJson(new FileReader("./settings.json"), SettingsModel.class);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override
    public SettingsModel setSettings(SettingsModel receivedSettings) throws IOException {
        SettingsModel storedSettings = this.getSettings();
        this.writeSettingsToJson(ObjectMapperUtils.map(receivedSettings, storedSettings));
        return getSettings();
    }

    @Override
    public Boolean createSettingsFile(SettingsModel settingsModel) throws IOException {
        try {
            this.writeSettingsToJson(settingsModel);
            return true;
        } catch (Exception error) {
            System.err.println(error.getCause().getMessage());
            return false;
        }
    }

    @Override
    public Object getSpecificSetting(String setting) throws IOException, InvocationTargetException, IllegalAccessException {
        SettingsModel storedSettings = this.getSettings();
        List<Method> metodos = this.reflectionService.getGetMethods(storedSettings);
        return this.reflectionService.getMethodFromList(metodos,"get" + setting).invoke(storedSettings);
    }

    private void writeSettingsToJson(SettingsModel settingsModel) throws IOException {
        try (Writer writer = new FileWriter("settings.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(settingsModel, writer);
        }
    }
}
