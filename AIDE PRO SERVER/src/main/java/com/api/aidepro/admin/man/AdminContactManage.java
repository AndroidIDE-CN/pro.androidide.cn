package com.api.aidepro.admin.man;


import com.api.aidepro.admin.mod.AdminContactModel;
import org.springframework.stereotype.Service;

@Service
public class AdminContactManage {
    public void addItem(AdminContactModel adminContactModel, String name, String url, String description) {
        adminContactModel.setUrl(url);
        adminContactModel.setName(name);
        adminContactModel.setDescription(description);
    }

    public void updateItem(AdminContactModel adminContactModel, int id, String name, String url, String description) {
        adminContactModel.setId(id);
        adminContactModel.setUrl(url);
        adminContactModel.setName(name);
        adminContactModel.setDescription(description);
    }
}
