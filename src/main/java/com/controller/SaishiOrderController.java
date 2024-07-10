
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 赛事订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/saishiOrder")
public class SaishiOrderController {
    private static final Logger logger = LoggerFactory.getLogger(SaishiOrderController.class);

    @Autowired
    private SaishiOrderService saishiOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private SaishiService saishiService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;
@Autowired
private SaishiCommentbackService saishiCommentbackService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = saishiOrderService.queryPage(params);

        //字典表数据转换
        List<SaishiOrderView> list =(List<SaishiOrderView>)page.getList();
        for(SaishiOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SaishiOrderEntity saishiOrder = saishiOrderService.selectById(id);
        if(saishiOrder !=null){
            //entity转view
            SaishiOrderView view = new SaishiOrderView();
            BeanUtils.copyProperties( saishiOrder , view );//把实体数据重构到view中

                //级联表
                SaishiEntity saishi = saishiService.selectById(saishiOrder.getSaishiId());
                if(saishi != null){
                    BeanUtils.copyProperties( saishi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setSaishiId(saishi.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(saishiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody SaishiOrderEntity saishiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,saishiOrder:{}",this.getClass().getName(),saishiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            saishiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        saishiOrder.setInsertTime(new Date());
        saishiOrder.setCreateTime(new Date());
        saishiOrderService.insert(saishiOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SaishiOrderEntity saishiOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,saishiOrder:{}",this.getClass().getName(),saishiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            saishiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<SaishiOrderEntity> queryWrapper = new EntityWrapper<SaishiOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SaishiOrderEntity saishiOrderEntity = saishiOrderService.selectOne(queryWrapper);
        if(saishiOrderEntity==null){
            saishiOrderService.updateById(saishiOrder);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        saishiOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<SaishiOrderEntity> saishiOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            SaishiOrderEntity saishiOrderEntity = new SaishiOrderEntity();
//                            saishiOrderEntity.setSaishiOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            saishiOrderEntity.setSaishiId(Integer.valueOf(data.get(0)));   //赛事 要改的
//                            saishiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            saishiOrderEntity.setSaishiOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            saishiOrderEntity.setSaishiOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            saishiOrderEntity.setSaishiOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            saishiOrderEntity.setBuyZuoweiNumber(data.get(0));                    //购买的座位 要改的
//                            saishiOrderEntity.setBuyZuoweiTime(new Date(data.get(0)));          //订购日期 要改的
//                            saishiOrderEntity.setInsertTime(date);//时间
//                            saishiOrderEntity.setCreateTime(date);//时间
                            saishiOrderList.add(saishiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("saishiOrderUuidNumber")){
                                    List<String> saishiOrderUuidNumber = seachFields.get("saishiOrderUuidNumber");
                                    saishiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> saishiOrderUuidNumber = new ArrayList<>();
                                    saishiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("saishiOrderUuidNumber",saishiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<SaishiOrderEntity> saishiOrderEntities_saishiOrderUuidNumber = saishiOrderService.selectList(new EntityWrapper<SaishiOrderEntity>().in("saishi_order_uuid_number", seachFields.get("saishiOrderUuidNumber")));
                        if(saishiOrderEntities_saishiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SaishiOrderEntity s:saishiOrderEntities_saishiOrderUuidNumber){
                                repeatFields.add(s.getSaishiOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        saishiOrderService.insertBatch(saishiOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = saishiOrderService.queryPage(params);

        //字典表数据转换
        List<SaishiOrderView> list =(List<SaishiOrderView>)page.getList();
        for(SaishiOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SaishiOrderEntity saishiOrder = saishiOrderService.selectById(id);
            if(saishiOrder !=null){


                //entity转view
                SaishiOrderView view = new SaishiOrderView();
                BeanUtils.copyProperties( saishiOrder , view );//把实体数据重构到view中

                //级联表
                    SaishiEntity saishi = saishiService.selectById(saishiOrder.getSaishiId());
                if(saishi != null){
                    BeanUtils.copyProperties( saishi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setSaishiId(saishi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(saishiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody SaishiOrderEntity saishiOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,saishiOrder:{}",this.getClass().getName(),saishiOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            SaishiEntity saishiEntity = saishiService.selectById(saishiOrder.getSaishiId());
            if(saishiEntity == null){
                return R.error(511,"查不到该物品");
            }
            // Double saishiNewMoney = saishiEntity.getSaishiNewMoney();

            if(false){
            }
            else if(saishiEntity.getSaishiNewMoney() == null){
                return R.error(511,"物品价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - saishiEntity.getSaishiNewMoney()*(saishiOrder.getBuyZuoweiNumber().split(",").length);//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            saishiOrder.setSaishiOrderTypes(1); //设置订单状态为已支付
            saishiOrder.setSaishiOrderTruePrice(saishiEntity.getSaishiNewMoney()*(saishiOrder.getBuyZuoweiNumber().split(",").length)); //设置实付价格
            saishiOrder.setYonghuId(userId); //设置订单支付人id
            saishiOrder.setSaishiOrderPaymentTypes(1);
            saishiOrder.setInsertTime(new Date());
            saishiOrder.setCreateTime(new Date());
                saishiOrderService.insert(saishiOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);
            return R.ok();
        }else{
            return R.error(511,"您没有权限支付订单");
        }
    }

    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

        if("用户".equals(role)){
            SaishiOrderEntity saishiOrder = saishiOrderService.selectById(id);
            Integer buyNumber = saishiOrder.getBuyZuoweiNumber().split(",").length;
            Integer saishiOrderPaymentTypes = saishiOrder.getSaishiOrderPaymentTypes();
            Integer saishiId = saishiOrder.getSaishiId();
            if(saishiId == null)
                return R.error(511,"查不到该物品");
            SaishiEntity saishiEntity = saishiService.selectById(saishiId);
            if(saishiEntity == null)
                return R.error(511,"查不到该物品");
            Double saishiNewMoney = saishiEntity.getSaishiNewMoney();
            if(saishiNewMoney == null)
                return R.error(511,"物品价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;


            //判断是什么支付方式 1代表余额 2代表积分
            if(saishiOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = saishiEntity.getSaishiNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


            }




            saishiOrder.setSaishiOrderTypes(2);//设置订单状态为退款
            saishiOrderService.updateById(saishiOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            saishiService.updateById(saishiEntity);//更新订单中物品的信息
            return R.ok();
        }else{
            return R.error(511,"您没有权限退款");
        }
    }


    /**
     * 出票
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        SaishiOrderEntity  saishiOrderEntity = new  SaishiOrderEntity();;
        saishiOrderEntity.setId(id);
        saishiOrderEntity.setSaishiOrderTypes(3);
        boolean b =  saishiOrderService.updateById( saishiOrderEntity);
        if(!b){
            return R.error("出票出错");
        }
        return R.ok();
    }














    /**
     * 使用
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        SaishiOrderEntity  saishiOrderEntity = new  SaishiOrderEntity();
        saishiOrderEntity.setId(id);
        saishiOrderEntity.setSaishiOrderTypes(4);
        boolean b =  saishiOrderService.updateById( saishiOrderEntity);
        if(!b){
            return R.error("使用出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText,HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            SaishiOrderEntity saishiOrder = saishiOrderService.selectById(id);
        if(saishiOrder == null)
            return R.error(511,"查不到该订单");
        if(saishiOrder.getSaishiOrderTypes() != 4)
            return R.error(511,"您不能评价");
        Integer saishiId = saishiOrder.getSaishiId();
        if(saishiId == null)
            return R.error(511,"查不到该物品");

        SaishiCommentbackEntity saishiCommentbackEntity = new SaishiCommentbackEntity();
            saishiCommentbackEntity.setId(id);
            saishiCommentbackEntity.setSaishiId(saishiId);
            saishiCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            saishiCommentbackEntity.setSaishiCommentbackText(commentbackText);
            saishiCommentbackEntity.setReplyText(null);
            saishiCommentbackEntity.setInsertTime(new Date());
            saishiCommentbackEntity.setUpdateTime(null);
            saishiCommentbackEntity.setCreateTime(new Date());
            saishiCommentbackService.insert(saishiCommentbackEntity);

            saishiOrder.setSaishiOrderTypes(5);//设置订单状态为已评价
            saishiOrderService.updateById(saishiOrder);//根据id更新
            return R.ok();
        }else{
            return R.error(511,"您没有权限评价");
        }
    }












}
