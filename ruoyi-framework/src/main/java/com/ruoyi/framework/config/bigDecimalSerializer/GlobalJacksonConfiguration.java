package com.ruoyi.framework.config.bigDecimalSerializer;//package com.ruoyi.framework.config.bigDecimalSerializer;
//
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.math.BigDecimal;
//import java.util.List;
//
///**
// * BigDecimal序列化全局配置
// */
//@Configuration
//public class GlobalJacksonConfiguration extends WebMvcConfigurationSupport {
//    @Override
//    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        SimpleModule module = new SimpleModule();
//
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
////        //BigDecimal反序列化
////        module.addDeserializer(BigDecimal.class, new BigDecimalDeserializer());
//        //BigDecimal序列化
//        module.addSerializer(BigDecimal.class,new BigDecimalSerializer());
//        converter.getObjectMapper().registerModule(module);
//        converters.add(converter);
//        super.configureMessageConverters(converters);
//    }
//}