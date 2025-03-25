package com.ruoyi.system.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.entity.UserAmount;
import com.ruoyi.common.core.domain.entity.UserInfo;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.mapper.UserShoppingOrderMapper;
import com.ruoyi.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户购物订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
@Service
public class UserShoppingOrderServiceImpl implements IUserShoppingOrderService 
{
    @Resource
    private UserShoppingOrderMapper userShoppingOrderMapper;

    @Autowired
    private IUserShoppingOrderDetailService userShoppingOrderDetailService;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ISellingGoodsInfoService sellingGoodsInfoService;

    @Autowired
    private ISkuService skuService;

    @Autowired
    private ISkuAttrValueService skuAttrValueService;

    @Autowired
    private IUserReceiveAddressService userReceiveAddressService;

    @Autowired
    private IUserAmountService userAmountService;

    @Autowired
    private IUserBillDetailService userBillDetailService;

    @Autowired
    private IUserShoppingCartService userShoppingCartService;

    @Autowired
    private IShopInfoService shopInfoService;

    /**
     * 查询用户购物订单
     * 
     * @param id 用户购物订单主键
     * @return 用户购物订单
     */
    @Override
    public UserShoppingOrder selectUserShoppingOrderById(Long id)
    {
        return userShoppingOrderMapper.selectUserShoppingOrderById(id);
    }

    /**
     * 查询用户购物订单列表
     * 
     * @param userShoppingOrder 用户购物订单
     * @return 用户购物订单
     */
    @Override
    public List<UserShoppingOrder> selectUserShoppingOrderList(UserShoppingOrder userShoppingOrder)
    {
        return userShoppingOrderMapper.selectUserShoppingOrderList(userShoppingOrder);
    }

    /**
     * 填充其他信息
     * @param userShoppingOrders
     */
    @Override
    public void fillOtherInfo(List<UserShoppingOrder> userShoppingOrders){

    }

    /**
     * 新增用户购物订单
     * 
     * @param userShoppingOrder 用户购物订单
     * @return 结果
     */
    @Override
    public int insertUserShoppingOrder(UserShoppingOrder userShoppingOrder)
    {
        userShoppingOrder.setCreateTime(DateUtils.getNowDate());
        return userShoppingOrderMapper.insertUserShoppingOrder(userShoppingOrder);
    }

    /**
     * 修改用户购物订单
     * 
     * @param userShoppingOrder 用户购物订单
     * @return 结果
     */
    @Override
    public int updateUserShoppingOrder(UserShoppingOrder userShoppingOrder)
    {
        userShoppingOrder.setUpdateTime(DateUtils.getNowDate());
        return userShoppingOrderMapper.updateUserShoppingOrder(userShoppingOrder);
    }

    /**
     * 批量删除用户购物订单
     * 
     * @param ids 需要删除的用户购物订单主键
     * @return 结果
     */
    @Override
    public int deleteUserShoppingOrderByIds(Long[] ids)
    {
        return userShoppingOrderMapper.deleteUserShoppingOrderByIds(ids);
    }

    /**
     * 删除用户购物订单信息
     * 
     * @param id 用户购物订单主键
     * @return 结果
     */
    @Override
    public int deleteUserShoppingOrderById(Long id)
    {
        return userShoppingOrderMapper.deleteUserShoppingOrderById(id);
    }

    /**
     * 购物订单平台发货
     * @param userShoppingOrder
     * @return
     */
    @Override
    @Transactional
    public int shipUserShoppingOrder(UserShoppingOrder userShoppingOrder){
        //用户购物订单ID
        Long id = userShoppingOrder.getId();
        if (id == null){
            throw new ServiceException("请选择需要发货的订单");
        }
        //快递单号
        String trackingNumber = userShoppingOrder.getTrackingNumber();
        if (StringUtils.isEmpty(trackingNumber)){
            throw new ServiceException("请输入快递单号");
        }
        //获取购物订单信息
        userShoppingOrder = userShoppingOrderMapper.selectUserShoppingOrderById(id);
        //校验订单状态
        if (!userShoppingOrder.getOrderStatus().equals(1) && !userShoppingOrder.getSubmitPlatformFlag().equals(1)){
            throw new ServiceException("订单非待发货状态");
        }
        userShoppingOrder.setTrackingNumber(trackingNumber);
        userShoppingOrder.setShippingTime(new Date());
        userShoppingOrder.setOrderStatus(2);
        int updateUserShoppingOrder = userShoppingOrderMapper.updateUserShoppingOrder(userShoppingOrder);
        if (updateUserShoppingOrder == 0){
            throw new ServiceException("更新订单信息异常");
        }
        return 1;
    }

    /**
     * 用户提交商城订单
     *
     * @param userShoppingOrders 用户购物订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int userAddShoppingOrders(List<UserShoppingOrder> userShoppingOrders){
        //用户ID
        Long userId = SecurityUtils.getUserId();
        //用户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        if (userInfo == null || !userInfo.getIsDel().equals(0)){
            throw new ServiceException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
        if (!userInfo.getIsLock().equals(0)){
            throw new LangException(HintConstants.SYSTEM_BUSY,"此账号已禁止交易");
        }
        //全部的商品订单明细
        List<UserShoppingOrderDetail> allUserShoppingOrderDetails = new ArrayList<>();
        //如果是购物车下单，收集购物车IDS
        List<Long> userShoppingCartIds = new ArrayList<>();
        //检验传入参数
        userShoppingOrders.stream().forEach(a->{
            //检验是否选择收货地址
            if (StringUtils.isEmpty(a.getShippingAddress())){
                throw new LangException("hint_100","请选择收货地址");
            }
            //用户购物订单明细列表
            List<UserShoppingOrderDetail> userShoppingOrderDetails = a.getUserShoppingOrderDetails();
            if (userShoppingOrderDetails == null || userShoppingOrderDetails.size() == 0){
                throw new LangException("hint_101","请选择购物选项");
            }
            //验证商品信息ID
            if (userShoppingOrderDetails.stream().filter(vo->vo.getSellingGoodsInfoId() == null).count() > 0){
                throw new LangException("hint_102","请选择下单的商品");
            }
            //验证购买数量
            if (userShoppingOrderDetails.stream().filter(vo->vo.getBuyNum() <= 0).count() > 0){
                throw new LangException("hint_103","请输入购买数量");
            }
            //收集购物车ID
            userShoppingOrderDetails.stream().forEach(vo->{
                Object userShoppingCartId = vo.getParams().get("userShoppingCartId");
                if (userShoppingCartId != null){
                    userShoppingCartIds.add(Long.valueOf(String.valueOf(userShoppingCartId)));
                }
            });
            //验证收货地址
            allUserShoppingOrderDetails.addAll(userShoppingOrderDetails);
        });

        //用户收货地址ids
        List<Long> userReceiveAddressIds = userShoppingOrders.stream().map(a -> Long.valueOf(a.getShippingAddress())).distinct().collect(Collectors.toList());
        UserReceiveAddress userReceiveAddressSearch = new UserReceiveAddress();
        userReceiveAddressSearch.getParams().put("ids",userReceiveAddressIds);
        List<UserReceiveAddress> userReceiveAddresses = userReceiveAddressService.selectUserReceiveAddressList(userReceiveAddressSearch);
        if (userReceiveAddressIds.size() != userReceiveAddresses.size()){
            throw new LangException(HintConstants.SYSTEM_BUSY,"获取用户收货地址信息异常");
        }
        //用户收货信息map
        Map<String, UserReceiveAddress> userReceiveAddressmap = userReceiveAddresses.stream().collect(Collectors.toMap(a -> String.valueOf(a.getId()), a -> a));
        //检验身份
        if (userReceiveAddresses.stream().filter(a->!a.getUserId().equals(userId)).count() > 0){
            throw new ServiceException("用户收货地址校验异常", HttpStatus.UNAUTHORIZED);
        }

        //在售商品信息IDS
        List<Long> sellingGoodsInfoIds = allUserShoppingOrderDetails.stream().map(a -> a.getSellingGoodsInfoId()).distinct().collect(Collectors.toList());
        //获取在售商品信息
        SellingGoodsInfo sellingGoodsInfoSearch = new SellingGoodsInfo();
        sellingGoodsInfoSearch.getParams().put("ids",sellingGoodsInfoIds);
        List<SellingGoodsInfo> sellingGoodsInfos = sellingGoodsInfoService.selectSellingGoodsInfoList(sellingGoodsInfoSearch);
        if (sellingGoodsInfos.size() != sellingGoodsInfoIds.size()){
            throw new LangException(HintConstants.SYSTEM_BUSY,"获取商品信息异常");
        }
        //商品信息map
        Map<Long, SellingGoodsInfo> sellingGoodsInfoMap = sellingGoodsInfos.stream().collect(Collectors.toMap(a -> a.getId(), a -> a));

        //检验订单信息是否错乱
        userShoppingOrders.stream().forEach(a->{
            List<UserShoppingOrderDetail> userShoppingOrderDetails = a.getUserShoppingOrderDetails();
            Map<Long, List<UserShoppingOrderDetail>> map = userShoppingOrderDetails.stream().collect(Collectors.groupingBy(b -> sellingGoodsInfoMap.get(b.getSellingGoodsInfoId()).getShopInfoId()));
            if (map.size() > 1){
                throw new LangException(HintConstants.SYSTEM_BUSY,"订单信息错乱");
            }
        });


        //系统商品IDS
        List<Long> systemGoodsInfoIds = sellingGoodsInfos.stream().map(a -> a.getSystemGoodsInfoId()).distinct().collect(Collectors.toList());
        //商品sku信息
        Sku skuSearch = new Sku();
        skuSearch.getParams().put("systemGoodsInfoIds",systemGoodsInfoIds);
        List<Sku> skus = skuService.selectSkuList(skuSearch);
        //商品sku根据系统商品ID分组的map
        Map<Long, List<Sku>> skuGroupBySystemGoodsInfoIdMap = skus.stream().collect(Collectors.groupingBy(a -> a.getSystemGoodsInfoId()));

        //验证sku与商品是否对应
        for (int i = 0; i < allUserShoppingOrderDetails.size(); i++) {
            //用户购物订单明细列表
            UserShoppingOrderDetail userShoppingOrderDetail = allUserShoppingOrderDetails.get(i);
            //在售商品信息ID
            Long sellingGoodsInfoId = userShoppingOrderDetail.getSellingGoodsInfoId();
            //在售商品信息
            SellingGoodsInfo sellingGoodsInfo = sellingGoodsInfoMap.get(sellingGoodsInfoId);
            //系统商品ID
            Long systemGoodsInfoId = sellingGoodsInfo.getSystemGoodsInfoId();
            //此商品对应的所有商品sku
            List<Sku> skuList = skuGroupBySystemGoodsInfoIdMap.get(systemGoodsInfoId);
            //商品SKU ID
            Long skuId = userShoppingOrderDetail.getSkuId();
            if (skuId != null){
                if (skuList == null){
                    throw new LangException(HintConstants.SYSTEM_BUSY,"获取商品SKU信息异常");
                }
                //如果该SKU不是该商品的SKU
                if (!skuList.stream().map(a->a.getId()).collect(Collectors.toList()).contains(skuId)){
                    throw new LangException(HintConstants.SYSTEM_BUSY,"获取商品SKU信息错乱");
                }
            }else {
                //如果该商品有SKU，但是下单时没选择sku
                if (skuList != null){
                    throw new LangException(HintConstants.SYSTEM_BUSY,"请选择下单属性");
                }
            }
        }

        //商品sku对应属性
        SkuAttrValue skuAttrValueSearch = new SkuAttrValue();
        skuAttrValueSearch.getParams().put("skuIds",skus.stream().map(a->a.getId()).collect(Collectors.toList()));
        List<SkuAttrValue> skuAttrValues = skuAttrValueService.selectSkuAttrValueList(skuAttrValueSearch);
        //商品sku对应属性分组的map
        Map<Long, List<SkuAttrValue>> skuAttrValuesMap = skuAttrValues.stream().collect(Collectors.groupingBy(a -> a.getSkuId()));

        //当前时间
        Date nowDateTime = new Date();
        //商品sku map
        Map<Long, Sku> skuMap = skus.stream().collect(Collectors.toMap(a -> a.getId(), a -> a));
        //
        for (int i = 0; i < userShoppingOrders.size(); i++) {
            //用户购物订单信息
            UserShoppingOrder userShoppingOrder = userShoppingOrders.get(i);
            //用户购物订单信息明细信息
            List<UserShoppingOrderDetail> userShoppingOrderDetails = userShoppingOrder.getUserShoppingOrderDetails();
            //订单号
            String orderCode = CodeUtils.generateOrderCode("");
            //统计信息
            for (int j = 0; j < userShoppingOrderDetails.size(); j++) {
                //用户购物订单明细
                UserShoppingOrderDetail userShoppingOrderDetail = userShoppingOrderDetails.get(j);
                //在售商品信息ID
                Long sellingGoodsInfoId = userShoppingOrderDetail.getSellingGoodsInfoId();
                //在售商品信息
                SellingGoodsInfo sellingGoodsInfo = sellingGoodsInfoMap.get(sellingGoodsInfoId);
                //商品单价
                BigDecimal sellingPrice = sellingGoodsInfo.getSellingPrice();
                //商品系统单价
                BigDecimal systemPrice = sellingGoodsInfo.getSystemPrice();
                //商品SKU ID
                Long skuId = userShoppingOrderDetail.getSkuId();
                if (skuId != null){
                    //商品SKU
                    Sku sku = skuMap.get(skuId);
                    userShoppingOrderDetail.setSkuImgs(sku.getSkuImgs());
                    userShoppingOrderDetail.setSkuAttrValues(skuAttrValuesMap.get(skuId));
                    sellingPrice = sku.getGoodsPrice();
                    systemPrice = sku.getSystemPrice();
                }
                //购买数量
                Integer buyNum = userShoppingOrderDetail.getBuyNum();
                //该明细总金额
                BigDecimal orderPrice = sellingPrice.multiply(new BigDecimal(buyNum)).setScale(Constants.BIGDECIMAL_SCALE,Constants.BIGDECIMAL_ROUNDINGMODE);
                //该明细系统总金额
                BigDecimal orderSystemPrice = systemPrice.multiply(new BigDecimal(buyNum)).setScale(Constants.BIGDECIMAL_SCALE,Constants.BIGDECIMAL_ROUNDINGMODE);
                userShoppingOrderDetail.setShopInfoId(sellingGoodsInfo.getShopInfoId());
                userShoppingOrderDetail.setGoodsSinglePrice(sellingPrice);
                userShoppingOrderDetail.setOrderPrice(orderPrice);
                userShoppingOrderDetail.setGoodsSystemSinglePrice(systemPrice);
                userShoppingOrderDetail.setOrderSystemPrice(orderSystemPrice);
                userShoppingOrderDetail.setCreateTime(nowDateTime);
                userShoppingOrderDetail.setFreightAmount(sellingGoodsInfo.getFreightAmount());
                userShoppingOrderDetail.getParams().put("orderCode",orderCode);

                //为返回填充信息
                userShoppingOrderDetail.setGoodsNameLang(sellingGoodsInfo.getGoodsNameLang());
                userShoppingOrderDetail.setGoodsDescLang(sellingGoodsInfo.getGoodsDescLang());
                userShoppingOrderDetail.setGoodsImg(sellingGoodsInfo.getGoodsImg());
                userShoppingOrderDetail.setShopInfoId(sellingGoodsInfo.getShopInfoId());
                userShoppingOrderDetail.setShopName(sellingGoodsInfo.getShopName());
                userShoppingOrderDetail.setSellerId(sellingGoodsInfo.getSellerId());
            }
            //订单总金额
            BigDecimal orderPriceTotal = userShoppingOrderDetails.stream().map(a -> a.getOrderPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
            //订单系统总金额
            BigDecimal orderSystemPriceTotal = userShoppingOrderDetails.stream().map(a -> a.getOrderSystemPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
            //运费金额（每一种商品只收一份运费金额）
            BigDecimal freightAmount = userShoppingOrderDetails.stream().map(a -> a.getFreightAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
            //订单花费加上运费
            orderPriceTotal = orderPriceTotal.add(freightAmount);
            userShoppingOrder.setOrderCode(orderCode);
            userShoppingOrder.setUserId(userId);
            userShoppingOrder.setOrderPrice(orderPriceTotal);
            userShoppingOrder.setOrderSystemPrice(orderSystemPriceTotal);
            userShoppingOrder.setFreightAmount(freightAmount);
            userShoppingOrder.setShippingAddress(JSONObject.toJSONString(userReceiveAddressmap.get(userShoppingOrder.getShippingAddress())));
            userShoppingOrder.setShopInfoId(userShoppingOrderDetails.get(0).getShopInfoId());
            userShoppingOrder.setSellerId(userShoppingOrderDetails.get(0).getSellerId());
            userShoppingOrder.setOrderStatus(0);
            userShoppingOrder.setPayTime(null);
            userShoppingOrder.setShippingTime(null);
            userShoppingOrder.setFinishTime(null);
            userShoppingOrder.setCreateTime(nowDateTime);
        }

        //插入用户购物订单
        int insertUserShoppingOrder = userShoppingOrderMapper.insertUserShoppingOrders(userShoppingOrders);
        if (insertUserShoppingOrder != userShoppingOrders.size()){
            throw new LangException(HintConstants.SYSTEM_BUSY,"插入用户购物订单异常");
        }
        //对应订单号的订单ID Map
        Map<String, Long> map = userShoppingOrders.stream().collect(Collectors.toMap(a -> a.getOrderCode(), a -> a.getId()));
        //给购物订单明细绑定购物主订单
        allUserShoppingOrderDetails.stream().forEach(a->a.setUserShoppingOrderId(map.get(a.getParams().get("orderCode"))));
        //插入用户购物订单明细
        int insertUserShoppingOrderDetail = userShoppingOrderDetailService.insertUserShoppingOrderDetails(allUserShoppingOrderDetails);
        if (insertUserShoppingOrderDetail != allUserShoppingOrderDetails.size()){
            throw new LangException(HintConstants.SYSTEM_BUSY,"插入用户购物订单明细异常");
        }
        if (userShoppingCartIds.size() > 0){
            //如果是购物车下单，清空购物车
            int deleteUserShoppingCartByIds = userShoppingCartService.deleteUserShoppingCartByIds(userShoppingCartIds.toArray(new Long[userShoppingCartIds.size()]));
            if (deleteUserShoppingCartByIds != userShoppingCartIds.size()){
                throw new LangException(HintConstants.SYSTEM_BUSY,"清空购物车数据异常");
            }
        }
        return 1;
    }

    /**
     * 用户支付商城订单
     * @param userShoppingOrders
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int userPayShoppingOrders(List<UserShoppingOrder> userShoppingOrders){
        //用户ID
        Long userId = SecurityUtils.getUserId();
        //订单IDS
        List<Long> userShoppingOrderIds = userShoppingOrders.stream().map(a -> a.getId()).distinct().collect(Collectors.toList());

        //获取订单信息
        UserShoppingOrder userShoppingOrderSearch = new UserShoppingOrder();
        userShoppingOrderSearch.getParams().put("ids",userShoppingOrderIds);
        userShoppingOrders = userShoppingOrderMapper.selectUserShoppingOrderList(userShoppingOrderSearch);
        if (userShoppingOrders.size() != userShoppingOrderIds.size()){
            throw new LangException(HintConstants.SYSTEM_BUSY,"获取订单信息异常");
        }
        //共需支付
        BigDecimal payAmount = userShoppingOrders.stream().map(a -> a.getOrderPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        //判断余额是否足够
        UserAmount userAmount = userAmountService.getUserAmount(userId, 3L);
        //用户余额变更前
        BigDecimal userAmountBefore = userAmount.getAmount();
        if (payAmount.compareTo(userAmountBefore) > 0){
            throw new LangException("hint_104","余额不足");
        }
        //余额变更后
        BigDecimal userAmountAfter = userAmountBefore.subtract(payAmount);
        userAmount.setAmount(userAmountAfter);
        int updateUserAmount = userAmountService.updateUserAmount(userAmount);
        if (updateUserAmount <= 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户余额异常");
        }
        //插入明细
        //插入账户明细记录
        UserBillDetail userBillDetail = new UserBillDetail();
        userBillDetail.setUserId(userId);
        userBillDetail.setDeType("用户商城购物下单");
        userBillDetail.setDeSummary("用户商城购物下单");
        userBillDetail.setOrderAmount(payAmount.negate());
        userBillDetail.setOrderTime(new Date());
        userBillDetail.setAmountBefore(userAmountBefore);
        userBillDetail.setAmountAfter(userAmountAfter);
        userBillDetail.setRelateOrderId(null);
        userBillDetail.setRelateInfo(userShoppingOrderIds.toString());
        userBillDetail.setOrderClass(73);
        userBillDetail.setCurrencyId(userAmount.getCurrencyId());
        int insertUserBillDetail = userBillDetailService.insertUserBillDetail(userBillDetail);
        if (insertUserBillDetail <= 0) {
            throw new LangException(HintConstants.SYSTEM_BUSY,"插入明细异常");
        }
        //更新订单状态
        for (int i = 0; i < userShoppingOrders.size(); i++) {
            //订单信息
            UserShoppingOrder userShoppingOrder = userShoppingOrders.get(i);
            userShoppingOrder.setOrderStatus(1);
            userShoppingOrder.setPayTime(new Date());
            int updateUserShoppingOrder = userShoppingOrderMapper.updateUserShoppingOrder(userShoppingOrder);
            if (updateUserShoppingOrder <= 0){
                throw new LangException(HintConstants.SYSTEM_BUSY,"更新订单状态异常");
            }
        }
        return 1;
    }

    /**
     * 用户取消商订单
     * @param userShoppingOrder
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int userCancelShoppingOrders(UserShoppingOrder userShoppingOrder){
        //用户ID
        Long userId = SecurityUtils.getUserId();
        //订单ID
        Long id = userShoppingOrder.getId();
        //订单信息
        userShoppingOrder = userShoppingOrderMapper.selectUserShoppingOrderById(id);
        if (!userShoppingOrder.getOrderStatus().equals(0)){
            throw new LangException(HintConstants.SYSTEM_BUSY,"订单状态异常");
        }
        //校验用户信息
        if (!userId.equals(userShoppingOrder.getUserId())){
            throw new ServiceException("校验用户信息异常", HttpStatus.UNAUTHORIZED);
        }
        userShoppingOrder.setOrderStatus(3);
        userShoppingOrder.setFinishTime(new Date());
        return userShoppingOrderMapper.updateUserShoppingOrder(userShoppingOrder);
    }

    /**
     * 用户确认收货
     * @param userShoppingOrder
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int userConfirmReceipt(UserShoppingOrder userShoppingOrder){
        //订单ID
        Long id = userShoppingOrder.getId();
        //订单信息
        userShoppingOrder = userShoppingOrderMapper.selectUserShoppingOrderById(id);
        if (!userShoppingOrder.getOrderStatus().equals(2)){
            throw new LangException(HintConstants.SYSTEM_BUSY,"订单状态异常");
        }
        //机器人跳过校验
        if (!userShoppingOrder.getAccountType().equals(2)){
            //用户ID
            Long userId = SecurityUtils.getUserId();
            //校验用户信息
            if (!userId.equals(userShoppingOrder.getUserId())){
                throw new LangException(HintConstants.SYSTEM_BUSY,"订单状态异常");
            }
        }
        //商户ID
        Long sellerId = userShoppingOrder.getSellerId();
        //给商户打利润并且返还本金
        //本金
        BigDecimal orderSystemPrice = userShoppingOrder.getOrderSystemPrice();
        //用户支付金额
        BigDecimal orderPrice = userShoppingOrder.getOrderPrice();
        //利润
        BigDecimal orderProfit = orderPrice.subtract(orderSystemPrice);
        //商户钱包信息
        UserAmount userAmount = userAmountService.getUserAmount(sellerId, 3L);
        //商户余额变更前
        BigDecimal userAmountBefore = userAmount.getAmount();
        //商户余额变更后
        BigDecimal userAmountBeforeAfter = userAmountBefore.add(orderSystemPrice);
        //商户流水记录
        UserBillDetail userBillDetail = new UserBillDetail();
        userBillDetail.setUserId(sellerId);
        userBillDetail.setDeType("订单完成商户提交本金返还");
        userBillDetail.setDeSummary("订单完成商户提交本金返还");
        userBillDetail.setOrderAmount(orderSystemPrice);
        userBillDetail.setOrderTime(new Date());
        userBillDetail.setAmountBefore(userAmountBefore);
        userBillDetail.setAmountAfter(userAmountBeforeAfter);
        userBillDetail.setRelateOrderId(userShoppingOrder.getId());
        userBillDetail.setOrderClass(76);
        userBillDetail.setCurrencyId(userAmount.getCurrencyId());
        int insertUserBillDetail = userBillDetailService.insertUserBillDetail(userBillDetail);
        if (insertUserBillDetail == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"新增本金返还流水记录异常");
        }
        //商户余额变更前
        userAmountBefore = userAmountBeforeAfter;
        //商户余额变更后
        userAmountBeforeAfter = userAmountBefore.add(orderProfit);
        //商户流水记录
        UserBillDetail userBillDetail2 = new UserBillDetail();
        userBillDetail2.setUserId(sellerId);
        userBillDetail2.setDeType("订单完成商户利润收入");
        userBillDetail2.setDeSummary("订单完成商户利润收入");
        userBillDetail2.setOrderAmount(orderProfit);
        userBillDetail2.setOrderTime(new Date());
        userBillDetail2.setAmountBefore(userAmountBefore);
        userBillDetail2.setAmountAfter(userAmountBeforeAfter);
        userBillDetail2.setRelateOrderId(userShoppingOrder.getId());
        userBillDetail2.setOrderClass(77);
        userBillDetail2.setCurrencyId(userAmount.getCurrencyId());
        int insertUserBillDetail2 = userBillDetailService.insertUserBillDetail(userBillDetail2);
        if (insertUserBillDetail2 == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"新增利润收入流水记录异常");
        }
        userAmount.setAmount(userAmountBeforeAfter);
        int updateUserAmount = userAmountService.updateUserAmount(userAmount);
        if (updateUserAmount == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新商户余额异常");
        }
        //更新订单信息
        userShoppingOrder.setOrderStatus(5);
        userShoppingOrder.setFinishTime(new Date());
        int updateUserShoppingOrder = userShoppingOrderMapper.updateUserShoppingOrder(userShoppingOrder);
        if (updateUserShoppingOrder == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新订单信息异常");
        }
        //更新商品销量
        List<UserShoppingOrderDetail> userShoppingOrderDetails = userShoppingOrder.getUserShoppingOrderDetails();
        SellingGoodsInfo sellingGoodsInfoSearch = new SellingGoodsInfo();
        sellingGoodsInfoSearch.getParams().put("ids",userShoppingOrderDetails.stream().map(a->a.getSellingGoodsInfoId()).collect(Collectors.toList()));
        //店铺商品信息
        List<SellingGoodsInfo> sellingGoodsInfos = sellingGoodsInfoService.selectSellingGoodsInfoList(sellingGoodsInfoSearch);
        //店铺商品信息
        Map<Long, SellingGoodsInfo> sellingGoodsInfoMap = sellingGoodsInfos.stream().collect(Collectors.toMap(a -> a.getId(), a -> a));
        for (int i = 0; i < userShoppingOrderDetails.size(); i++) {
            UserShoppingOrderDetail userShoppingOrderDetail = userShoppingOrderDetails.get(i);
            Integer buyNum = userShoppingOrderDetail.getBuyNum();
            Long sellingGoodsInfoId = userShoppingOrderDetail.getSellingGoodsInfoId();
            //店铺商品信息
            SellingGoodsInfo sellingGoodsInfo = sellingGoodsInfoMap.get(sellingGoodsInfoId);
            if (sellingGoodsInfo != null){
                sellingGoodsInfo.setSoldNum(sellingGoodsInfo.getSoldNum()+buyNum);
                int updateSellingGoodsInfo = sellingGoodsInfoService.updateSellingGoodsInfo(sellingGoodsInfo);
                if (updateSellingGoodsInfo == 0){
                    throw new LangException(HintConstants.SYSTEM_BUSY,"更新店铺商品信息异常");
                }
            }
        }
        //店铺信息ID
        Long shopInfoId = userShoppingOrder.getShopInfoId();
        ShopInfo shopInfo = shopInfoService.selectShopInfoById(shopInfoId);
        shopInfo.setSoldNum(shopInfo.getSoldNum()+userShoppingOrderDetails.stream().map(a->a.getBuyNum()).reduce(0,Integer::sum));
        int updateShopInfo = shopInfoService.updateShopInfo(shopInfo);
        if (updateShopInfo == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新店铺信息异常");
        }
        return 1;
    }

    /**
     * 商户提交订单到后台
     * @param userShoppingOrders
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int sellerSubmitOrderToPlatform(List<UserShoppingOrder> userShoppingOrders){
        //商户ID
        Long userId = SecurityUtils.getUserId();
        //商户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        //如果不是商户
        if (!userInfo.getSellerFlag().equals(1)){
            throw new ServiceException("请登录商户账号",HttpStatus.UNAUTHORIZED);
        }
        //检验传参格式
        if (userShoppingOrders.stream().filter(a->a.getId() == null).count() > 0){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要提交到平台的订单");
        }
        //用户购物订单IDS
        List<Long> userShoppingOrderids = userShoppingOrders.stream().map(a -> a.getId()).collect(Collectors.toList());
        //获取订单信息
        UserShoppingOrder userShoppingOrder = new UserShoppingOrder();
        userShoppingOrder.getParams().put("ids",userShoppingOrderids);
        userShoppingOrders = userShoppingOrderMapper.selectUserShoppingOrderList(userShoppingOrder);
        //检验
        userShoppingOrders.stream().forEach(a->{
            //检验订单是否属于自己
            if (!a.getSellerId().equals(userId)){
                throw new LangException(HintConstants.SYSTEM_BUSY,a.getOrderCode()+"提交商户校验异常");
            }
            //检验订单状态是否已付款
            if (!a.getOrderStatus().equals(1)){
                throw new LangException(HintConstants.SYSTEM_BUSY,a.getOrderCode()+"订单状态异常");
            }
        });
        //总共需要支付成本
        BigDecimal payAmount = userShoppingOrders.stream().map(a -> a.getOrderSystemPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        //商户钱包信息
        UserAmount userAmount = userAmountService.getUserAmount(userId, 3L);
        //商户余额变更前
        BigDecimal userAmountBefore = userAmount.getAmount();
        if (payAmount.compareTo(userAmountBefore) > 0){
            throw new LangException("hint_104","余额不足");
        }
        //商户余额变更后
        BigDecimal userAmountAfter = userAmountBefore.subtract(payAmount);
        //更新商户余额
        userAmount.setAmount(userAmountAfter);
        int updateUserAmount = userAmountService.updateUserAmount(userAmount);
        if (updateUserAmount == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新商户余额异常");
        }

        //插入账户明细记录
        UserBillDetail userBillDetail = new UserBillDetail();
        userBillDetail.setUserId(userId);
        userBillDetail.setDeType("商户提交订单扣除");
        userBillDetail.setDeSummary("商户提交订单扣除");
        userBillDetail.setOrderAmount(payAmount.negate());
        userBillDetail.setOrderTime(new Date());
        userBillDetail.setAmountBefore(userAmountBefore);
        userBillDetail.setAmountAfter(userAmountAfter);
        userBillDetail.setRelateInfo(userShoppingOrderids.toString());
        userBillDetail.setOrderClass(75);
        userBillDetail.setCurrencyId(userAmount.getCurrencyId());
        int insertUserBillDetail = userBillDetailService.insertUserBillDetail(userBillDetail);
        if (insertUserBillDetail <= 0) {
            throw new ServiceException("系统繁忙");
        }

        //更新订单信息
        for (int i = 0; i < userShoppingOrders.size(); i++) {
            //订单信息
            UserShoppingOrder vo = userShoppingOrders.get(i);
            vo.setUpdateTime(new Date());
            vo.setSubmitPlatformFlag(1);
            int updateUserShoppingOrder = userShoppingOrderMapper.updateUserShoppingOrder(vo);
            if (updateUserShoppingOrder == 0){
                throw new LangException(HintConstants.SYSTEM_BUSY,"更新订单信息异常");
            }
        }
        return 1;
    }

    /**
     * 用户申请退款
     * @param userShoppingOrder
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int userApplyRefund(UserShoppingOrder userShoppingOrder){
        //订单信息ID
        Long id = userShoppingOrder.getId();
        //订单信息
        userShoppingOrder = userShoppingOrderMapper.selectUserShoppingOrderById(id);
        //订单状态
        Integer orderStatus = userShoppingOrder.getOrderStatus();
        //如果正在退款中
        if (orderStatus.equals(4)){
            if (!userShoppingOrder.getRefundStatus().equals(0)){
                throw new LangException(HintConstants.SYSTEM_BUSY,"订单非可退款状态");
            }
        }else {
            if (orderStatus.equals(0) || orderStatus.equals(3)){
                throw new LangException(HintConstants.SYSTEM_BUSY,"订单非可退款状态");
            }
        }
        //如果还未发货，直接退款成功
        if (orderStatus.equals(1)){
            userShoppingOrder.setRefundStatus(2);
            //用户ID
            Long userId = userShoppingOrder.getUserId();
            //返还购物金额
            BigDecimal orderPrice = userShoppingOrder.getOrderPrice();
            //用户钱包信息
            UserAmount userAmount = userAmountService.getUserAmount(userId, 3L);
            //用户余额变更前
            BigDecimal userAmountBefore = userAmount.getAmount();
            //用户余额变更前
            BigDecimal userAmountAfter = userAmountBefore.add(orderPrice);
            //更新用户余额
            userAmount.setAmount(userAmountAfter);
            int updateUserAmount = userAmountService.updateUserAmount(userAmount);
            if (updateUserAmount == 0){
                throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户余额异常");
            }
            //插入流水明细记录
            UserBillDetail userBillDetail = new UserBillDetail();
            userBillDetail.setUserId(userId);
            userBillDetail.setDeType("用户购物订单退款");
            userBillDetail.setDeSummary("用户购物订单退款");
            userBillDetail.setOrderAmount(orderPrice);
            userBillDetail.setOrderTime(new Date());
            userBillDetail.setAmountBefore(userAmountBefore);
            userBillDetail.setAmountAfter(userAmountAfter);
            userBillDetail.setRelateOrderId(userShoppingOrder.getId());
            userBillDetail.setOrderClass(78);
            userBillDetail.setCurrencyId(userAmount.getCurrencyId());
            int insertUserBillDetail = userBillDetailService.insertUserBillDetail(userBillDetail);
            if (insertUserBillDetail <= 0) {
                throw new LangException(HintConstants.SYSTEM_BUSY,"插入流水明细异常");
            }
        }else {
            //如果已发货或者订单已经完成，则退款需要申请
            userShoppingOrder.setRefundStatus(1);
        }
        userShoppingOrder.setOrderStatus(4);
        int updateUserShoppingOrder = userShoppingOrderMapper.updateUserShoppingOrder(userShoppingOrder);
        if (updateUserShoppingOrder == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新订单信息异常");
        }
        return 1;
    }

    /**
     * 后台购物订单退款审核
     * @param userShoppingOrder
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int refundReview(UserShoppingOrder userShoppingOrder){
        //订单id
        Long id = userShoppingOrder.getId();
        //审核状态
        Integer refundStatus = userShoppingOrder.getRefundStatus();
        //订单信息
        userShoppingOrder = userShoppingOrderMapper.selectUserShoppingOrderById(id);
        if (!userShoppingOrder.getOrderStatus().equals(4)){
            throw new ServiceException("该订单不处于售后状态");
        }
        if (!userShoppingOrder.getRefundStatus().equals(1)){
            throw new ServiceException("该订单状态不是退款中");
        }
        //如果同意退款
        if (refundStatus.equals(2)){
            //退钱
            //用户ID
            Long userId = userShoppingOrder.getUserId();
            //返还购物金额
            BigDecimal orderPrice = userShoppingOrder.getOrderPrice();
            //用户钱包信息
            UserAmount userAmount = userAmountService.getUserAmount(userId, 3L);
            //用户余额变更前
            BigDecimal userAmountBefore = userAmount.getAmount();
            //用户余额变更前
            BigDecimal userAmountAfter = userAmountBefore.add(orderPrice);
            //更新用户余额
            userAmount.setAmount(userAmountAfter);
            int updateUserAmount = userAmountService.updateUserAmount(userAmount);
            if (updateUserAmount == 0){
                throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户余额异常");
            }
            //插入流水明细记录
            UserBillDetail userBillDetail = new UserBillDetail();
            userBillDetail.setUserId(userId);
            userBillDetail.setDeType("用户购物订单退款");
            userBillDetail.setDeSummary("用户购物订单退款");
            userBillDetail.setOrderAmount(orderPrice);
            userBillDetail.setOrderTime(new Date());
            userBillDetail.setAmountBefore(userAmountBefore);
            userBillDetail.setAmountAfter(userAmountAfter);
            userBillDetail.setRelateOrderId(userShoppingOrder.getId());
            userBillDetail.setOrderClass(78);
            userBillDetail.setCurrencyId(userAmount.getCurrencyId());
            int insertUserBillDetail = userBillDetailService.insertUserBillDetail(userBillDetail);
            if (insertUserBillDetail <= 0) {
                throw new LangException(HintConstants.SYSTEM_BUSY,"插入流水明细异常");
            }
        }else if (refundStatus.equals(3)){
            //拒绝退款

        }
        userShoppingOrder.setRefundStatus(refundStatus);
        int updateUserShoppingOrder = userShoppingOrderMapper.updateUserShoppingOrder(userShoppingOrder);
        if (updateUserShoppingOrder <= 0) {
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新购物订单信息异常");
        }
        return 1;
    }
}
