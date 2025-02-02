package com.api.aidepro.admin.man;

import com.api.aidepro.admin.mod.AdminDownloadModel;
import org.springframework.stereotype.Service;

@Service
public class AdminDownloadManage {
    public void addItem(AdminDownloadModel adminDownloadModel, String name, String url, String description) {
        adminDownloadModel.setUrl(url);
        adminDownloadModel.setName(name);
    }

    public void updateItem(AdminDownloadModel adminDownloadModel, int id, String name, String url, String description) {
        adminDownloadModel.setId(id);
        adminDownloadModel.setUrl(url);
        adminDownloadModel.setName(name);
    }
}
