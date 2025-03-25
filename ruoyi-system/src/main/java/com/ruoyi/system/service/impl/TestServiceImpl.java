//package com.ruoyi.system.service.impl;
//
//import cn.hutool.http.HttpRequest;
//import cn.hutool.http.HttpUtil;
//import com.alibaba.fastjson2.JSONArray;
//import com.alibaba.fastjson2.JSONObject;
//import com.ruoyi.common.exception.ServiceException;
//import com.ruoyi.common.utils.StringUtils;
//import com.ruoyi.system.domain.*;
//import com.ruoyi.system.mapper.*;
//import com.ruoyi.system.service.ITestService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.math.BigDecimal;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.stream.Collectors;
//
//@Service
//public class TestServiceImpl implements ITestService {
//
//    @Resource
//    private CategoryMapper categoryMapper;
//
//    @Resource
//    private SellingGoodsInfo2Mapper sellingGoodsInfo2Mapper;
//
//    @Resource
//    private AttrTypeMapper attrTypeMapper;
//
//    @Resource
//    private AttrValueMapper attrValueMapper;
//
//    @Resource
//    private SystemGoodsInfoMapper systemGoodsInfoMapper;
//
//    @Resource
//    private SkuMapper skuMapper;
//
//    /**
//     * 获取所有在售店铺商品
//     */
//    public void addGoodsInfos() {
//        Category category = new Category();
//        category.setCategoryLevel(2);
//        List<Category> categories = categoryMapper.selectCategoryList(category);
//        ExecutorService executorService = Executors.newFixedThreadPool(300);
//        for (int i = 0; i < categories.size(); i++) {
//            //分类id
//            String id = categories.get(i).getId();
//            Integer pageSize = 50;
//            Map<String, Object> map = addGoodsInfo(id, 1, pageSize);
//            Integer totalPage = Integer.valueOf(map.get("totalPage").toString());
//            for (int j = 2; j <= totalPage; j++) {
//                Integer k = j;
//                executorService.execute(()->{
//                    addGoodsInfo(id,k,pageSize);
//                });
//            }
//        }
//        executorService.shutdown();
//    }
//
//    public Map<String,Object> addGoodsInfo(String categoryId, Integer pageNum, Integer pageSize) {
//        //根据分类获取商品信息
//        String baseUrl = "https://ttsco24h.shop/wap/api/sellerGoods!list.action?pageNum="+pageNum+"&pageSize="+pageSize+"&categoryId="+categoryId+"&lang=en";
//        //创建请求
//        HttpRequest post = HttpUtil.createPost(baseUrl);
////        //超时时间3秒
////        post.timeout(5000);
//        //传参
//        JSONObject params = new JSONObject();
//        params.put("pageNum", pageNum);
//        params.put("pageSize", pageSize);
//        params.put("categoryId", categoryId);
//        params.put("lang", "en");
//        post.body(JSONObject.toJSONString(params), "application/json");
//        //字符编码
//        post.charset(StandardCharsets.UTF_8);
//        //发送请求并获取响应
//        String responseString = post.execute().body();
//        //解析响应
//        JSONObject responseJsonObject = JSONObject.parseObject(responseString);
//        //商品数据
//        JSONArray goodsData = responseJsonObject.getJSONObject("data").getJSONArray("pageList");
//        //goodsInfos-new
//        List<SellingGoodsInfo2> goodsInfos = new ArrayList<>();
//        //获取数据
//        for (int i = 0; i < goodsData.size(); i++) {
//            //商品信息
//            JSONObject obj = goodsData.getJSONObject(i);
//            //商品ID
//            String id = obj.getString("id");
//            //商品名称
//            String name = obj.getString("name");
//            //商品编码
//            String goodsCode = obj.getString("goodsId");
//            //描述
//            String des = obj.getString("des");
//            //价格
//            BigDecimal sellingPrice = obj.getBigDecimal("sellingPrice");
//            //系统价格
//            BigDecimal systemPrice = obj.getBigDecimal("systemPrice");
//            //分类ID
//            String categoryIdVo = obj.getString("categoryId");
//            //第二分类
//            String secondaryCategoryId = obj.getString("secondaryCategoryId");
//            if (StringUtils.isNotEmpty(secondaryCategoryId))
//            categoryIdVo = secondaryCategoryId;
//            if (!categoryIdVo.equals(categoryId)) {
//                continue;
//            }
//            //价格
//            BigDecimal freightAmount = obj.getBigDecimal("freightAmount");
//            //商家ID
//            String sellerId = obj.getString("sellerId");
//
//            String imgUrl1 = obj.getString("imgUrl1");
//            String imgUrl2 = obj.getString("imgUrl2");
//            String imgUrl3 = obj.getString("imgUrl3");
//            String imgUrl4 = obj.getString("imgUrl4");
//            String imgUrl5 = obj.getString("imgUrl5");
//            String imgUrl6 = obj.getString("imgUrl6");
//            String imgUrl7 = obj.getString("imgUrl7");
//            String imgUrl8 = obj.getString("imgUrl8");
//            String imgUrl9 = obj.getString("imgUrl9");
//            String imgUrl10 = obj.getString("imgUrl10");
//            List<String> imgs = new ArrayList<>();
//            imgs.add(imgUrl1);
//            imgs.add(imgUrl2);
//            imgs.add(imgUrl3);
//            imgs.add(imgUrl4);
//            imgs.add(imgUrl5);
//            imgs.add(imgUrl6);
//            imgs.add(imgUrl7);
//            imgs.add(imgUrl8);
//            imgs.add(imgUrl9);
//            imgs.add(imgUrl10);
//            String imgsStr = imgs.stream().filter(a -> StringUtils.isNotEmpty(a)).collect(Collectors.joining(","));
//
//            SellingGoodsInfo2 sellingGoodsInfo2 = new SellingGoodsInfo2();
//            sellingGoodsInfo2.setId(id);
//            sellingGoodsInfo2.setGoodsCode(goodsCode);
//            sellingGoodsInfo2.setGoodsNameEn(name);
//            sellingGoodsInfo2.setGoodsImg(imgsStr);
//            sellingGoodsInfo2.setGoodsDescEn(des);
//            sellingGoodsInfo2.setSellingPrice(sellingPrice);
//            sellingGoodsInfo2.setSystemPrice(systemPrice);
//            sellingGoodsInfo2.setCategoryId(categoryIdVo);
//            sellingGoodsInfo2.setFreightAmount(freightAmount);
//            sellingGoodsInfo2.setSellerId(sellerId);
//            goodsInfos.add(sellingGoodsInfo2);
//        }
//        if (goodsInfos.size() > 0){
//            sellingGoodsInfo2Mapper.insertSellingGoodsInfo2s(goodsInfos);
//        }
//        //result
//        Map<String, Object> result = new HashMap<>();
//        //totalPageNum
//        result.put("totalPage",responseJsonObject.getJSONObject("data").getJSONObject("pageInfo").get("totalPage"));
//        result.put("pageNum",pageNum);
//        return result;
//    }
//
//    /**
//     * 获取商品详情
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void getGoodsInfoDetail(String goodsId){
//        //根据商品ID获取商品信息
//        String baseUrl = "https://ttsco24h.shop/wap/api/sellerGoods!info.action?sellerGoodsId="+goodsId+"&lang="+"en";
//        //创建请求
//        HttpRequest post = HttpUtil.createPost(baseUrl);
//        //传参
//        JSONObject params = new JSONObject();
//        params.put("sellerGoodsId", goodsId);
//        params.put("lang", "en");
//        post.body(JSONObject.toJSONString(params), "application/json");
//        //字符编码
//        post.charset(StandardCharsets.UTF_8);
//        //发送请求并获取响应
//        String responseString = post.execute().body();
//        //解析响应
//        JSONObject responseJsonObject = JSONObject.parseObject(responseString);
//        //商品数据
//        JSONObject goodsDetail = responseJsonObject.getJSONObject("data");
//        //商品编码
//        String goodsCode = goodsDetail.getString("goodsId");
//        //canSelectAttributes
//        JSONObject canSelectAttributes = goodsDetail.getJSONObject("canSelectAttributes");
//        //如果商品有属性
//        if (canSelectAttributes != null){
//            //商品属性
//            JSONArray goodAttrs = canSelectAttributes.getJSONArray("goodAttrs");
//            if (goodAttrs != null){
//                //属性列表
//                List<AttrType> attrTypeList = new ArrayList<>();
//                //属性值列表
//                List<AttrValue> attrValueList = new ArrayList<>();
//                //商品属性
//                for (int i = 0; i < goodAttrs.size(); i++) {
//                    //属性信息
//                    JSONObject obj = goodAttrs.getJSONObject(i);
//                    //属性ID
//                    String attrId = obj.getString("attrId");
//                    //属性名称(多语言)
//                    String attrName = obj.getString("attrName");
//                    //new attrType
//                    AttrType attrType = new AttrType();
//                    attrType.setId(attrId);
//                    attrType.setGoodsCode(goodsCode);
//                    attrType.setAttrTypeNameEn(attrName);
//                    attrTypeList.add(attrType);
//                    //属性值
//                    JSONArray attrValues = obj.getJSONArray("attrValues");
//                    if (attrValues != null){
//                        for (int j = 0; j < attrValues.size(); j++) {
//                            //属性值信息
//                            JSONObject objValue = attrValues.getJSONObject(j);
//                            //属性值ID
//                            String attrValueId = objValue.getString("attrValueId");
//                            //属性值名称(多语言)
//                            String attrValueName = objValue.getString("attrValueName");
//                            //图标
//                            String iconImg = objValue.getString("iconImg");
//                            //new attrValue
//                            AttrValue attrValue = new AttrValue();
//                            attrValue.setId(attrValueId);
//                            attrValue.setAttrValueNameEn(attrValueName);
//                            attrValue.setAttrImg(iconImg);
//                            attrValue.setAttrTypeId(attrId);
//                            attrValue.setGoodsCode(goodsCode);
//                            attrValueList.add(attrValue);
//                        }
//                    }
//                }
//                if (attrTypeList.size() > 0){
//                    //检验是否重复数据
//                    AttrType attrType = new AttrType();
//                    attrType.getParams().put("ids",attrTypeList.stream().map(a->a.getId()).collect(Collectors.toList()));
//                    List<AttrType> attrTypes = attrTypeMapper.selectAttrTypeList(attrType);
//                    List<String> attrTypesIds = attrTypes.stream().map(a -> a.getId()).collect(Collectors.toList());
//                    List<AttrType> collect1 = attrTypeList.stream().filter(a -> !attrTypesIds.contains(a.getId())).collect(Collectors.toList());
//                    if (collect1.size() > 0){
//                        int insertAttrTypes = attrTypeMapper.insertAttrTypes(collect1);
//                        if (insertAttrTypes != collect1.size()){
//                            throw new ServiceException();
//                        }
//                    }
//                }
//                if (attrValueList.size() > 0){
//                    //检验是否重复数据
//                    AttrValue attrValue = new AttrValue();
//                    attrValue.getParams().put("ids",attrValueList.stream().map(a->a.getId()).collect(Collectors.toList()));
//                    List<AttrValue> attrValues = attrValueMapper.selectAttrValueList(attrValue);
//                    List<String> attrValuesIds = attrValues.stream().map(a -> a.getId()).collect(Collectors.toList());
//                    List<AttrValue> collect2 = attrValueList.stream().filter(a -> !attrValuesIds.contains(a.getId())).collect(Collectors.toList());
//                    if (collect2.size() > 0){
//                        int insertAttrValues = attrValueMapper.insertAttrValues(collect2);
//                        if (insertAttrValues != collect2.size()){
//                            throw new ServiceException();
//                        }
//                    }
//                }
//            }
//            //sku
//            JSONArray skus = canSelectAttributes.getJSONArray("skus");
//            if (skus != null){
//                //skuList
//                List<Sku> skuList = new ArrayList<>();
//                //商品属性
//                for (int i = 0; i < skus.size(); i++) {
//                    //sku信息
//                    JSONObject sku = skus.getJSONObject(i);
//                    //skuId
//                    String skuId = sku.getString("skuId");
//                    //价格
//                    BigDecimal price = sku.getBigDecimal("price");
//                    //出售价格
//                    BigDecimal sellingPrice = sku.getBigDecimal("sellingPrice");
//                    //属性
//                    JSONArray attrs = sku.getJSONArray("attrs");
//                    //属性值IDS
//                    String attrValueIdsStr;
//                    if (attrs != null){
//                        attrValueIdsStr = attrs.toList(JSONObject.class).stream().map(a->a.getString("attrValueId")).collect(Collectors.joining(","));
//                    }else {
//                        throw new ServiceException();
//                    }
//                    //skuImgs
//                    JSONArray skuImgs = sku.getJSONArray("img");
//                    //skuImgs
//                    String skuImgsStr = null;
//                    if (skuImgs != null){
//                        skuImgsStr = skuImgs.toList(String.class).stream().collect(Collectors.joining(","));
//                    }
//                    Sku skuVo = new Sku();
//                    skuVo.setId(skuId);
//                    skuVo.setGoodsId(goodsId);
//                    skuVo.setGoodsCode(goodsCode);
//                    skuVo.setAttrValueId(attrValueIdsStr);
//                    skuVo.setGoodsPrice(sellingPrice);
//                    skuVo.setSystemPrice(price);
//                    skuVo.setSkuImgs(skuImgsStr);
//                    skuList.add(skuVo);
//                }
//                if (skuList.size() > 0){
//                    //检验数据是否重复
//                    Sku sku = new Sku();
//                    sku.getParams().put("ids",skuList.stream().map(a->a.getId()).collect(Collectors.toList()));
//                    List<Sku> skusVo = skuMapper.selectSkuList(sku);
//                    List<String> skusIds = skusVo.stream().map(a -> a.getId()).collect(Collectors.toList());
//                    List<Sku> collect3 = skuList.stream().filter(a -> !skusIds.contains(a.getId())).collect(Collectors.toList());
//                    if (collect3.size() > 0){
//                        int insertSkus = skuMapper.insertSkus(collect3);
//                        if (insertSkus != collect3.size()){
//                            throw new ServiceException();
//                        }
//                    }
//                }
//            }
//        }
//        //更新系统价格和在售价格
//        SystemGoodsInfo goodsInfo = new SystemGoodsInfo();
//        goodsInfo.setId(goodsId);
//        goodsInfo.setDelFlag(1);
//        int updateGoodsInfo = systemGoodsInfoMapper.updateSystemGoodsInfo(goodsInfo);
//        if (updateGoodsInfo <= 0){
//            throw new ServiceException();
//        }
//    }
//
//    /**
//     * 获取商品详情
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void getGoodsInfoDetail2(String goodsId){
//        //根据商品ID获取商品信息
//        String baseUrl = "https://ttsco24h.shop/wap/api/sellerGoods!info.action?sellerGoodsId="+goodsId+"&lang="+"cn";
//        //创建请求
//        HttpRequest post = HttpUtil.createPost(baseUrl);
//        //传参
//        JSONObject params = new JSONObject();
//        params.put("sellerGoodsId", goodsId);
//        params.put("lang", "cn");
//        post.body(JSONObject.toJSONString(params), "application/json");
//        //字符编码
//        post.charset(StandardCharsets.UTF_8);
//        //发送请求并获取响应
//        String responseString = post.execute().body();
//        //解析响应
//        JSONObject responseJsonObject = JSONObject.parseObject(responseString);
//        //商品数据
//        JSONObject goodsDetail = responseJsonObject.getJSONObject("data");
//        //商品名称(多语言)
//        String name = goodsDetail.getString("name");
//        //描述(多语言)
//        String des = goodsDetail.getString("des");
//        //canSelectAttributes
//        JSONObject canSelectAttributes = goodsDetail.getJSONObject("canSelectAttributes");
//        //如果商品有属性
//        if (canSelectAttributes != null){
//            //商品属性
//            JSONArray goodAttrs = canSelectAttributes.getJSONArray("goodAttrs");
//            if (goodAttrs != null){
//                //属性列表
//                List<AttrType> attrTypeList = new ArrayList<>();
//                //属性值列表
//                List<AttrValue> attrValueList = new ArrayList<>();
//                //商品属性
//                for (int i = 0; i < goodAttrs.size(); i++) {
//                    //属性信息
//                    JSONObject obj = goodAttrs.getJSONObject(i);
//                    //属性ID
//                    String attrId = obj.getString("attrId");
//                    //属性名称(多语言)
//                    String attrName = obj.getString("attrName");
//                    //new attrType
//                    AttrType attrType = new AttrType();
//                    attrType.setId(attrId);
//                    attrType.setAttrTypeName(attrName);
//                    attrTypeList.add(attrType);
//                    //属性值
//                    JSONArray attrValues = obj.getJSONArray("attrValues");
//                    if (attrValues != null){
//                        for (int j = 0; j < attrValues.size(); j++) {
//                            //属性值信息
//                            JSONObject objValue = attrValues.getJSONObject(j);
//                            //属性值ID
//                            String attrValueId = objValue.getString("attrValueId");
//                            //属性值名称(多语言)
//                            String attrValueName = objValue.getString("attrValueName");
//                            //图标
//                            String iconImg = objValue.getString("iconImg");
//                            //new attrValue
//                            AttrValue attrValue = new AttrValue();
//                            attrValue.setId(attrValueId);
//                            attrValue.setAttrValueName(attrValueName);
//                            attrValue.setAttrImg(iconImg);
//                            attrValue.setAttrTypeId(attrId);
//                            attrValueList.add(attrValue);
//                        }
//                    }
//                }
//                if (attrTypeList.size() > 0){
//                    List<String> attrTypeIds = attrTypeList.stream().map(a -> a.getId()).collect(Collectors.toList());
//                    Map<String, AttrType> map = attrTypeList.stream().collect(Collectors.toMap(a -> a.getId(), a -> a));
//                    AttrType attrType = new AttrType();
//                    attrType.getParams().put("ids",attrTypeIds);
//                    List<AttrType> attrTypes = attrTypeMapper.selectAttrTypeList(attrType);
//                    for (int i = 0; i < attrTypes.size(); i++) {
//                        AttrType vo = attrTypes.get(i);
//                        vo.setAttrTypeName(map.get(vo.getId()).getAttrTypeName());
//                        int updateAttrType = attrTypeMapper.updateAttrType(vo);
//                        if(updateAttrType <= 0){
//                            throw new ServiceException();
//                        }
//                    }
//                }
//                if (attrValueList.size() > 0){
//                    List<String> attrValueIds = attrValueList.stream().map(a -> a.getId()).collect(Collectors.toList());
//                    Map<String, AttrValue> map = attrValueList.stream().collect(Collectors.toMap(a -> a.getId(), a -> a));
//                    AttrValue attrValue = new AttrValue();
//                    attrValue.getParams().put("ids",attrValueIds);
//                    List<AttrValue> attrValues = attrValueMapper.selectAttrValueList(attrValue);
//                    for (int i = 0; i < attrValues.size(); i++) {
//                        AttrValue vo = attrValues.get(i);
//                        vo.setAttrValueName(map.get(vo.getId()).getAttrValueName());
//                        int updateAttrValue = attrValueMapper.updateAttrValue(vo);
//                        if(updateAttrValue <= 0){
//                            throw new ServiceException();
//                        }
//                    }
//                }
//            }
//        }
//        //更新系统价格和在售价格
//        SystemGoodsInfo goodsInfo = new SystemGoodsInfo();
//        goodsInfo.setId(goodsId);
//        LangMgr goodsNameLang = new LangMgr();
//        LangMgr goodsDescLang = new LangMgr();
//        goodsNameLang.setZh(name);
//        goodsDescLang.setZh(des);
//        goodsInfo.setGoodsNameLang(goodsNameLang);
//        goodsInfo.setGoodsDescLang(goodsDescLang);
//        goodsInfo.setDelFlag(2);
//        int updateGoodsInfo = systemGoodsInfoMapper.updateSystemGoodsInfo(goodsInfo);
//        if (updateGoodsInfo <= 0){
//            throw new ServiceException();
//        }
//    }
//}