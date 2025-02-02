package com.api.aidepro.official.model;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("aide_contact")
public class ContactModel {
    @TableId
    private Integer id;
    private String url;
    private String name;
    private String description;
}
