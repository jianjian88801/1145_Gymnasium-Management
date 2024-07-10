
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
 * 场地预约
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/changdiOrder")
public class ChangdiOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ChangdiOrderController.class);

    @Autowired
    private ChangdiOrderService changdiOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private ChangdiService changdiService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;



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
        PageUtils page = changdiOrderService.queryPage(params);

        //字典表数据转换
        List<ChangdiOrderView> list =(List<ChangdiOrderView>)page.getList();
        for(ChangdiOrderView c:list){
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
        ChangdiOrderEntity changdiOrder = changdiOrderService.selectById(id);
        if(changdiOrder !=null){
            //entity转view
            ChangdiOrderView view = new ChangdiOrderView();
            BeanUtils.copyProperties( changdiOrder , view );//把实体数据重构到view中

                //级联表
                ChangdiEntity changdi = changdiService.selectById(changdiOrder.getChangdiId());
                if(changdi != null){
                    BeanUtils.copyProperties( changdi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setChangdiId(changdi.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(changdiOrder.getYonghuId());
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
    public R save(@RequestBody ChangdiOrderEntity changdiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,changdiOrder:{}",this.getClass().getName(),changdiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            changdiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        changdiOrder.setInsertTime(new Date());
        changdiOrder.setCreateTime(new Date());
        changdiOrderService.insert(changdiOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ChangdiOrderEntity changdiOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,changdiOrder:{}",this.getClass().getName(),changdiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            changdiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ChangdiOrderEntity> queryWrapper = new EntityWrapper<ChangdiOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChangdiOrderEntity changdiOrderEntity = changdiOrderService.selectOne(queryWrapper);
        if(changdiOrderEntity==null){
            changdiOrderService.updateById(changdiOrder);//根据id更新
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
        changdiOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<ChangdiOrderEntity> changdiOrderList = new ArrayList<>();//上传的东西
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
                            ChangdiOrderEntity changdiOrderEntity = new ChangdiOrderEntity();
//                            changdiOrderEntity.setChangdiOrderUuidNumber(data.get(0));                    //预约单号 要改的
//                            changdiOrderEntity.setChangdiId(Integer.valueOf(data.get(0)));   //场地 要改的
//                            changdiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            changdiOrderEntity.setChangdiOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            changdiOrderEntity.setChangdiOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            changdiOrderEntity.setChangdiOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            changdiOrderEntity.setYuyueTime(new Date(data.get(0)));          //预约日期 要改的
//                            changdiOrderEntity.setInsertTime(date);//时间
//                            changdiOrderEntity.setCreateTime(date);//时间
                            changdiOrderList.add(changdiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //预约单号
                                if(seachFields.containsKey("changdiOrderUuidNumber")){
                                    List<String> changdiOrderUuidNumber = seachFields.get("changdiOrderUuidNumber");
                                    changdiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> changdiOrderUuidNumber = new ArrayList<>();
                                    changdiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("changdiOrderUuidNumber",changdiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //预约单号
                        List<ChangdiOrderEntity> changdiOrderEntities_changdiOrderUuidNumber = changdiOrderService.selectList(new EntityWrapper<ChangdiOrderEntity>().in("changdi_order_uuid_number", seachFields.get("changdiOrderUuidNumber")));
                        if(changdiOrderEntities_changdiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ChangdiOrderEntity s:changdiOrderEntities_changdiOrderUuidNumber){
                                repeatFields.add(s.getChangdiOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [预约单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        changdiOrderService.insertBatch(changdiOrderList);
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
        PageUtils page = changdiOrderService.queryPage(params);

        //字典表数据转换
        List<ChangdiOrderView> list =(List<ChangdiOrderView>)page.getList();
        for(ChangdiOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ChangdiOrderEntity changdiOrder = changdiOrderService.selectById(id);
            if(changdiOrder !=null){


                //entity转view
                ChangdiOrderView view = new ChangdiOrderView();
                BeanUtils.copyProperties( changdiOrder , view );//把实体数据重构到view中

                //级联表
                    ChangdiEntity changdi = changdiService.selectById(changdiOrder.getChangdiId());
                if(changdi != null){
                    BeanUtils.copyProperties( changdi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setChangdiId(changdi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(changdiOrder.getYonghuId());
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
    public R add(@RequestBody ChangdiOrderEntity changdiOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,changdiOrder:{}",this.getClass().getName(),changdiOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            ChangdiEntity changdiEntity = changdiService.selectById(changdiOrder.getChangdiId());
            if(changdiEntity == null){
                return R.error(511,"查不到场地");
            }
            // Double changdiNewMoney = changdiEntity.getChangdiNewMoney();

            if(false){
            }
            else if(changdiEntity.getChangdiNewMoney() == null){
                return R.error(511,"场地价格不能为空");
            }else if(changdiOrder.getYuyueTime() == null){
                return R.error(511,"预约时间不能为空");

            }

            ChangdiOrderEntity changdiOrderEntity = changdiOrderService.selectOne(new EntityWrapper<ChangdiOrderEntity>().eq("changdi_id", changdiOrder.getChangdiId()).eq("yuyue_time", new SimpleDateFormat("yyyy-MM-dd").format(changdiOrder.getYuyueTime())));
            if(changdiOrderEntity != null){
                return R.error(511,"当天该场地已经被预约");
            }


            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - changdiEntity.getChangdiNewMoney();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            changdiOrder.setChangdiOrderTypes(1); //设置订单状态为已支付
            changdiOrder.setChangdiOrderTruePrice(changdiEntity.getChangdiNewMoney()); //设置实付价格
            changdiOrder.setYonghuId(userId); //设置订单支付人id
            changdiOrder.setChangdiOrderPaymentTypes(1);
            changdiOrder.setInsertTime(new Date());
            changdiOrder.setCreateTime(new Date());
            changdiOrderService.insert(changdiOrder);//新增订单
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
            ChangdiOrderEntity changdiOrder = changdiOrderService.selectById(id);
            Integer changdiOrderPaymentTypes = changdiOrder.getChangdiOrderPaymentTypes();
            Integer changdiId = changdiOrder.getChangdiId();
            if(changdiId == null)
                return R.error(511,"查不到该场地");
            ChangdiEntity changdiEntity = changdiService.selectById(changdiId);
            if(changdiEntity == null)
                return R.error(511,"查不到该场地");
            Double changdiNewMoney = changdiEntity.getChangdiNewMoney();
            if(changdiNewMoney == null)
                return R.error(511,"场地价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            //判断是什么支付方式 1代表余额 2代表积分
            if(changdiOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = changdiEntity.getChangdiNewMoney();
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
            }




            changdiOrder.setChangdiOrderTypes(2);//设置订单状态为退款
            changdiOrderService.updateById(changdiOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            changdiService.updateById(changdiEntity);//更新订单中物品的信息
            return R.ok();
        }else{
            return R.error(511,"您没有权限退款");
        }
    }


    /**
     * 同意
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        ChangdiOrderEntity  changdiOrderEntity = new  ChangdiOrderEntity();;
        changdiOrderEntity.setId(id);
        changdiOrderEntity.setChangdiOrderTypes(3);
        boolean b =  changdiOrderService.updateById( changdiOrderEntity);
        if(!b){
            return R.error("同意出错");
        }
        return R.ok();
    }














    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        ChangdiOrderEntity  changdiOrderEntity = new  ChangdiOrderEntity();
        changdiOrderEntity.setId(id);
        changdiOrderEntity.setChangdiOrderTypes(4);
        boolean b =  changdiOrderService.updateById( changdiOrderEntity);
        if(!b){
            return R.error("收货出错");
        }
        return R.ok();
    }














}
