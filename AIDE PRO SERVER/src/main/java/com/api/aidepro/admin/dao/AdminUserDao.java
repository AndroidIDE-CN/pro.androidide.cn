package com.api.aidepro.admin.dao;

import com.api.aidepro.admin.mod.AdminUserModel;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface AdminUserDao extends BaseMapper<AdminUserModel> {}
