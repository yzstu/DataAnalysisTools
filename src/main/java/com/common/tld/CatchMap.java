package com.common.tld;

import java.util.HashMap;
import java.util.Map;


/**
 * 缓存页面端查询各种NoName的键值对map
 *
 * @author linilq
 */
public class CatchMap {

    public static int catchType_UserIdName = 1;
    public static int catchType_ServiceMenu = 2;

    public static Map<Integer, String> serviceMenuIdNameMap = new HashMap<>();
    public static Map<Integer, String> UserIdNameMap = new HashMap<>();
    public static Map<Integer, String> GameNoNameMap = new HashMap<>();

    /**
     * 平台账号的Id与真实名称的缓存修改
     *
     * @param id
     * @param name
     */
    public static void updateIdContentMap(Integer id, String name, Integer relateType) {
        if (relateType == catchType_UserIdName) {
            if (UserIdNameMap.containsKey(id)) {
                UserIdNameMap.put(id, name);
            }
        } else if (relateType == catchType_ServiceMenu) {
            if (serviceMenuIdNameMap.containsKey(id)) {
                serviceMenuIdNameMap.put(id, name);
            }
        } else if (relateType == catchType_ServiceMenu) {
            if (GameNoNameMap.containsKey(id)) {
                GameNoNameMap.put(id, name);
            }
        }

    }
}
