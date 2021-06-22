package com.marcguillem.tradfriguicore.Services.Device;

import com.marcguillem.tradfriguicore.Models.InputBlind;
import com.marcguillem.tradfriguicore.Models.InputLight;
import com.marcguillem.tradfriguicore.Services.Tradfri.TradfriServiceImpl;
import nl.stijngroenen.tradfri.device.Light;
import nl.stijngroenen.tradfri.util.ColourHex;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class DeviceServiceImpl implements IDeviceService {

    @Override
    public void setBlindPosition(InputBlind inputBlind) {
        TradfriServiceImpl.tradfriGateway.getDevice(inputBlind.getBlindId()).toBlind().setPosition(Float.valueOf(inputBlind.getBlindPosition()));
    }

    @Override
    public void setLightBrightness(InputLight inputLight) {
        TradfriServiceImpl.tradfriGateway.getDevice(inputLight.getLightId()).toLight().setBrightness(inputLight.getBrightness());
    }

    @Override
    public void setLightOnOff(InputLight inputLight) {
        TradfriServiceImpl.tradfriGateway.getDevice(inputLight.getLightId()).toLight().setOn(inputLight.getOnOff());
    }

    @Override
    public void setLightColor(InputLight inputLight) {
        Light luz = TradfriServiceImpl.tradfriGateway.getDevice(inputLight.getLightId()).toLight();
        luz.updateColourHex(inputLight.getHexColor());
        luz.applyUpdates();
    }
}
