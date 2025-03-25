package com.ruoyi.web.controller.system;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.UserInfo;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.mapper.SystemGoodsInfoMapper;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.service.ICategoryService;
import com.ruoyi.system.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/test")
public class TestControllerX extends BaseController {

    @Resource
    private SystemGoodsInfoMapper systemGoodsInfoMapper;

    @Resource
    private ICategoryService categoryService;

    @Autowired
    private ISkuService skuService;

//    @GetMapping("test")
//    public AjaxResult test(){
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        for (int i = 1; i < 1000; i++) {
//            PageUtils.startPage(i,50);
//            SystemGoodsInfo systemGoodsInfo1 = new SystemGoodsInfo();
//            systemGoodsInfo1.setDelFlag(1);
//            List<SystemGoodsInfo> systemGoodsInfos = systemGoodsInfoMapper.selectSystemGoodsInfoList(systemGoodsInfo1);
//            if (systemGoodsInfos.size() == 0){
//                break;
//            }
//            for (int j = 0; j < systemGoodsInfos.size(); j++) {
//                int finalJ = j;
//                executorService.execute(()->{
//                    SystemGoodsInfo systemGoodsInfo = systemGoodsInfos.get(finalJ);
//                    //商品图片
//                    String goodsImg = systemGoodsInfo.getGoodsImg();
//                    if (StringUtils.isNotEmpty(goodsImg)){
//                        if (goodsImg.startsWith("http")){
//                            //图片数组
//                            String[] imgsArr = systemGoodsInfo.getGoodsImg().split(",");
//                            List<String> newImgs = new ArrayList<>();
//                            for (int k = 0; k < imgsArr.length; k++) {
//                                try {
//                                    byte[] bytes = ImageUtils.readFile(imgsArr[k]);
//                                    String s = FileUtils.writeBytes(bytes, RuoYiConfig.getProfile()+"/goodsImg");
//                                    newImgs.add(s);
//                                } catch (Exception e) {
//
//                                }
//                            }
//                            if (newImgs.size() > 0){
//                                SystemGoodsInfo vo = new SystemGoodsInfo();
//                                vo.setId(systemGoodsInfo.getId());
//                                vo.setGoodsImg(newImgs.stream().collect(Collectors.joining(",")));
//                                systemGoodsInfoMapper.updateSystemGoodsInfo(vo);
//                            }
//                        }
//                    }
//                });
//            }
//        }
//        executorService.shutdown();
//        return AjaxResult.success();
//    }

//    @GetMapping("test")
//    public AjaxResult test(){
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        for (int i = 1; i < 10; i++) {
//            PageUtils.startPage(i,50);
//            Category category = new Category();
//            List<Category> categories = categoryService.selectCategoryList(category);
//            if (categories.size() == 0){
//                break;
//            }
//            for (int j = 0; j < categories.size(); j++) {
//                int finalJ = j;
//                executorService.execute(()->{
//                    Category category1 = categories.get(finalJ);
//                    //分类
//                    String img = category1.getCategoryImg();
//                    if (StringUtils.isNotEmpty(img)){
//                        if (img.startsWith("http")){
//                            try {
//                                byte[] bytes = ImageUtils.readFile(img);
//                                String s = FileUtils.writeBytes(bytes, RuoYiConfig.getProfile()+"/categoryImg");
//                                Category vo = new Category();
//                                vo.setId(category1.getId());
//                                vo.setCategoryImg(s);
//                                categoryService.updateCategory(vo);
//                            } catch (Exception e) {
//
//                            }
//                        }
//                    }
//                });
//            }
//        }
//        executorService.shutdown();
//        return AjaxResult.success();
//    }
//
//
//    private static final Logger log = LoggerFactory.getLogger(TestControllerX.class);
//
//    @GetMapping("addGoodsInfos")
//    public AjaxResult addGoodsInfos(){
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.execute(()->{
//            SpringUtils.getBean(TestServiceImpl.class).addGoodsInfos();
//        });
//        executorService.shutdown();
//        return AjaxResult.success();
//    }
//
//    @Autowired
//    private ITestService testService;
//
//    @GetMapping("getGoodsInfoDetail")
//    public AjaxResult getGoodsInfoDetail(){
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.execute(()->{
//            //获取所有商品信息
//            SystemGoodsInfo systemGoodsInfo = new SystemGoodsInfo();
//            systemGoodsInfo.setDelFlag(0);
//            List<SystemGoodsInfo> goodsInfos = systemGoodsInfoMapper.selectSystemGoodsInfoList(systemGoodsInfo);
//            ExecutorService executorService2 = Executors.newFixedThreadPool(300);
//            for (int i = 0; i < goodsInfos.size(); i++) {
//                SystemGoodsInfo vo = goodsInfos.get(i);
//                executorService2.execute(()->{
//                    testService.getGoodsInfoDetail(vo.getId());
//                });
//            }
//        });
//        executorService.shutdown();
//        return AjaxResult.success();
//    }
//
//    @GetMapping("getGoodsInfoDetail2")
//    public AjaxResult getGoodsInfoDetail2(){
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.execute(()->{
//            //获取所有商品信息
//            SystemGoodsInfo systemGoodsInfo = new SystemGoodsInfo();
//            systemGoodsInfo.setDelFlag(1);
//            List<SystemGoodsInfo> goodsInfos = systemGoodsInfoMapper.selectSystemGoodsInfoList(systemGoodsInfo);
//            ExecutorService executorService2 = Executors.newFixedThreadPool(300);
//            for (int i = 0; i < goodsInfos.size(); i++) {
//                SystemGoodsInfo vo = goodsInfos.get(i);
//                executorService2.execute(()->{
//                    testService.getGoodsInfoDetail2(vo.getId());
//                });
//            }
//        });
//        executorService.shutdown();
//        return AjaxResult.success();
//    }
//
//    @GetMapping("test3")
//    public AjaxResult test3(String goodsId){
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
//        return AjaxResult.success(goodsDetail);
//    }

    @GetMapping("test")
    public AjaxResult test(){
        for (int i = 0; i < 500; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserAccount(CodeUtils.generateOrderCode(""));
            userInfo.setInviteCode("R"+CodeUtils.generateInviteCode());
            userInfo.setNickName("robot");
            userInfo.setAccountType(2);
            userInfo.setUserPwd("robot");
            SpringUtils.getBean(UserInfoMapper.class).insertUserInfo(userInfo);
        }
        return AjaxResult.success();
    }


//    @GetMapping("test")
//    public AjaxResult test(){
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        List<Sku> skuList = skuService.selectSkuList(new Sku());
//        for (int i = 0; i < skuList.size(); i++) {
//            Sku sku = skuList.get(i);
//            executorService.execute(()->{
//                String skuImgs = sku.getSkuImgs();
//                if (StringUtils.isNotEmpty(skuImgs)){
//                    if (skuImgs.startsWith("http")){
//                        //图片数组
//                        String[] imgsArr = skuImgs.split(",");
//                        List<String> newImgs = new ArrayList<>();
//                        for (int k = 0; k < imgsArr.length; k++) {
//                            try {
//                                byte[] bytes = ImageUtils.readFile(imgsArr[k]);
//                                String s = FileUtils.writeBytes(bytes, RuoYiConfig.getProfile()+"/skuImg");
//                                newImgs.add(s);
//                            } catch (Exception e) {
//
//                            }
//                        }
//                        if (newImgs.size() > 0){
//                            Sku vo = new Sku();
//                            vo.setId(sku.getId());
//                            vo.setSkuImgs(newImgs.stream().collect(Collectors.joining(",")));
//                            skuService.updateSku(vo);
//                        }
//                    }
//                }
//            });
//        }
//        executorService.shutdown();
//        return AjaxResult.success();
//    }
}
