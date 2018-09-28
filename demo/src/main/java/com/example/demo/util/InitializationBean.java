
package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务系统初始化类<br> 
 *
 */
public class InitializationBean {
    // 系统初始化
    public static Map<String, Object> initMap = new HashMap<String, Object>();
    // activiti 最新流程id和流程名对应关系 用来提高后续访问效率
    public static Map<String, Object> activitiMap = new HashMap<String, Object>();
}
