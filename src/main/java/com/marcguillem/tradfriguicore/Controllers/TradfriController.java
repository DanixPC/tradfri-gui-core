package com.marcguillem.tradfriguicore.Controllers;

import com.marcguillem.tradfriguicore.Models.ResponseMessageModel;
import com.marcguillem.tradfriguicore.Services.TradfriDiscover.ITradfriDiscoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/tradfri")
public class TradfriController {

    @Autowired
    private ITradfriDiscoverService tradfriDiscoverService;

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

}
