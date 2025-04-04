package com.api.aidepro.admin.service;

import com.api.aidepro.console.HandleResults;
import com.api.aidepro.console.ConsolePrints;
import org.springframework.stereotype.Service;
import com.api.aidepro.admin.dao.AdminHistoricalDao;
import com.api.aidepro.admin.mod.AdminHistoricalModel;
import com.api.aidepro.admin.man.AdminHistoricalManage;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

@Service
public class AdminHistoricalService {

    private final AdminHistoricalDao adminHistoricalDao;

    @Autowired
    public AdminHistoricalService(AdminHistoricalDao adminHistoricalDao) {
        this.adminHistoricalDao = adminHistoricalDao;
    }

    public Map<String, Object> deleteAdminHistorical(int id) {
        try {
            if (adminHistoricalDao.delete(new QueryWrapper<AdminHistoricalModel>().eq("id", id)) > 0) {
                return new HandleResults().handleResultByCode(200, null, "删除成功");
            } else return new HandleResults().handleResultByCode(409, null, "删除失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> addAdminHistorical(Long versionCode, String versionName, String size, String updateLog) {
        try {
            AdminHistoricalModel adminHistoricalModel = new AdminHistoricalModel();
            new AdminHistoricalManage().addItem(adminHistoricalModel, versionCode, versionName, size, updateLog);
            if (adminHistoricalDao.insert(adminHistoricalModel) > 0) {
                return new HandleResults().handleResultByCode(200, null, "新增成功");
            } else return new HandleResults().handleResultByCode(409, null, "新增失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> updateAdminHistorical(int id, Long versionCode, String versionName, String size, String updateLog) {
        try {
            AdminHistoricalModel adminHistoricalModel = new AdminHistoricalModel();
            new AdminHistoricalManage().updateItem(adminHistoricalModel, id, versionCode, versionName, size, updateLog);
            if (adminHistoricalDao.updateById(adminHistoricalModel) > 0) {
                return new HandleResults().handleResultByCode(200, null, "修改成功");
            } else return new HandleResults().handleResultByCode(409, null, "修改失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> getAdminHistoricalList(int page, int limit) {
        try {
            List<AdminHistoricalModel> adminHistoricalModels = adminHistoricalDao.selectList(null);
            Collections.reverse(adminHistoricalModels); // 倒序
            int startWith = (page - 1) * limit;
            int endWith = Math.min(startWith + limit, adminHistoricalModels.size());
            List<AdminHistoricalModel> subList = adminHistoricalModels.subList(startWith, endWith);

            return new HandleResults().handleResultByCode(200, new HashMap<>(){{
                put("items", subList);
                put("total", adminHistoricalModels.size());
            }}, "获取成功");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
