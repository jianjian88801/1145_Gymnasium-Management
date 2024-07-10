package com.dao;

import com.entity.SaishiOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SaishiOrderView;

/**
 * 赛事订单 Dao 接口
 *
 * @author 
 */
public interface SaishiOrderDao extends BaseMapper<SaishiOrderEntity> {

   List<SaishiOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
