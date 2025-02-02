package com.api.aidepro.admin.service;

import com.api.aidepro.console.ConsolePrints;
import com.api.aidepro.console.HandleResults;
import com.api.aidepro.admin.dao.AdminTeamDao;
import org.springframework.stereotype.Service;
import com.api.aidepro.admin.man.AdminTeamMange;
import com.api.aidepro.admin.mod.AdminTeamModel;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@Service
public class AdminTeamService {

    private final AdminTeamDao adminTeamDao;

    @Autowired
    public AdminTeamService(AdminTeamDao adminTeamDao) {
        this.adminTeamDao = adminTeamDao;
    }

    public Map<String, Object> deleteAdminTeam(int id) {
        try {
            if (adminTeamDao.delete(new QueryWrapper<AdminTeamModel>().eq("id", id)) > 0) {
                return new HandleResults().handleResultByCode(200, null, "删除成功");
            } else return new HandleResults().handleResultByCode(409, null, "删除失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> addAdminTeam(String name, String avatar, String support) {
        try {
            AdminTeamModel adminTeamModel = new AdminTeamModel();
            new AdminTeamMange().addItem(adminTeamModel, name, avatar, support);
            if (adminTeamDao.insert(adminTeamModel) > 0) {
                return new HandleResults().handleResultByCode(200, null, "新增成功");
            } else return new HandleResults().handleResultByCode(409, null, "新增失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> updateAdminTeam(int id, String name, String avatar, String support) {
        try {
            AdminTeamModel adminTeamModel = new AdminTeamModel();
            new AdminTeamMange().updateItem(adminTeamModel, id, name, avatar, support);
            if (adminTeamDao.updateById(adminTeamModel) > 0) {
                return new HandleResults().handleResultByCode(200, null, "修改成功");
            } else return new HandleResults().handleResultByCode(409, null, "修改失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> getAdminTeamList(int page, int limit) {
        try {
            List<AdminTeamModel> adminTeamModels = adminTeamDao.selectList(null);
            int startWith = (page - 1) * limit;
            int endWith = Math.min(startWith + limit, adminTeamModels.size());
            List<AdminTeamModel> subList = adminTeamModels.subList(startWith, endWith);

            return new HandleResults().handleResultByCode(200, new HashMap<>(){{
                put("items", subList);
                put("total", adminTeamModels.size());
            }}, "获取成功");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
