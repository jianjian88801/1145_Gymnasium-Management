package com.entity.view;

import com.entity.SaishiCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 赛事收藏
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("saishi_collection")
public class SaishiCollectionView extends SaishiCollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 类型的值
		*/
		private String saishiCollectionValue;



		//级联表 saishi
			/**
			* 赛事名称
			*/
			private String saishiName;
			/**
			* 赛事照片
			*/
			private String saishiPhoto;
			/**
			* 赛事类型
			*/
			private Integer saishiTypes;
				/**
				* 赛事类型的值
				*/
				private String saishiValue;
			/**
			* 赛事原价
			*/
			private Double saishiOldMoney;
			/**
			* 现价
			*/
			private Double saishiNewMoney;
			/**
			* 座位
			*/
			private Integer zuoweiNumber;
			/**
			* 点击次数
			*/
			private Integer saishiClicknum;
			/**
			* 是否上架
			*/
			private Integer shangxiaTypes;
				/**
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer saishiDelete;
			/**
			* 赛事介绍
			*/
			private String saishiContent;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;
			/**
			* 余额
			*/
			private Double newMoney;

	public SaishiCollectionView() {

	}

	public SaishiCollectionView(SaishiCollectionEntity saishiCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, saishiCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 类型的值
			*/
			public String getSaishiCollectionValue() {
				return saishiCollectionValue;
			}
			/**
			* 设置： 类型的值
			*/
			public void setSaishiCollectionValue(String saishiCollectionValue) {
				this.saishiCollectionValue = saishiCollectionValue;
			}



















				//级联表的get和set saishi
					/**
					* 获取： 赛事名称
					*/
					public String getSaishiName() {
						return saishiName;
					}
					/**
					* 设置： 赛事名称
					*/
					public void setSaishiName(String saishiName) {
						this.saishiName = saishiName;
					}
					/**
					* 获取： 赛事照片
					*/
					public String getSaishiPhoto() {
						return saishiPhoto;
					}
					/**
					* 设置： 赛事照片
					*/
					public void setSaishiPhoto(String saishiPhoto) {
						this.saishiPhoto = saishiPhoto;
					}
					/**
					* 获取： 赛事类型
					*/
					public Integer getSaishiTypes() {
						return saishiTypes;
					}
					/**
					* 设置： 赛事类型
					*/
					public void setSaishiTypes(Integer saishiTypes) {
						this.saishiTypes = saishiTypes;
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
					* 获取： 赛事原价
					*/
					public Double getSaishiOldMoney() {
						return saishiOldMoney;
					}
					/**
					* 设置： 赛事原价
					*/
					public void setSaishiOldMoney(Double saishiOldMoney) {
						this.saishiOldMoney = saishiOldMoney;
					}
					/**
					* 获取： 现价
					*/
					public Double getSaishiNewMoney() {
						return saishiNewMoney;
					}
					/**
					* 设置： 现价
					*/
					public void setSaishiNewMoney(Double saishiNewMoney) {
						this.saishiNewMoney = saishiNewMoney;
					}
					/**
					* 获取： 座位
					*/
					public Integer getZuoweiNumber() {
						return zuoweiNumber;
					}
					/**
					* 设置： 座位
					*/
					public void setZuoweiNumber(Integer zuoweiNumber) {
						this.zuoweiNumber = zuoweiNumber;
					}
					/**
					* 获取： 点击次数
					*/
					public Integer getSaishiClicknum() {
						return saishiClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setSaishiClicknum(Integer saishiClicknum) {
						this.saishiClicknum = saishiClicknum;
					}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
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
					/**
					* 获取： 逻辑删除
					*/
					public Integer getSaishiDelete() {
						return saishiDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setSaishiDelete(Integer saishiDelete) {
						this.saishiDelete = saishiDelete;
					}
					/**
					* 获取： 赛事介绍
					*/
					public String getSaishiContent() {
						return saishiContent;
					}
					/**
					* 设置： 赛事介绍
					*/
					public void setSaishiContent(String saishiContent) {
						this.saishiContent = saishiContent;
					}

























				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}
					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}



}
