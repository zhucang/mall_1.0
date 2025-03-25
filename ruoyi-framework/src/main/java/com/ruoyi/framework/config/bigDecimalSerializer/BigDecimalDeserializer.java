package com.ruoyi.framework.config.bigDecimalSerializer;//package com.ruoyi.framework.config.bigDecimalSerializer;//package com.ruoyi.framework.config.bigDecimalSerializer;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.ruoyi.common.utils.StringUtils;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.function.Predicate;
//import java.util.regex.Pattern;
//
///**
// * BigDecimal反序列化
// */
//public class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {
//    public static final String PATTERN = "^[0-9]*\\.?[0-9]{0,2}$";
//    public static final Predicate<String> PATTERN_BIG_DECIMAL = Pattern.compile(PATTERN).asPredicate();
//    @Override
//    public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
//        String s = jsonParser.readValueAs(String.class);
//        if (StringUtils.isBlank(s))  return null;
//        if (PATTERN_BIG_DECIMAL.test(s)) {
//            return new BigDecimal(s);
//        }
//        throw new RuntimeException("BigDecimal格式错误");
//    }
//}
