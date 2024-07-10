package com.entity.model;

import com.entity.SaishiOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 赛事订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class SaishiOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String saishiOrderUuidNumber;


    /**
     * 赛事
     */
    private Integer saishiId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 实付价格
     */
    private Double saishiOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer saishiOrderTypes;


    /**
     * 支付类型
     */
    private Integer saishiOrderPaymentTypes;


    /**
     * 购买的座位
     */
    private String buyZuoweiNumber;


    /**
     * 订购日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date buyZuoweiTime;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getSaishiOrderUuidNumber() {
        return saishiOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setSaishiOrderUuidNumber(String saishiOrderUuidNumber) {
        this.saishiOrderUuidNumber = saishiOrderUuidNumber;
    }
    /**
	 * 获取：赛事
	 */
    public Integer getSaishiId() {
        return saishiId;
    }


    /**
	 * 设置：赛事
	 */
    public void setSaishiId(Integer saishiId) {
        this.saishiId = saishiId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getSaishiOrderTruePrice() {
        return saishiOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setSaishiOrderTruePrice(Double saishiOrderTruePrice) {
        this.saishiOrderTruePrice = saishiOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getSaishiOrderTypes() {
        return saishiOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setSaishiOrderTypes(Integer saishiOrderTypes) {
        this.saishiOrderTypes = saishiOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getSaishiOrderPaymentTypes() {
        return saishiOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setSaishiOrderPaymentTypes(Integer saishiOrderPaymentTypes) {
        this.saishiOrderPaymentTypes = saishiOrderPaymentTypes;
    }
    /**
	 * 获取：购买的座位
	 */
    public String getBuyZuoweiNumber() {
        return buyZuoweiNumber;
    }


    /**
	 * 设置：购买的座位
	 */
    public void setBuyZuoweiNumber(String buyZuoweiNumber) {
        this.buyZuoweiNumber = buyZuoweiNumber;
    }
    /**
	 * 获取：订购日期
	 */
    public Date getBuyZuoweiTime() {
        return buyZuoweiTime;
    }


    /**
	 * 设置：订购日期
	 */
    public void setBuyZuoweiTime(Date buyZuoweiTime) {
        this.buyZuoweiTime = buyZuoweiTime;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
