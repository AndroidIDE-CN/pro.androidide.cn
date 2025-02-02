package com.api.aidepro.admin.man;

import com.api.aidepro.admin.mod.AdminTeamModel;
import org.springframework.stereotype.Service;

@Service
public class AdminTeamMange {
    public void addItem(AdminTeamModel adminTeamModel, String name, String avatar, String support) {
        adminTeamModel.setName(name);
        adminTeamModel.setAvatar(avatar);
        adminTeamModel.setSupport(support);
    }

    public void updateItem(AdminTeamModel adminTeamModel, int id, String name, String avatar, String support) {
        adminTeamModel.setId(id);
        adminTeamModel.setName(name);
        adminTeamModel.setAvatar(avatar);
        adminTeamModel.setSupport(support);
    }
}
