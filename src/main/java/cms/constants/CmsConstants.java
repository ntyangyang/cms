package cms.constants;

import java.util.HashMap;
import java.util.Map;

public class CmsConstants{
    
    /** 属性值类型*/
    public static final String STRING_NORMAL = "STRING_NORMAL";
    
    public static final String STRING_URL = "STRING_URL";
    
    public static final String INTEGER = "INTEGER";
    
    public static final String TIMESTAMP = "TIMESTAMP";
    
    /**属性类型*/
    public static final String NORMAL_INPUT = "NORMAL_INPUT";
    
    public static final String CHECK_RADIO = "CHECK_RADIO";
    
    public static final String PULL_DOWN_SELECT = "PULL_DOWN_SELECT";
    
    public static final String IMAGE_SELECT = "IMAGE_SELECT";
    
    public static final String VIDEO_SELECT = "VIDEO_SELECT";
    
    public static final String QUOTE_COMPONENT_SELECT = "QUOTE_COMPONENT_SELECT";
    
    /**页面类型*/
    public static final String HOMEPAGE = "HOMEPAGE";
    
    public static final String CATEGORY = "CATEGORY";

    public static final String LOOKS = "LOOKS";

    public static final String SHOWS = "SHOWS";
    
    /**缓存键值*/
    public static final String CMS_INSTANCE_URLS = "key_cms_instance_urls";
    
    public static final String CMS_INSTANCE_DATA_PREFIX = "key_cms_instance_data_prefix";
    
    public static final int CMS_INSTANCE_EXPIRE_TIME = 1*60*60;
    
    public static Map<String,String> propertyTypeMap = new HashMap<String,String>();
    
    public static Map<String,String> propertyParamTypeMap = new HashMap<String,String>();
    
    static{
        propertyParamTypeMap.put(STRING_NORMAL, "普通字符");
        propertyParamTypeMap.put(STRING_URL, "链接地址");
        propertyParamTypeMap.put(INTEGER, "数字");
        propertyParamTypeMap.put(TIMESTAMP, "时间格式");
        
        propertyTypeMap.put(NORMAL_INPUT, "一般文本输入");
        propertyTypeMap.put(VIDEO_SELECT, "多语言文本输入");
        propertyTypeMap.put(CHECK_RADIO, "单选按钮");
        propertyTypeMap.put(PULL_DOWN_SELECT, "下拉选项");
        propertyTypeMap.put(IMAGE_SELECT, "图片选择");
        propertyTypeMap.put(QUOTE_COMPONENT_SELECT, "引用组件选择");
    }
}
