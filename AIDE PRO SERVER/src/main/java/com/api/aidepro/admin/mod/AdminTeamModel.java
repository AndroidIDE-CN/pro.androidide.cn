package com.api.aidepro.admin.mod;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("aide_team")
public class AdminTeamModel {
    @TableId
    private Integer id;
    private String name;
    private String avatar;
    private String support;
}
