package com.api.aidepro.admin.service;

import com.api.aidepro.console.ConsolePrints;
import com.api.aidepro.console.HandleResults;
import org.springframework.stereotype.Service;
import com.api.aidepro.admin.dao.AdminFriendsDao;
import com.api.aidepro.admin.mod.AdminFriendsModel;
import com.api.aidepro.admin.man.AdminFriendsManage;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@Service
public class AdminFriendsService {

    private final AdminFriendsDao adminFriendsDao;

    @Autowired
    public AdminFriendsService(AdminFriendsDao adminFriendsDao) {
        this.adminFriendsDao = adminFriendsDao;
    }

    public Map<String, Object> deleteAdminFriend(int id) {
        try {
            if (adminFriendsDao.delete(new QueryWrapper<AdminFriendsModel>().eq("id", id)) > 0) {
                return new HandleResults().handleResultByCode(200, null, "删除成功");
            } else return new HandleResults().handleResultByCode(409, null, "删除失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> addAdminFriend(String name, String icon, String url, String description) {
        try {
            AdminFriendsModel adminFriendsModel = new AdminFriendsModel();
            new AdminFriendsManage().addItem(adminFriendsModel, name, icon, url, description);
            if (adminFriendsDao.insert(adminFriendsModel) > 0) {
                return new HandleResults().handleResultByCode(200, null, "新增成功");
            } else return new HandleResults().handleResultByCode(409, null, "新增失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> updateAdminFriend(int id, String name, String icon, String url, String description) {
        try {
            AdminFriendsModel adminFriendsModel = new AdminFriendsModel();
            new AdminFriendsManage().updateItem(adminFriendsModel, id, name, icon, url, description);
            if (adminFriendsDao.updateById(adminFriendsModel) > 0) {
                return new HandleResults().handleResultByCode(200, null, "修改成功");
            } else return new HandleResults().handleResultByCode(409, null, "修改失败");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }

    public Map<String, Object> getAdminFriendsList(int page, int limit) {
        try {
            List<AdminFriendsModel> adminFriendsModels = adminFriendsDao.selectList(null);
            int startWith = (page - 1) * limit;
            int endWith = Math.min(startWith + limit, adminFriendsModels.size());
            List<AdminFriendsModel> subList = adminFriendsModels.subList(startWith, endWith);

            return new HandleResults().handleResultByCode(200, new HashMap<>(){{
                put("items", subList);
                put("total", adminFriendsModels.size());
            }}, "获取成功");
        } catch (Exception e) {
            new ConsolePrints().printErrorLog(e);
            return new HandleResults().handleResultByCode(500, null, "服务端异常");
        }
    }
}
