package com.marcguillem.tradfriguicore.Controllers;

import com.marcguillem.tradfriguicore.Models.InputBlind;
import com.marcguillem.tradfriguicore.Models.InputLight;
import com.marcguillem.tradfriguicore.Services.Device.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @PostMapping("/blind/position")
    public void setBlindPosition(@RequestBody InputBlind blindPosition) {
        this.deviceService.setBlindPosition(blindPosition);
    }

    @PostMapping("/light/brightness")
    public void setLightBrightness(@RequestBody InputLight inputLight) {
        this.deviceService.setLightBrightness(inputLight);
    }

    @PostMapping("/light/onOff")
    public void setLightOnOff(@RequestBody InputLight inputLight) {
        this.deviceService.setLightOnOff(inputLight);
    }

    @PostMapping("/light/color")
    public void setLightColor(@RequestBody InputLight inputLight) {
        this.deviceService.setLightColor(inputLight);
    }

}
