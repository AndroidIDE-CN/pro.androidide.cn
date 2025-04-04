package com.api.aidepro.admin.controller;

import com.api.aidepro.service.TokenService;
import com.api.aidepro.console.ConsolePrints;
import com.api.aidepro.console.HandleResults;
import org.springframework.web.bind.annotation.*;
import com.api.aidepro.admin.service.AdminFriendsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("admin/friends")
public class AdminFriendsController {

    private final TokenService tokenService;
    private final AdminFriendsService adminFriendsService;

    @Autowired
    public AdminFriendsController(TokenService tokenService, AdminFriendsService adminFriendsService) {
        this.tokenService = tokenService;
        this.adminFriendsService = adminFriendsService;
    }

    @PostMapping("delete")
    public Map<String, Object> deleteAdminFriend(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            int id = Integer.parseInt(data.get("id").toString());
            return adminFriendsService.deleteAdminFriend(id);
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
            String icon = data.get("icon").toString();
            String description = data.get("description").toString();
            return adminFriendsService.addAdminFriend(name, icon, url, description);
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
            String icon = data.get("icon").toString();
            String description = data.get("description").toString();
            return adminFriendsService.updateAdminFriend(id, name, icon, url, description);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @GetMapping("query")
    public Map<String, Object> getAdminFriendsList(@RequestHeader Map<String, String> headers, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            return adminFriendsService.getAdminFriendsList(page, limit);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
