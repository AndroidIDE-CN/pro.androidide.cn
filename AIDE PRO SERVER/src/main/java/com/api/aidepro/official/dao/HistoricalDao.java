package com.api.aidepro.official.dao;

import org.apache.ibatis.annotations.Mapper;
import com.api.aidepro.official.model.HistoricalModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface HistoricalDao extends BaseMapper<HistoricalModel> {}
