package com.api.aidepro.official.dao;

import org.apache.ibatis.annotations.Mapper;
import com.api.aidepro.official.model.TeamModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface TeamDao extends BaseMapper<TeamModel> {}
