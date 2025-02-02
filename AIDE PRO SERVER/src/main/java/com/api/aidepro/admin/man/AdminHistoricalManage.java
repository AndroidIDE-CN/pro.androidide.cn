package com.api.aidepro.admin.man;

import com.api.aidepro.admin.mod.AdminHistoricalModel;
import org.springframework.stereotype.Service;

@Service
public class AdminHistoricalManage {
    public void addItem(AdminHistoricalModel adminHistoricalModel, Long versionCode, String versionName, String size, String downloadUrl, String updateLog) {
        adminHistoricalModel.setSize(size);
        adminHistoricalModel.setUpdateLog(updateLog);
        adminHistoricalModel.setVersionCode(versionCode);
        adminHistoricalModel.setVersionName(versionName);
        adminHistoricalModel.setDownloadUrl(downloadUrl);
    }

    public void updateItem(AdminHistoricalModel adminHistoricalModel, int id, Long versionCode, String versionName, String size, String downloadUrl, String updateLog) {
        adminHistoricalModel.setId(id);
        adminHistoricalModel.setSize(size);
        adminHistoricalModel.setUpdateLog(updateLog);
        adminHistoricalModel.setVersionCode(versionCode);
        adminHistoricalModel.setVersionName(versionName);
        adminHistoricalModel.setDownloadUrl(downloadUrl);
    }
}
