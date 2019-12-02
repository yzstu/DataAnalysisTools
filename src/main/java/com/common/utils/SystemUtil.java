package com.common.utils;

/**
 * 系统判断工具类
 *
 * @author linilq
 */
public class SystemUtil {

    /**
     * 判断系统时win还是linux
     *
     * @return
     */
    public static boolean isLinux() {
        String name = System.getProperty("os.name");
        if (name.toLowerCase().startsWith("win"))
            return false;
        else
            return true;
    }
}
