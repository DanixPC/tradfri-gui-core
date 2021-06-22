package com.marcguillem.tradfriguicore.Services.Tradfri;

import com.marcguillem.tradfriguicore.Models.SettingsModel;
import com.marcguillem.tradfriguicore.Services.ControllerAdvice.Errors.GenericException;
import com.marcguillem.tradfriguicore.Services.SettingsLoader.ISettingsLoader;
import com.marcguillem.tradfriguicore.Services.TradfriDiscover.ITradfriDiscoverService;
import nl.stijngroenen.tradfri.device.Device;
import nl.stijngroenen.tradfri.device.Gateway;
import nl.stijngroenen.tradfri.util.ColourHex;
import nl.stijngroenen.tradfri.util.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TradfriServiceImpl implements ITradfriService{

    @Autowired
    private ISettingsLoader settingsLoader;

    @Autowired
    private ITradfriDiscoverService tradfriDiscoverService;

    @Autowired
    private ITradfriService tradfriService;

    private static SettingsModel settings;
    public static Gateway tradfriGateway;

    public Boolean loadSettingsInTradfriClass() throws IOException {
        settings = this.settingsLoader.getSettings();
        if(settings != null) {
            if(settings.getTradfriIp() != null && settings.getTradfriSecurityCode() != null) {
                if(settings.getIdentity() == null || settings.getKey() == null) {
                    settings = this.tradfriDiscoverService.getAndSaveIdentityAndKey();
                    return true;
                }
                this.tradfriService.initializeTradfriGateway();
            }
        }
        return false;
    }

    @Override
    public Boolean initializeTradfriGateway() {
        Credentials credentialsGateway;
        if(settings != null && tradfriGateway == null) {
            credentialsGateway = new Credentials(settings.getIdentity(), settings.getKey());
            tradfriGateway = new Gateway(settings.getTradfriIp());
            tradfriGateway.connect(credentialsGateway);
            return true;
        }
        return false;
    }

    @Override
    public List<Device> getAllDevices() {
        if(tradfriGateway != null) {
            return Arrays.asList(tradfriGateway.getDevices());
        }
        throw new GenericException("Error, cannot retrieve list of devices");
    }

    @Override
    public List<String> getHexColours() throws IllegalAccessException {
        List<String> listaRetorno;
        Field[] variables;
        try {
            listaRetorno = new ArrayList<>();
            variables = ColourHex.class.getFields();
            for(Field field: variables) {
                listaRetorno.add((String) field.get(ColourHex.class));
            }
            return listaRetorno;
        } finally {
            variables = null;
            listaRetorno = null;
        }
    }


}
