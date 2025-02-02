package com.api.aidepro.admin.mod;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class AdminUserModel {
    @TableId
    private Long uid;
    private String name;
    private String username;
    private String password;
}
