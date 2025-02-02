package com.api.aidepro.official.controller;

import com.api.aidepro.console.HandleResults;
import com.api.aidepro.official.service.WebService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("web")
@CrossOrigin(allowCredentials = "true", origins = "http://11.47.17.166:5173/")
public class WebController {

    private final WebService webService;

    @Autowired
    public WebController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping("info")
    public Map<String, Object> getWebInfo() {
        return new HandleResults().handleResultByCode(200, new HashMap<>(){{
            put("friend", webService.getWebLinks());
            put("download", webService.getWebDownload());
        }}, "获取成功");
    }

    @GetMapping("team")
    public Map<String, Object> getWebSupport() {
        return webService.getWebTeam();
    }

    @GetMapping("contact")
    public Map<String, Object> getWebContact() {
        return webService.getWebContact();
    }

    @GetMapping("historical")
    public Map<String, Object> getWebHistorical() {
        return webService.getWebHistorical();
    }
}
