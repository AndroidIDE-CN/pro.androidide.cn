package com.api.aidepro.admin.controller;

import com.api.aidepro.service.TokenService;
import com.api.aidepro.console.ConsolePrints;
import com.api.aidepro.console.HandleResults;
import org.springframework.web.bind.annotation.*;
import com.api.aidepro.admin.service.AdminContactService;

import java.util.Map;

@RestController
@RequestMapping("admin/contact")
public class AdminContactController {

    private final TokenService tokenService;
    private final AdminContactService adminContactService;

    public AdminContactController(TokenService tokenService, AdminContactService adminContactService) {
        this.tokenService = tokenService;
        this.adminContactService = adminContactService;
    }

    @PostMapping("delete")
    public Map<String, Object> deleteAdminFriend(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            int id = Integer.parseInt(data.get("id").toString());
            return adminContactService.deleteAdminContact(id);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @PostMapping("add")
    public Map<String, Object> addAdminFriend(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            String url = data.get("url").toString();
            String name = data.get("name").toString();
            String description = data.get("description").toString();
            return adminContactService.addAdminContact(name, url, description);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @PostMapping("update")
    public Map<String, Object> updateAdminFriend(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            int id = Integer.parseInt(data.get("id").toString());
            String url = data.get("url").toString();
            String name = data.get("name").toString();
            String description = data.get("description").toString();
            return adminContactService.updateAdminContact(id, name, url, description);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @GetMapping("query")
    public Map<String, Object> getAdminFriendsList(@RequestHeader Map<String, String> headers, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            return adminContactService.getAdminContactList(page, limit);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
