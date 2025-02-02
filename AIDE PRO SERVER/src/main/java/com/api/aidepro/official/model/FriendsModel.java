package com.api.aidepro.official.model;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("aide_friends")
public class FriendsModel {
    @TableId
    private Integer id;
    private String name;
    private String url;
    private String icon;
    private String description;
}
