package com.dao;

import com.entity.SaishiCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SaishiCommentbackView;

/**
 * 赛事评价 Dao 接口
 *
 * @author 
 */
public interface SaishiCommentbackDao extends BaseMapper<SaishiCommentbackEntity> {

   List<SaishiCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
