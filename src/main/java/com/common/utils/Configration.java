package com.common.utils;

import java.util.ResourceBundle;

public class Configration {
    private static Object lock = new Object();
    private static Configration config = null;
    private static ResourceBundle rb = null;

    private Configration(String filename) {
        rb = ResourceBundle.getBundle(filename);
    }


    public static Configration getInstance(String filename) {
        synchronized (lock) {
            if (null == config) {
                config = new Configration(filename);
            }
        }
        return (config);
    }

    public String getValue(String key) {
        String ret = "";
        if (rb.containsKey(key)) {
            ret = rb.getString(key);
        }
        return ret;
    }
}
