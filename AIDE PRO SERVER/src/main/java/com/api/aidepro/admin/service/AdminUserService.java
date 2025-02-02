package com.api.aidepro.admin.service;

import com.api.aidepro.admin.dao.AdminUserDao;
import com.api.aidepro.console.MD5Encoder;
import com.api.aidepro.admin.mod.AdminUserModel;
import com.api.aidepro.console.ConsolePrints;
import com.api.aidepro.console.HandleResults;
import com.api.aidepro.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;
import java.util.HashMap;

@Service
public class AdminUserService {

    private final AdminUserDao adminUserDao;
    private final TokenService tokenService;

    @Autowired
    public AdminUserService(AdminUserDao adminUserDao, TokenService tokenService) {
        this.adminUserDao = adminUserDao;
        this.tokenService = tokenService;
    }

    public Map<String, Object> adminUserLogout(Long uid) {
        try {
            tokenService.removeUserToken(uid);
            return new HandleResults().handleResultByCode(200, null, "操作成功");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> validUserToken(Long uid, String token) {
        try {
            if (tokenService.validUserToken(uid, token)) {
                return new HandleResults().handleResultByCode(200, null, "验证成功");
            } else return new HandleResults().handleResultByCode(400, null, "验证失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> adminUserLogin(String username, String password) {
        try {
            AdminUserModel adminUserModel = adminUserDao.selectOne(new QueryWrapper<AdminUserModel>().eq("username", username));
            if (adminUserModel == null) {
                return new HandleResults().handleResultByCode(400, null, "账号或密码错误");
            } else {
                if (new MD5Encoder().toMD5(password).equals(adminUserModel.getPassword())) {
                    return new HandleResults().handleResultByCode(200, new HashMap<>(){{
                        put("uid", adminUserModel.getUid());
                        put("token", tokenService.generateUserToken(adminUserModel.getUid(), adminUserModel.getUsername(), adminUserModel.getPassword()));
                    }}, "登录成功");
                } else return new HandleResults().handleResultByCode(400, null, "账号或密码错误");
            }
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
