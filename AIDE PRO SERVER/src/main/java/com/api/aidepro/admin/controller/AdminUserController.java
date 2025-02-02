package com.api.aidepro.admin.controller;

import java.util.Map;
import com.api.aidepro.service.TokenService;
import com.api.aidepro.console.ConsolePrints;
import com.api.aidepro.console.HandleResults;
import org.springframework.web.bind.annotation.*;
import com.api.aidepro.admin.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("admin/user")
@CrossOrigin(allowCredentials = "true", origins = "http://11.47.17.166:5173/")
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final TokenService tokenService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService, TokenService tokenService) {
        this.adminUserService = adminUserService;
        this.tokenService = tokenService;
    }

    @PostMapping("logout")
    public Map<String, Object> adminUserLogout(@RequestHeader Map<String, String> headers) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            return adminUserService.adminUserLogout(Long.parseLong(headers.get("uid")));
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @PostMapping("token")
    public Map<String, Object> validUserToken(@RequestHeader Map<String, String> headers) {
        try {
            return adminUserService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"));
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @PostMapping("login")
    public Map<String, Object> adminUserLogin(@RequestBody Map<String, Object> data) {
        try {
            String username = data.get("username").toString();
            String password = data.get("password").toString();
            return adminUserService.adminUserLogin(username, password);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
