package com.api.aidepro.admin.mod;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("aide_historical")
public class AdminHistoricalModel {
    @TableId
    private Integer id;
    private String size;
    private Long versionCode;
    private String updateLog;
    private String versionName;
    private String downloadUrl;
}
