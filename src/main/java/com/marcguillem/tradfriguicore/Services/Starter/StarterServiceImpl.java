package com.marcguillem.tradfriguicore.Services.Starter;

import com.marcguillem.tradfriguicore.Constants.ErrorMessages;
import com.marcguillem.tradfriguicore.Constants.SettingsCheckConstant;
import com.marcguillem.tradfriguicore.Models.ResponseMessageModel;
import com.marcguillem.tradfriguicore.Models.SettingsModel;
import com.marcguillem.tradfriguicore.Services.ConsoleLogTool.IConsoleLogToolService;
import com.marcguillem.tradfriguicore.Services.ControllerAdvice.Errors.GenericException;
import com.marcguillem.tradfriguicore.Services.ReflectionService.IReflectionService;
import com.marcguillem.tradfriguicore.Services.SettingsLoader.ISettingsLoader;
import com.marcguillem.tradfriguicore.Services.Tradfri.ITradfriService;
import com.marcguillem.tradfriguicore.Services.TradfriDiscover.ITradfriDiscoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StarterServiceImpl implements IStarterService {

    @Autowired
    private ISettingsLoader settingsLoader;

    @Autowired
    private IReflectionService reflectionService;

    @Autowired
    private IConsoleLogToolService consoleLogToolService;

    @Autowired
    private ITradfriService tradfriService;

    @Override
    public ResponseMessageModel checkStartUp() throws Exception {
        ResponseMessageModel responseMessageModel;
        try {
            responseMessageModel = new ResponseMessageModel(String.valueOf(this.checkSettingsFile()));
            return responseMessageModel;
        } finally {
            responseMessageModel = null;
        }
    }

    @Override
    public Boolean setStartupSettings(SettingsModel settingsModel) throws IOException {
        this.settingsLoader.createSettingsFile(settingsModel);
        return this.tradfriService.loadSettingsInTradfriClass();
    }

    private int checkSettingsFile() throws Exception {
        SettingsModel ajustes;
        try {
            ajustes = this.settingsLoader.getSettings();
            if(ajustes == null) {
                return SettingsCheckConstant.SettingsFileNotFound;
            }
            if(this.reflectionService.checkNullFields(ajustes)) {
                return SettingsCheckConstant.SettingsFileFoundButNull;
            }
            if(ajustes.getTradfriIp().equalsIgnoreCase("") || ajustes.getTradfriSecurityCode().equalsIgnoreCase("")) {
                return SettingsCheckConstant.SettingsFileFoundButNull;
            }
            this.tradfriService.loadSettingsInTradfriClass();
            return SettingsCheckConstant.SettingsFileAlreadyConfigured;
        } finally {
            ajustes = null;
        }
    }
}
