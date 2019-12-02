package com.common.bean;

import com.common.utils.Configration;

public interface Constants {
    /**
     * windows _excel上传路径
     */
    public static String UploadPathWindow = "D:\\upload\\";
    /**
     *
     */
    public static String UploadPathLinux = "/home/data/linuxLog/upload/";

    /*
     * 默认游戏版本号
     */
    public static String DEFAULT_GAME_VERSION = "2";

    /**
     * 标识响应计费请求的tomcat序号，保证订单号生成不重复
     */
    public static String SERVERID = "1";

    String SESSION_LOGIN_USER = "SESSION_USER";

    String SESSION_MENUS = "SESSION_MENUS";

    String SESSION_ANNOUNCEMENT = "SESSION_ANNOUNCEMENT";

    int PAGE_SIZE = 25;

    String CSV_CONTENT = "CSV_CONTENT";

    String CSV_FILE_NAME = "CSV_FILE_NAME";

    String CSV_CONTENT_TYPE = "text/csv";

    CsvView CSV_VIEW = new CsvView();

    String EXCEL_FILE_NAME = "EXCEL_FILE_NAME";

    String EXCEL_FILE_PATH = "EXCEL_FILE_PATH";

    String EXCEL_SHEET_NAME = "EXCEL_SHEET_NAME";

    String EXCEL_CONTENT = "EXCEL_CONTENT";

    String EXCEL_CONTENT_TITLE_ROW = "row";

    String EXCEL_CONTENT_TITLE_COLUMN = "column";

    ExcelView EXCEL_VIEW = new ExcelView();

    ExcelFileView EXCEL_FILE_VIEW = new ExcelFileView();

    String JSON_ROOT = "JSON_ROOT";

    JSONView JSON_VIEW = new JSONView();

    String SECRET_KEY = "2013";

    String ONLINE = "online";

    String OFFLINE = "offline";

    String TEMP_PREFIX = "temp_";

    Integer STATIS_TYPE_CHANNEL = 0;

    Integer STATIS_TYPE_SDK = 1;

    Long DEV_ADMIN_ROLE_ID = 2L; // 开发者角色ID

    Long SUPER_ADMIN_ROLE_ID = 1L; // 管理员角色ID

    Integer LIST_PAGESIZE = 30;

    Integer CHANNEL_LIST_PAGESIZE = 10;

    String LOGINUSER_TYPE = "LOGINUSER_TYPE";


    static final String LinuxLog = "/home/data/linuxLog/server" + Configration.getInstance("config").getValue("serverId") + "/";
    static final String winLog = "D:/winLog/server" + Configration.getInstance("config").getValue("serverId") + "/";

    //用来接收通知的邮箱
    String[] Email = new String[]{"1240594179@qq.com", "496654180@qq.com", "DikeyWang@163.com"};

}
