package com.api.aidepro.official.model;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("aide_team")
public class TeamModel {
    @TableId
    private Integer id;
    private String name;
    private String avatar;
    private String support;
}
