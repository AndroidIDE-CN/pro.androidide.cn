package com.api.aidepro.admin.man;

import org.springframework.stereotype.Service;
import com.api.aidepro.admin.mod.AdminFriendsModel;

@Service
public class AdminFriendsManage {
    public void addItem(AdminFriendsModel adminFriendsModel, String name, String icon, String url, String description) {
        adminFriendsModel.setUrl(url);
        adminFriendsModel.setName(name);
        adminFriendsModel.setIcon(icon);
        adminFriendsModel.setDescription(description);
    }

    public void updateItem(AdminFriendsModel adminFriendsModel, int id, String name, String icon, String url, String description) {
        adminFriendsModel.setId(id);
        adminFriendsModel.setUrl(url);
        adminFriendsModel.setName(name);
        adminFriendsModel.setIcon(icon);
        adminFriendsModel.setDescription(description);
    }
}
