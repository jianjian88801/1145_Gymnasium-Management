package com.entity.view;

import com.entity.SaishiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 赛事
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("saishi")
public class SaishiView extends SaishiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 赛事类型的值
		*/
		private String saishiValue;
		/**
		* 是否上架的值
		*/
		private String shangxiaValue;



	public SaishiView() {

	}

	public SaishiView(SaishiEntity saishiEntity) {
		try {
			BeanUtils.copyProperties(this, saishiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 赛事类型的值
			*/
			public String getSaishiValue() {
				return saishiValue;
			}
			/**
			* 设置： 赛事类型的值
			*/
			public void setSaishiValue(String saishiValue) {
				this.saishiValue = saishiValue;
			}
			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}

















}
