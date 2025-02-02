package com.api.aidepro.admin.controller;

import com.api.aidepro.service.TokenService;
import com.api.aidepro.console.ConsolePrints;
import com.api.aidepro.console.HandleResults;
import org.springframework.web.bind.annotation.*;
import com.api.aidepro.admin.service.AdminDownloadService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("admin/download")
@CrossOrigin(allowCredentials = "true", origins = "http://11.47.17.166:5173/")
public class AdminDownloadController {

    private final TokenService tokenService;
    private final AdminDownloadService adminDownloadService;

    @Autowired
    public AdminDownloadController(TokenService tokenService, AdminDownloadService adminDownloadService) {
        this.tokenService = tokenService;
        this.adminDownloadService = adminDownloadService;
    }

    @PostMapping("delete")
    public Map<String, Object> deleteAdminDownload(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            int id = Integer.parseInt(data.get("id").toString());
            return adminDownloadService.deleteAdminDownload(id);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @PostMapping("add")
    public Map<String, Object> addAdminDownload(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            String url = data.get("url").toString();
            String name = data.get("name").toString();
            String description = data.get("description").toString();
            return adminDownloadService.addAdminDownload(name, url, description);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @PostMapping("update")
    public Map<String, Object> updateAdminDownload(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            int id = Integer.parseInt(data.get("id").toString());
            String url = data.get("url").toString();
            String name = data.get("name").toString();
            String description = data.get("description").toString();
            return adminDownloadService.updateAdminFriend(id, name, url, description);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @GetMapping("query")
    public Map<String, Object> getAdminDownloadList(@RequestHeader Map<String, String> headers, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            return adminDownloadService.getAdminDownloadList(page, limit);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
