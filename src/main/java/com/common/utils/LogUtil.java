package com.common.utils;

import com.common.bean.Constants;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LogUtil {

    public static LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(Integer.MAX_VALUE);
    public static ThreadPoolExecutor excecutor = new ThreadPoolExecutor(1, 1, 1000l, TimeUnit.MILLISECONDS, queue);

    public static void write2file(final String str, final String fileName) {
        String isDebug = Configration.getInstance("config").getValue("isDeBug");
        final String dateD = DateUtil.getDate() + "/";
        if (StringUtil.checkNotNull(isDebug) && isDebug.equals("1")) {
            excecutor.execute(new Runnable() {

                @Override
                public void run() {
                    if (SystemUtil.isLinux()) {
                        checkDirectory(Constants.LinuxLog + dateD);
                        FileUtil.write(Constants.LinuxLog + dateD + fileName + ".txt",
                                DateUtil.getDateTime() + ":" + str + "\r\n");
                    } else {
                        checkDirectory(Constants.winLog + dateD);
                        FileUtil.write(Constants.winLog + dateD + fileName + ".txt",
                                DateUtil.getDateTime() + ":" + str + "\r\n");
                    }
                }
            });

        }
    }

    public static void checkDirectory(String path) {
        File file = new File(path);
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
    }

    /**
     * 打印异常调用栈
     *
     * @param e
     * @param fileName
     */
    public static void write2file(final Exception e, final String fileName) {
        String isDebug = Configration.getInstance("config").getValue("isDeBug");
        if (StringUtil.checkNotNull(isDebug) && isDebug.equals("1")) {
            excecutor.execute(new Runnable() {

                @Override
                public void run() {
                    String stackTrace = e.getMessage() + "\r\n";
                    for (int i = 0; i < e.getStackTrace().length; i++) {
                        stackTrace = stackTrace + e.getStackTrace()[i] + "\r\n";
                    }
                    if (SystemUtil.isLinux()) {
                        FileUtil.write(Constants.LinuxLog + fileName + DateUtil.getDate() + ".txt",
                                DateUtil.getDateTime() + ":" + stackTrace + "\r\n");
                    } else {

                        FileUtil.write(Constants.winLog + fileName + DateUtil.getDate() + ".txt",
                                DateUtil.getDateTime() + ":" + stackTrace + "\r\n");
                    }
                }
            });

        }

    }

    public static void shutDown() {
        if (excecutor.isShutdown()) {
        } else {
            excecutor.shutdown();
        }

    }

}
