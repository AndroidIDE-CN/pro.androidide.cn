package com.api.aidepro.admin.controller;

import com.api.aidepro.service.TokenService;
import com.api.aidepro.console.ConsolePrints;
import com.api.aidepro.console.HandleResults;
import org.springframework.web.bind.annotation.*;
import com.api.aidepro.admin.service.AdminTeamService;

import java.util.Map;

@RestController
@RequestMapping("admin/team")
public class AdminTeamController {

    private final TokenService tokenService;
    private final AdminTeamService adminTeamService;

    public AdminTeamController(TokenService tokenService, AdminTeamService adminTeamService) {
        this.tokenService = tokenService;
        this.adminTeamService = adminTeamService;
    }

    @PostMapping("delete")
    public Map<String, Object> deleteAdminTeam(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            int id = Integer.parseInt(data.get("id").toString());
            return adminTeamService.deleteAdminTeam(id);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @PostMapping("add")
    public Map<String, Object> addAdminTeam(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            String name = data.get("name").toString();
            String avatar = data.get("avatar").toString();
            String support = data.get("support").toString();
            return adminTeamService.addAdminTeam(name, avatar, support);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @PostMapping("update")
    public Map<String, Object> updateAdminTeam(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> data) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            int id = Integer.parseInt(data.get("id").toString());
            String name = data.get("name").toString();
            String avatar = data.get("avatar").toString();
            String support = data.get("support").toString();
            return adminTeamService.updateAdminTeam(id, name, avatar, support);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    @GetMapping("query")
    public Map<String, Object> getAdminTeamList(@RequestHeader Map<String, String> headers, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        try {
            if (!tokenService.validUserToken(Long.parseLong(headers.get("uid")), headers.get("token"))) return new HandleResults().handleResultByCode(403, null, "权限认证失败");
            return adminTeamService.getAdminTeamList(page, limit);
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
