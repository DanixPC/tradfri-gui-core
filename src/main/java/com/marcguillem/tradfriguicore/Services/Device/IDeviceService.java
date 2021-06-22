package com.marcguillem.tradfriguicore.Services.Device;

import com.marcguillem.tradfriguicore.Models.InputBlind;
import com.marcguillem.tradfriguicore.Models.InputLight;
import com.marcguillem.tradfriguicore.Models.InputMessageModel;

public interface IDeviceService {

    void setBlindPosition(InputBlind blindPosition);
    void setLightBrightness(InputLight inputLight);
    void setLightOnOff(InputLight inputLight);
    void setLightColor(InputLight inputLight);
}
