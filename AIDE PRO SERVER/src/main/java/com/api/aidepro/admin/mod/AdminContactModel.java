package com.api.aidepro.admin.mod;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("aide_contact")
public class AdminContactModel {
    @TableId
    private Integer id;
    private String url;
    private String name;
    private String description;
}
