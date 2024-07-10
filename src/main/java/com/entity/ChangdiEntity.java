package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 场地
 *
 * @author 
 * @email
 */
@TableName("changdi")
public class ChangdiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ChangdiEntity() {

	}

	public ChangdiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 场地名称
     */
    @TableField(value = "changdi_name")

    private String changdiName;


    /**
     * 场地照片
     */
    @TableField(value = "changdi_photo")

    private String changdiPhoto;


    /**
     * 场地类型
     */
    @TableField(value = "changdi_types")

    private Integer changdiTypes;


    /**
     * 预约原价格/天
     */
    @TableField(value = "changdi_old_money")

    private Double changdiOldMoney;


    /**
     * 现价/天
     */
    @TableField(value = "changdi_new_money")

    private Double changdiNewMoney;


    /**
     * 点击次数
     */
    @TableField(value = "changdi_clicknum")

    private Integer changdiClicknum;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "changdi_delete")

    private Integer changdiDelete;


    /**
     * 场地介绍
     */
    @TableField(value = "changdi_content")

    private String changdiContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：场地名称
	 */
    public String getChangdiName() {
        return changdiName;
    }


    /**
	 * 获取：场地名称
	 */

    public void setChangdiName(String changdiName) {
        this.changdiName = changdiName;
    }
    /**
	 * 设置：场地照片
	 */
    public String getChangdiPhoto() {
        return changdiPhoto;
    }


    /**
	 * 获取：场地照片
	 */

    public void setChangdiPhoto(String changdiPhoto) {
        this.changdiPhoto = changdiPhoto;
    }
    /**
	 * 设置：场地类型
	 */
    public Integer getChangdiTypes() {
        return changdiTypes;
    }


    /**
	 * 获取：场地类型
	 */

    public void setChangdiTypes(Integer changdiTypes) {
        this.changdiTypes = changdiTypes;
    }
    /**
	 * 设置：预约原价格/天
	 */
    public Double getChangdiOldMoney() {
        return changdiOldMoney;
    }


    /**
	 * 获取：预约原价格/天
	 */

    public void setChangdiOldMoney(Double changdiOldMoney) {
        this.changdiOldMoney = changdiOldMoney;
    }
    /**
	 * 设置：现价/天
	 */
    public Double getChangdiNewMoney() {
        return changdiNewMoney;
    }


    /**
	 * 获取：现价/天
	 */

    public void setChangdiNewMoney(Double changdiNewMoney) {
        this.changdiNewMoney = changdiNewMoney;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getChangdiClicknum() {
        return changdiClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setChangdiClicknum(Integer changdiClicknum) {
        this.changdiClicknum = changdiClicknum;
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
    public Integer getChangdiDelete() {
        return changdiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setChangdiDelete(Integer changdiDelete) {
        this.changdiDelete = changdiDelete;
    }
    /**
	 * 设置：场地介绍
	 */
    public String getChangdiContent() {
        return changdiContent;
    }


    /**
	 * 获取：场地介绍
	 */

    public void setChangdiContent(String changdiContent) {
        this.changdiContent = changdiContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Changdi{" +
            "id=" + id +
            ", changdiName=" + changdiName +
            ", changdiPhoto=" + changdiPhoto +
            ", changdiTypes=" + changdiTypes +
            ", changdiOldMoney=" + changdiOldMoney +
            ", changdiNewMoney=" + changdiNewMoney +
            ", changdiClicknum=" + changdiClicknum +
            ", shangxiaTypes=" + shangxiaTypes +
            ", changdiDelete=" + changdiDelete +
            ", changdiContent=" + changdiContent +
            ", createTime=" + createTime +
        "}";
    }
}
