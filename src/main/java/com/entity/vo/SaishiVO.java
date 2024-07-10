package com.entity.vo;

import com.entity.SaishiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 赛事
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("saishi")
public class SaishiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 赛事名称
     */

    @TableField(value = "saishi_name")
    private String saishiName;


    /**
     * 赛事照片
     */

    @TableField(value = "saishi_photo")
    private String saishiPhoto;


    /**
     * 赛事类型
     */

    @TableField(value = "saishi_types")
    private Integer saishiTypes;


    /**
     * 赛事原价
     */

    @TableField(value = "saishi_old_money")
    private Double saishiOldMoney;


    /**
     * 现价
     */

    @TableField(value = "saishi_new_money")
    private Double saishiNewMoney;


    /**
     * 座位
     */

    @TableField(value = "zuowei_number")
    private Integer zuoweiNumber;


    /**
     * 点击次数
     */

    @TableField(value = "saishi_clicknum")
    private Integer saishiClicknum;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "saishi_delete")
    private Integer saishiDelete;


    /**
     * 赛事介绍
     */

    @TableField(value = "saishi_content")
    private String saishiContent;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：赛事名称
	 */
    public String getSaishiName() {
        return saishiName;
    }


    /**
	 * 获取：赛事名称
	 */

    public void setSaishiName(String saishiName) {
        this.saishiName = saishiName;
    }
    /**
	 * 设置：赛事照片
	 */
    public String getSaishiPhoto() {
        return saishiPhoto;
    }


    /**
	 * 获取：赛事照片
	 */

    public void setSaishiPhoto(String saishiPhoto) {
        this.saishiPhoto = saishiPhoto;
    }
    /**
	 * 设置：赛事类型
	 */
    public Integer getSaishiTypes() {
        return saishiTypes;
    }


    /**
	 * 获取：赛事类型
	 */

    public void setSaishiTypes(Integer saishiTypes) {
        this.saishiTypes = saishiTypes;
    }
    /**
	 * 设置：赛事原价
	 */
    public Double getSaishiOldMoney() {
        return saishiOldMoney;
    }


    /**
	 * 获取：赛事原价
	 */

    public void setSaishiOldMoney(Double saishiOldMoney) {
        this.saishiOldMoney = saishiOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getSaishiNewMoney() {
        return saishiNewMoney;
    }


    /**
	 * 获取：现价
	 */

    public void setSaishiNewMoney(Double saishiNewMoney) {
        this.saishiNewMoney = saishiNewMoney;
    }
    /**
	 * 设置：座位
	 */
    public Integer getZuoweiNumber() {
        return zuoweiNumber;
    }


    /**
	 * 获取：座位
	 */

    public void setZuoweiNumber(Integer zuoweiNumber) {
        this.zuoweiNumber = zuoweiNumber;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getSaishiClicknum() {
        return saishiClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setSaishiClicknum(Integer saishiClicknum) {
        this.saishiClicknum = saishiClicknum;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getSaishiDelete() {
        return saishiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setSaishiDelete(Integer saishiDelete) {
        this.saishiDelete = saishiDelete;
    }
    /**
	 * 设置：赛事介绍
	 */
    public String getSaishiContent() {
        return saishiContent;
    }


    /**
	 * 获取：赛事介绍
	 */

    public void setSaishiContent(String saishiContent) {
        this.saishiContent = saishiContent;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
