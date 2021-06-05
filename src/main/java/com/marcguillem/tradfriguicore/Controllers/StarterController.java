package com.marcguillem.tradfriguicore.Controllers;

import com.marcguillem.tradfriguicore.Models.ResponseMessageModel;
import com.marcguillem.tradfriguicore.Models.SettingsModel;
import com.marcguillem.tradfriguicore.Services.Starter.IStarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/starter")
public class StarterController {

    @Autowired
    private IStarterService starterService;

    @GetMapping("/status")
    public ResponseMessageModel getStatus() throws Exception {
        return this.starterService.checkStartUp();
    }

    @PostMapping("/set")
    public Boolean setStartupSettings(@RequestBody SettingsModel settingsModel) throws IOException {
        return this.starterService.setStartupSettings(settingsModel);
    }

}
