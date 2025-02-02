package com.api.aidepro.official.dao;

import org.apache.ibatis.annotations.Mapper;
import com.api.aidepro.official.model.ContactModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface ContactDao extends BaseMapper<ContactModel> {}
