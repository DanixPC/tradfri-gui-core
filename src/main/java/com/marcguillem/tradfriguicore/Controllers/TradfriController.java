package com.marcguillem.tradfriguicore.Controllers;

import com.marcguillem.tradfriguicore.Models.ResponseMessageModel;
import com.marcguillem.tradfriguicore.Services.Tradfri.ITradfriService;
import com.marcguillem.tradfriguicore.Services.TradfriDiscover.ITradfriDiscoverService;
import nl.stijngroenen.tradfri.device.Device;
import nl.stijngroenen.tradfri.util.ColourHex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/tradfri")
public class TradfriController {

    @Autowired
    private ITradfriDiscoverService tradfriDiscoverService;

    @Autowired
    private ITradfriService tradfriService;

    @GetMapping("/ip")
    public Callable<ResponseMessageModel> getTradfriIp() {
        return new Callable<ResponseMessageModel>() {
            @Override
            public ResponseMessageModel call() throws Exception {
                ResponseMessageModel responseMessageModel;
                try {
                    responseMessageModel = new ResponseMessageModel();
                    responseMessageModel.setMessage(tradfriDiscoverService.discoverTradfriIp());
                    return responseMessageModel;
                } finally {
                    responseMessageModel = null;
                }
            }
        };
    }

    @GetMapping("/devices/get")
    public List<Device> getAllDevices() {
        return this.tradfriService.getAllDevices();
    }

    @GetMapping("/devices/colors")
    public List<String> getDeviceColors() throws IllegalAccessException {
        return this.tradfriService.getHexColours();
    }
}
