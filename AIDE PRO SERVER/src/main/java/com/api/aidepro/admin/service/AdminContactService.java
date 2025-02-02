package com.api.aidepro.admin.service;

import com.api.aidepro.console.ConsolePrints;
import com.api.aidepro.console.HandleResults;
import org.springframework.stereotype.Service;
import com.api.aidepro.admin.dao.AdminContactDao;
import com.api.aidepro.admin.mod.AdminContactModel;
import com.api.aidepro.admin.man.AdminContactManage;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@Service
public class AdminContactService {

    private final AdminContactDao adminContactDao;

    @Autowired
    public AdminContactService(AdminContactDao adminContactDao) {
        this.adminContactDao = adminContactDao;
    }

    public Map<String, Object> deleteAdminContact(int id) {
        try {
            if (adminContactDao.delete(new QueryWrapper<AdminContactModel>().eq("id", id)) > 0) {
                return new HandleResults().handleResultByCode(200, null, "删除成功");
            } else return new HandleResults().handleResultByCode(409, null, "删除失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> addAdminContact(String name, String url, String description) {
        try {
            AdminContactModel adminContactModel = new AdminContactModel();
            new AdminContactManage().addItem(adminContactModel, name, url, description);
            if (adminContactDao.insert(adminContactModel) > 0) {
                return new HandleResults().handleResultByCode(200, null, "新增成功");
            } else return new HandleResults().handleResultByCode(409, null, "新增失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> updateAdminContact(int id, String name, String url, String description) {
        try {
            AdminContactModel adminContactModel = new AdminContactModel();
            new AdminContactManage().updateItem(adminContactModel, id, name, url, description);
            if (adminContactDao.updateById(adminContactModel) > 0) {
                return new HandleResults().handleResultByCode(200, null, "修改成功");
            } else return new HandleResults().handleResultByCode(409, null, "修改失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> getAdminContactList(int page, int limit) {
        try {
            List<AdminContactModel> adminContactModels = adminContactDao.selectList(null);
            int startWith = (page - 1) * limit;
            int endWith = Math.min(startWith + limit, adminContactModels.size());
            List<AdminContactModel> subList = adminContactModels.subList(startWith, endWith);

            return new HandleResults().handleResultByCode(200, new HashMap<>(){{
                put("items", subList);
                put("total", adminContactModels.size());
            }}, "获取成功");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
