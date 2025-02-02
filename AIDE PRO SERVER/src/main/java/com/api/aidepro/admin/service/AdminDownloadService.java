package com.api.aidepro.admin.service;

import com.api.aidepro.console.ConsolePrints;
import com.api.aidepro.console.HandleResults;
import org.springframework.stereotype.Service;
import com.api.aidepro.admin.dao.AdminDownloadDao;
import com.api.aidepro.admin.mod.AdminDownloadModel;
import com.api.aidepro.admin.man.AdminDownloadManage;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@Service
public class AdminDownloadService {

    private final AdminDownloadDao adminDownloadDao;

    @Autowired
    public AdminDownloadService(AdminDownloadDao adminDownloadDao) {
        this.adminDownloadDao = adminDownloadDao;
    }

    public Map<String, Object> deleteAdminDownload(int id) {
        try {
            if (adminDownloadDao.delete(new QueryWrapper<AdminDownloadModel>().eq("id", id)) > 0) {
                return new HandleResults().handleResultByCode(200, null, "删除成功");
            } else return new HandleResults().handleResultByCode(409, null, "删除失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> addAdminDownload(String name, String url, String description) {
        try {
            AdminDownloadModel adminDownloadModel = new AdminDownloadModel();
            new AdminDownloadManage().addItem(adminDownloadModel, name, url, description);
            if (adminDownloadDao.insert(adminDownloadModel) > 0) {
                return new HandleResults().handleResultByCode(200, null, "新增成功");
            } else return new HandleResults().handleResultByCode(409, null, "新增失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> updateAdminFriend(int id, String name, String url, String description) {
        try {
            AdminDownloadModel adminDownloadModel = new AdminDownloadModel();
            new AdminDownloadManage().updateItem(adminDownloadModel, id, name, url, description);
            if (adminDownloadDao.updateById(adminDownloadModel) > 0) {
                return new HandleResults().handleResultByCode(200, null, "修改成功");
            } else return new HandleResults().handleResultByCode(409, null, "修改失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> getAdminDownloadList(int page, int limit) {
        try {
            List<AdminDownloadModel> adminDownloadModels = adminDownloadDao.selectList(null);
            int startWith = (page - 1) * limit;
            int endWith = Math.min(startWith + limit, adminDownloadModels.size());
            List<AdminDownloadModel> subList = adminDownloadModels.subList(startWith, endWith);

            return new HandleResults().handleResultByCode(200, new HashMap<>(){{
                put("items", subList);
                put("total", adminDownloadModels.size());
            }}, "获取成功");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
