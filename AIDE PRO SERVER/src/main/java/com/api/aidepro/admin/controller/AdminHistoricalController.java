package com.api.aidepro.admin.controller;

import com.api.aidepro.service.TokenService;
import com.api.aidepro.console.ConsolePrints;
import com.api.aidepro.console.HandleResults;
import org.springframework.web.bind.annotation.*;
import com.api.aidepro.admin.service.AdminHistoricalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("admin/historical")
public class AdminHistoricalController {

    private final TokenService tokenService;
    private final AdminHistoricalService adminHistoricalService;

    @Autowired
    public AdminHistoricalController(TokenService tokenService, AdminHistoricalService adminHistoricalService) {
        this.tokenService = tokenService;
        this.adminHistoricalService = adminHistoricalService;
    }

    @PostMapping("delete")
    public Map<String, Object> deleteAdminHistorical(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            int id = Integer.parseInt(data.get("id").toString());
            return adminHistoricalService.deleteAdminHistorical(id);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @PostMapping("add")
    public Map<String, Object> addAdminHistorical(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            String size = data.get("size").toString();
            String updateLog = data.get("updateLog").toString();
            String versionName = data.get("versionName").toString();
            Long versionCode = Long.parseLong(data.get("versionCode").toString());
            return adminHistoricalService.addAdminHistorical(versionCode, versionName, size, updateLog);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @PostMapping("update")
    public Map<String, Object> updateAdminHistorical(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            int id = Integer.parseInt(data.get("id").toString());
            String size = data.get("size").toString();
            String updateLog = data.get("updateLog").toString();
            String versionName = data.get("versionName").toString();
            Long versionCode = Long.parseLong(data.get("versionCode").toString());
            return adminHistoricalService.updateAdminHistorical(id, versionCode, versionName, size, updateLog);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @GetMapping("query")
    public Map<String, Object> getAdminHistoricalList(@RequestHeader Map<String, String> headers, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            return adminHistoricalService.getAdminHistoricalList(page, limit);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
