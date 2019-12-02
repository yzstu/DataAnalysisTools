package com.common.utils;

import org.apache.commons.lang.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 公共方法类
 * </p>
 * <p>
 * 提供字符串处理的实用方法集
 * </p>
 *
 * @author Baldwin
 * @version 1.0
 */

public class StringUtil {
    public StringUtil() {
    }

    public static final String escapeForIntro(String string) {
        // String str = escapeHTMLTags(string);
        String str = string;
        str = replace(str, "\r\n", "<br>");
        str = replace(str, "\n", "<br>");
        str = replace(str, "'", "\\'");
        return replace(str, "\r", "");

    }

    /**
     * 得到非空的字符串，若字符串对象为null，则返回""。
     *
     * @param objValue Object待转换的原字符串对象
     * @return String 转换后的字符串
     */
    public static String getNotNullStr(Object objValue) {
        return (objValue == null ? "" : objValue.toString());
    }

    /**
     * 得到非空的字符串，若字符串为null，则返回""。
     *
     * @param strValue String待转换的原字符串
     * @return String 转换后的字符串
     */
    public static String getNotNullStr(String strValue) {
        return (strValue == null ? "" : strValue.trim());
    }


    /**
     * 用"0"补足一个字符串到指定长度
     *
     * @param str  - 源字符串
     * @param size - 补足后应达到的长度
     * @return - 补零后的结果
     */
    public static String fillZero(String str, int size) {
        String result;
        if (str.length() < size) {
            char[] s = new char[size - str.length()];
            for (int i = 0; i < (size - str.length()); i++) {
                s[i] = '0';
            }
            result = new String(s) + str;
        } else {
            result = str;
        }
        return result;
    }

    /**
     * 根据字符串（文件类型或者多行输入类型）获取字符串数组
     *
     * @param str1 String 输入字符串
     * @return String[] 返回结果
     */
    public static String[] getStrArryByString(String str1) {
        if (str1.indexOf("\t") > 0) {
            for (int i = 0; i < str1.length(); i++) {
                if (str1.substring(i, i + 1).equals("\t")) {
                    str1 = str1.substring(0, i) + " " + str1.substring(i + 1, str1.length());
                }
            }
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str1, "\r\n");
        String[] strId = new String[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            strId[i] = stringTokenizer.nextToken();
            i++;
        }
        return strId;
    }

    /**
     * 判断一个字符串是否为 NUll 或为空
     *
     * @param inStr inStr
     * @return boolean
     */
    public static boolean isValid(String inStr) {
        if (inStr == null) {
            return false;
        } else if (inStr.equals("")) {
            return false;
        } else if (inStr.equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断一个字符串是否为 NUll 或为空
     *
     * @param str inStr
     * @return boolean
     */
    public static boolean checkNotNull(String str) {
        boolean flag = false;

        if (str != null && str.trim().length() != 0)
            flag = true;
        return flag;
    }

    /**
     * 获得指定长度的空格
     *
     * @param spaceNum spaceNum
     * @return String
     */
    public static String getStrSpace(int spaceNum) {
        return getStrWithSameElement(spaceNum, " ");
    }

    /**
     * 获得指定长度的字符串
     *
     * @param num     int
     * @param element String
     * @return String
     */
    public static String getStrWithSameElement(int num, String element) {
        if (num <= 0) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            sb.append(element);
        }
        return sb.toString();
    }

    /**
     * 从右或左加空格
     *
     * @param strIn        String
     * @param totalLength  int
     * @param isRightAlign boolean
     * @return String
     */
    public static String getFillString(String strIn, int totalLength, boolean isRightAlign) {
        int spaceLength = 0;
        String spaces = null;
        String strOut = null;

        if (strIn == null) {
            strIn = "";
        }

        spaceLength = totalLength - strIn.length();

        if (spaceLength < 0) {
            spaceLength = 0;
        }
        spaces = StringUtil.getStrSpace(spaceLength);

        if (isRightAlign) {
            strOut = spaces + strIn;
        } else {
            strOut = strIn + spaces;

        }
        return strOut;
    }

    /**
     * 以String类型返回错误抛出的堆栈信息
     *
     * @param t Throwable
     * @return String
     */
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        t.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * 转换字符串第一个字符为大写
     *
     * @param str String
     * @return String
     */
    public static String getStrByUpperFirstChar(String str) {
        try {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 转换字符串第一个字符为小写
     *
     * @param str String
     * @return String
     */
    public static String getStrByLowerFirstChar(String str) {
        try {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 通过字符串转换成相应的整型，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return int 转换完成的整型
     */
    public static int getStrToInt(String strValue) {
        if (null == strValue) {
            return 0;
        }
        int iValue = 0;
        try {
            iValue = new Integer(strValue.trim()).intValue();
        } catch (Exception ex) {
            iValue = 0;
        }
        return iValue;
    }

    /**
     * 通过字符串转换成相应的DOUBLE，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return double 转换完成的DOUBLE
     */
    public static double getStrToDouble(String strValue) {
        if (null == strValue) {
            return 0;
        }
        double dValue = 0;
        try {
            dValue = Double.parseDouble(strValue.trim());
        } catch (Exception ex) {
            dValue = 0;
        }
        return dValue;
    }

    /**
     * 通过字符串转换成相应的短整型，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return short 转换完成的短整型
     */
    public static short getStrToShort(String strValue) {
        if (null == strValue) {
            return 0;
        }
        short iValue = 0;
        try {
            iValue = new Short(strValue.trim()).shortValue();
        } catch (Exception ex) {
            iValue = 0;
        }
        return iValue;
    }

    /**
     * 通过字符串转换成相应的长整型，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return long 转换完成的长整型
     */
    public static long getStrToLong(String strValue) {
        if (null == strValue) {
            return 0;
        }
        long lValue = 0;
        try {
            lValue = new Long(strValue.trim()).longValue();
        } catch (Exception ex) {
            lValue = 0;
        }
        return lValue;
    }

    public static String toLengthForEn(String str, int length) {
        if (null != str) {
            if (str.length() <= length) {
                return str;
            } else {
                str = str.substring(0, length - 2);
                str = str + "..";
                return str;
            }
        } else {
            return "";
        }
    }

    /**
     * 降字符串转换成给定长度的字符串，如超出的话截断，并在最后以两个点结尾
     *
     * @param str    String
     * @param length int
     * @return String
     */
    public static String toLengthForIntroduce(String str, int length) {
        str = delTag(str);

        byte[] strByte = str.getBytes();
        int byteLength = strByte.length;
        char[] charArray;
        StringBuffer buff = new StringBuffer();
        if (byteLength > (length * 2)) {
            charArray = str.toCharArray();
            int resultLength = 0;
            for (int i = 0; i < charArray.length; i++) {
                resultLength += String.valueOf(charArray[i]).getBytes().length;
                if (resultLength > (length * 2)) {
                    break;
                }
                buff.append(charArray[i]);

            }
            buff.append("..");
            str = buff.toString();
        }

        // str = replace(str, "'", "\\'");
        str = replace(str, "\"", "\\\"");
        str = replace(str, "，", ",");
        return str;

    }

    public static String delTag(String str) {
        str = str + "<>";
        StringBuffer buff = new StringBuffer();
        int start = 0;
        int end = 0;

        while (str.length() > 0) {
            start = str.indexOf("<");
            end = str.indexOf(">");
            if (start > 0) {
                buff.append(str.substring(0, start));
            }
            if (end > 0 && end <= str.length()) {
                str = str.substring(end + 1, str.length());
            } else {
                str = "";
            }

        }
        String result = buff.toString();

        while (result.startsWith(" ")) {

            result = result.substring(result.indexOf(" ") + 1, result.length());

        }
        return result;

    }

    public static final String replace(String line, String oldString, String newString) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;

    }

    // Replace
    public static String Replace(String source, String oldString, String newString) {
        if (source == null) {
            return null;
        }
        StringBuffer output = new StringBuffer();
        int lengOfsource = source.length();
        int lengOfold = oldString.length();
        int posStart = 0;
        int pos;
        while ((pos = source.indexOf(oldString, posStart)) >= 0) {
            output.append(source.substring(posStart, pos));
            output.append(newString);
            posStart = pos + lengOfold;
        }
        if (posStart < lengOfsource) {
            output.append(source.substring(posStart));
        }
        return output.toString();
    }

    // 此函数前台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
    public static String toHtml(String s) {
        s = Replace(s, "<", "&lt;");
        s = Replace(s, ">", "&gt;");
        s = Replace(s, "\t", "    ");
        s = Replace(s, "\r\n", "\n");
        s = Replace(s, "\n", "<br>");
        // s = Replace(s, " ", "&nbsp;");
        s = Replace(s, "'", "&#39;");
        s = Replace(s, "\"", "&quot;");
        s = Replace(s, "\\", "&#92;");
        s = Replace(s, "%", "％");
        // s = Replace(s, "&", "&amp;");
        return s;
    }

    // 逆
    public static String unHtml(String s) {

        // s = Replace(s, "&lt;", "<");
        // s = Replace(s, "&gt;", ">");
        // s = Replace(s, " ", "\t");
        // s = Replace(s, "\n", "\r\n");
        s = Replace(s, "<br>", "\n");
        // s = Replace(s, "&nbsp;", " ");
        // s = Replace(s, "&amp;", "&");
        // s = Replace(s, "&#39;", "'");
        // s = Replace(s, "&#92;", "\\");
        // s = Replace(s, "％", "%");
        return s;
    }

    // 此函数后台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
    public static String toHtmlBack(String s) {
        // 显示
        s = Replace(s, "&", "&amp;");
        s = Replace(s, "\\", "&#92;");
        s = Replace(s, "\"", "&quot;");
        s = Replace(s, "<", "&lt;");
        s = Replace(s, ">", "&gt;");
        s = Replace(s, "\t", "    ");
        s = Replace(s, "\r\n", "\n");
        // s = Replace(s, "\n", "<br>");
        // s = Replace(s, " ", "&nbsp;");
        // s = Replace(s, "'", "&#39;");
        // s = Replace(s, "%", "%");

        return s;
    }

    // 逆
    public static String unHtmlBack(String s) {
        s = Replace(s, "&lt;", "<");
        s = Replace(s, "&gt;", ">");
        s = Replace(s, "    ", "\t");
        s = Replace(s, "\n", "\r\n");
        s = Replace(s, "<br>", "\n");
        s = Replace(s, "&nbsp;", " ");
        s = Replace(s, "&amp;", "&");
        s = Replace(s, "&#39;", "'");
        s = Replace(s, "&#92;", "\\");
        s = Replace(s, "％", "%");
        return s;
    }

    // 判断是否含有中文，如果含有中文返回ture
    public static boolean containsChinese(String str) throws UnsupportedEncodingException {

        if (str.length() < (str.getBytes()).length)
            return true;

        return false;
    }

    public static boolean isEmpty(String str) {
        if (str == null || "null".equals(str))
            return true;
        return "".equals(str.trim());
    }

    public static String[] split(String str1, String str2) {
        return org.apache.commons.lang.StringUtils.split(str1, str2);
    }

    /**
     * <br>
     * <b>功能：</b>将字符串转成list<br>
     * <b>作者：</b>军<br>
     * <b>日期：</b> Oct 28, 2011 <br>
     *
     * @param exp   分割符 如","
     * @param value
     * @return
     */
    public static List<String> StringToList(String value, String exp) {
        List<String> resultList = new ArrayList<String>();
        String[] vals = split(value, exp);
        for (String val : vals) {
            resultList.add(val);
        }
        return resultList;
    }

    /**
     * <br>
     * <b>功能：</b>数字转换成字符串<br>
     * <b>作者：</b>军<br>
     * <b>日期：</b> Jul 30, 2011 <br>
     *
     * @param arrs
     * @return
     */
    public static String arrayToString(String[] arrs) {
        StringBuffer result = new StringBuffer("");
        if (arrs != null && arrs.length > 0) {
            for (int i = 0; i < arrs.length; i++) {

                if (!result.toString().equals("")) {
                    result.append(",");
                }
                if (arrs[i] != null && !"".equals(arrs[i].trim())) {
                    result.append(arrs[i]);
                }
            }
        }
        return result.toString();
    }

    public static String left(String str, int length) {
        return org.apache.commons.lang.StringUtils.left(str, length);
    }

    /**
     * <br>
     * <b>功能：</b>替换回车<br>
     * <b>作者：</b>军<br>
     * <b>日期：</b> Oct 26, 2011 <br>
     *
     * @param str
     * @return
     */
    public static String replaceHuiche(String str) {
        String after = str.replaceAll("\r\n", "");
        return after;
    }

    /**
     * 根据输入的长度截取字符串，单个单词超过输入长度的强制加<BR>
     * 换行
     *
     * @param str 输入的字符串
     * @param len 输入的长度
     * @return 处理过后的字符串
     */
    public static String truncateStr(String str, int len) {
        if (str != null && !("").equalsIgnoreCase(str)) {

            String strs[] = str.split(" ");
            StringBuffer buff = new StringBuffer();
            if (strs.length > 0) {
                for (int i = 0; i < strs.length; i++) {
                    StringBuffer temp = new StringBuffer();
                    while (strs[i].length() > len) {
                        temp.append(strs[i].substring(0, len) + "<BR>");
                        strs[i] = strs[i].substring(len);
                    }
                    temp.append(strs[i]);
                    buff.append(temp.toString() + " ");
                }

            }
            return buff.toString();
        } else {
            return "";
        }
    }

    public static String truncateStr2(String str, int len) {
        if (str != null && !("").equalsIgnoreCase(str) && len != 0) {
            String strs[] = str.split(" ");

            StringBuffer buff = new StringBuffer();
            for (int i = 0; i < strs.length; i++) {
                StringBuffer temp = new StringBuffer();
                String tempstr = "";
                while (strs[i].length() > len) {
                    tempstr = strs[i].substring(0, len);
                    tempstr = tempstr.replaceAll(" ", "&nbsp; ");
                    tempstr = tempstr.replaceAll("<", "&lt; ");
                    tempstr = tempstr.replaceAll("\n", "<br> ").replaceAll("\"", "&quot; ").replaceAll("'", "&#39; ");
                    tempstr = tempstr + "<br>";
                    temp.append(tempstr);

                    strs[i] = strs[i].substring(len);
                }
                tempstr = strs[i];
                tempstr = tempstr.replaceAll(" ", "&nbsp; ");
                tempstr = tempstr.replaceAll("<", "&lt; ");
                tempstr = tempstr.replaceAll("\n", "<br> ").replaceAll("\"", "&quot; ").replaceAll("'", "&#39; ");

                temp.append(tempstr);
                buff.append(temp.toString() + " ");
            }

            if (buff.length() > 0)
                return buff.toString().substring(0, buff.length() - 1);
            else
                return str;
        } else {
            return "";
        }
    }

    /**
     * 编码转换，从unicode转换为GBK
     *
     * @param l_S_Source 输入字符串
     * @return str编码转换后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String unicodeToGB(String l_S_Source) throws UnsupportedEncodingException {
        String l_S_Desc = "";
        if (l_S_Source != null && !l_S_Source.trim().equals("")) {
            byte l_b_Proc[] = l_S_Source.getBytes("GBK");
            l_S_Desc = new String(l_b_Proc, "ISO8859_1");
        }
        return l_S_Desc;
    }

    /**
     * 编码转换，从GBK转换为unicode
     *
     * @param l_S_Source 输入字符串
     * @return str 编码转换后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String GBToUnicode(String l_S_Source) throws UnsupportedEncodingException {
        String l_S_Desc = "";
        if (l_S_Source != null && !l_S_Source.trim().equals("")) {
            byte l_b_Proc[] = l_S_Source.getBytes("ISO8859_1");
            l_S_Desc = new String(l_b_Proc, "GBK");
        }
        return l_S_Desc;
    }

    /**
     * Escapes a <code>String</code> according the JavaScript string literal
     * escaping rules. The resulting string will not be quoted.
     *
     * <p>
     * It escapes both <tt>'</tt> and <tt>"</tt>. In additional it escapes
     * <tt>></tt> as <tt>\></tt> (to avoid <tt>&lt;/script></tt>). Furthermore,
     * all characters under UCS code point 0x20, that has no dedicated escape
     * sequence in JavaScript language, will be replaced with hexadecimal escape
     * (<tt>\x<i>XX</i></tt>).
     */
    public static String javaScriptStringEnc(String s) {
        int ln = s.length();
        for (int i = 0; i < ln; i++) {
            char c = s.charAt(i);
            if (c == '"' || c == '\'' || c == '\\' || c == '>' || c < 0x20) {
                StringBuffer b = new StringBuffer(ln + 4);
                b.append(s.substring(0, i));
                while (true) {
                    if (c == '"') {
                        b.append("\\\"");
                    } else if (c == '\'') {
                        b.append("\\'");
                    } else if (c == '\\') {
                        b.append("\\\\");
                    } else if (c == '>') {
                        b.append("\\>");
                    } else if (c < 0x20) {
                        if (c == '\n') {
                            b.append("\\n");
                        } else if (c == '\r') {
                            b.append("\\r");
                        } else if (c == '\f') {
                            b.append("\\f");
                        } else if (c == '\b') {
                            b.append("\\b");
                        } else if (c == '\t') {
                            b.append("\\t");
                        } else {
                            b.append("\\x");
                            int x = c / 0x10;
                            b.append((char) (x < 0xA ? x + '0' : x - 0xA + 'A'));
                            x = c & 0xF;
                            b.append((char) (x < 0xA ? x + '0' : x - 0xA + 'A'));
                        }
                    } else {
                        b.append(c);
                    }
                    i++;
                    if (i >= ln) {
                        return b.toString();
                    }
                    c = s.charAt(i);
                }
            } // if has to be escaped
        } // for each characters
        return s;
    }

    private static StringUtil instance = null;

    public static synchronized StringUtil getInstance() {
        if (instance == null) {
            instance = new StringUtil();
        }
        return instance;
    }

    /**
     * 将多个连续空格替换为一个,"a b"-->"a b"
     *
     * @param src
     * @return
     * @author WilliamLau
     * @desc
     */
    public static String trimContinuousSpace(String src) {
        return src.replaceAll("\\s+", " ");
    }

    public static String replace(String src, String target, String rWith, int maxCount) {
        return org.apache.commons.lang.StringUtils.replace(src, target, rWith, maxCount);
    }

    public static boolean equals(String str1, String str2) {
        return org.apache.commons.lang.StringUtils.equals(str1, str2);
    }

    public static boolean isAlphanumeric(String str) {
        return org.apache.commons.lang.StringUtils.isAlphanumeric(str);
    }

    public static boolean isNumeric(String str) {
        return org.apache.commons.lang.StringUtils.isNumeric(str);
    }

    public static String[] stripAll(String[] strs) {
        return org.apache.commons.lang.StringUtils.stripAll(strs);
    }

    public static String toFloatNumber(String num) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(Double.parseDouble(num));
    }

    public static String toFloatNumber(Double num, int accuracy) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(accuracy);
        nf.setMinimumFractionDigits(accuracy);
        return nf.format(num);
    }

    public static String wcsUnescape(String str) {
        str = str.replace("#lt;", "<");
        str = str.replace("#gt;", ">");
        str = str.replace("#quot;", "\"");
        str = str.replace("#amp;amp;", "&");
        str = str.replace("#amp;", "&");
        str = str.replace("#039;", "'");
        return str;
    }

    /**
     * <br>
     * <b>功能：</b>返回string型的字节数<br>
     * <b>作者：</b>军<br>
     * <b>日期：</b> Sep 2, 2011 <br>
     *
     * @param str
     * @return
     */
    public static int getByteLength(String str) {
        if (str == null) {
            return 0;
        }
        return str.getBytes().length;
    }

    /**
     * <br>
     * <b>功能：</b>详细的功能描述<br>
     * <b>作者：</b>军<br>
     * <b>日期：</b> Sep 2, 2011 <br>
     *
     * @param str      字符
     * @param limitLen 长度
     * @return
     */
    public static String getByteStr(String str, int limitLen) {
        StringBuffer sb = new StringBuffer();
        char[] chars = getNotNullStr(str).toCharArray();
        int len = 0;
        for (char c : chars) {
            len += getByteLength(String.valueOf(c));
            if (len <= limitLen) {
                sb.append(c);
            }
        }
        return sb.toString();

    }

    /**
     * @param content 内容
     * @param length  指定长度。 超出这个长度就截取字符串。
     * @param padding 超出长度后，尾加上字符，如"..."，可以为空
     * @return 返回结果 如果内容没有超出指定的长度。则返回原字符串，超出长度后则截取到指定的长度的内容
     */
    public static String subStr(String content, Integer length, String padding) throws UnsupportedEncodingException {
        String str = "";
        int paddSize = StringUtils.isBlank(padding) ? 0 : padding.length();
        // 如果内容为空，或者小于指定的长度。则返回原内容
        if (StringUtils.isBlank(content) || content.length() <= length) {
            return content;
        }
        str = content.substring(0, length - paddSize);
        if (StringUtils.isNotBlank(padding)) {
            str += padding;
        }
        return str;
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return 若有一个为空则返回true
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Object... obj) {
        if (obj == null)
            return true;
        for (int k = 0; k < obj.length; k++) {
            if (obj[k] == null)
                return true;
            if (obj[k] instanceof CharSequence)
                if (((CharSequence) obj[k]).length() == 0) {
                    return true;
                }
            if (obj[k] instanceof Collection)
                if (((Collection) obj[k]).isEmpty()) {
                    return true;
                }
            if (obj[k] instanceof Map)
                if (((Map) obj[k]).isEmpty()) {
                    return true;
                }
        }
        return false;
    }

    /**
     * 判断数组中是不是不全为空字符串 ""
     * 不是返回true,是返回flase
     */
    public static boolean isNotEmpty(String[] obj) {
        String str = "";
        for (int i = 0; i < obj.length; i++) {
            if (StringUtil.checkNotNull(obj[i]))
                str += obj;
        }
        if (StringUtil.isNullString(str))
            return false;
        return true;
    }

    /**
     * 判断字符串是否为null或者空
     *
     * @param str
     * @return
     */
    public static boolean isNullString(String str) {
        boolean ret = true;
        if ((str != null) && (str.length() > 0)) {
            ret = false;
        }
        return ret;
    }

    /**
     * 将字符串中的中文标点转成英文,目前能处理"，"、"；"、"："的情况
     *
     * @param cString
     * @return
     */
    public static String BiaoDianC2E(String cString) {
        if (checkNotNull(cString)) {
            if (cString.indexOf("，") > 0) {
                cString = cString.replace("，", ",");
            }
            if (cString.indexOf("；") > 0) {
                cString = cString.replace("；", ";");
            }
            if (cString.indexOf("：") > 0) {
                cString = cString.replace("：", ":");
            }
            if (cString.indexOf("~") > 0) {
                cString = cString.replace("~", "~");
            }
        }
        return cString;
    }

    /**
     * 判断分割后字符串的位数
     */
    public static Integer splitCount(String str) {
        String[] strArry = str.split(",");
        Integer splitCount = strArry.length;
        return splitCount;
    }

    /**
     * 判断是不是日期格式(日期标准格式：yyyy-mm-dd)
     */
    public static boolean dateFormat(String str) {
        String pat = "\\d{4}-\\d{2}-\\d{2}"; //正则表达式
        Pattern p = Pattern.compile(pat);//实例化pattern类
        Matcher m = p.matcher(str);//实例化Matcher类
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是不是日期格式(日期标准格式：yyyy-mm-dd~yyyy-mm-dd)
     */
    public static boolean dateFormats(String str) {
        String pat = "\\d{4}-\\d{2}-\\d{2}~\\d{4}-\\d{2}-\\d{2}"; //正则表达式
        Pattern p = Pattern.compile(pat);//实例化pattern类
        Matcher m = p.matcher(str);//实例化Matcher类
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是不是时间段格式(时间段标准格式：hh:mm:ss~hh:mm:ss)
     */
    public static boolean timeFormat(String str) {
        String pat = "\\d{2}:\\d{2}:\\d{2}~\\d{2}:\\d{2}:\\d{2}"; //正则表达式
        Pattern p = Pattern.compile(pat);//实例化pattern类
        Matcher m = p.matcher(str);//实例化Matcher类
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 除掉 字符串的空格，html的；以及将null转成“”，并且去除字符串两端空格
     *
     * @param str
     * @return
     */
    public static String trim_html(String str) {
        if (str == null) {
            return "";
        } else if (str == "&nbsp;" || str.compareTo("&nbsp;") == 0 || str.equals("null") || str.equals("") || str.trim() == null ||
                str.trim().equals("")) {
            return "";
        } else {
            return str.trim();
        }
    }

    /**
     * 根据正则表达式判定两个字符串是否匹配
     *
     * @param str   匹配字符串
     * @param regex 匹配正则表达式字符串
     * @param bool  是否区分大小写
     * @return
     */
    public static boolean isMatcher(String str, String regex, boolean bool) {

        regex = regex.replaceAll("\\*", ".*");
        regex = regex.replaceAll("\\?", ".");
        Pattern pattern = Pattern.compile(regex, bool ? Pattern.CASE_INSENSITIVE : 0);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * 判断是不是数值（小数/整数）
     */
    public static boolean isNum(String num) {
        boolean str = false;
        if (num.matches("\\d+(.\\d+)?")) {
            str = true;
        }
        return str;
    }

    private static String CMCC = "^[1]{1}(([3]{1}[4-9]{1})|([5]{1}[012789]{1})|([8]{1}[23478]{1})|([4]{1}[7]{1})|([7]{1}[8]{1}))[0-9]{8}$";
    private static String UNICOM = "^[1]{1}(([3]{1}[0-2]{1})|([5]{1}[56]{1})|([8]{1}[56]{1})|([4]{1}[5]{1})|([7]{1}[6]{1}))[0-9]{8}$";
    private static String TELE = "^[1]{1}(([3]{1}[3]{1})|([5]{1}[3]{1})|([8]{1}[09]{1})|([7]{1}[37]{1}))[0-9]{8}$";

    /**
     * 入参：手机号  根据手机号查询运营商类型
     * return param
     * code (int):200 手机号符合要求  code:500手机号不合要求
     * type  (int): 1:CMCC 2:UNICOM 3:TELE
     */
    public static String getOperatorTypeByNum(String mobPhnNum) {

        String operatorType = "";
        // 判断手机号码是否是11位
        if (mobPhnNum.length() == 11) {
            // 判断手机号码是否符合中国移动的号码规则
            if (mobPhnNum.matches(CMCC)) {
                operatorType = "移动";
            }
            // 判断手机号码是否符合中国联通的号码规则
            else if (mobPhnNum.matches(UNICOM)) {
                operatorType = "联通";
            }
            // 判断手机号码是否符合中国电信的号码规则
            else if (mobPhnNum.matches(TELE)) {
                operatorType = "电信";
            }
            // 都不合适 未知
            else {
                operatorType = "未知";
            }
        }
        // 不是11位
        else {
            operatorType = "手机号错误";
        }
        return operatorType;
    }

    /**
     * 判定imsi是否合法：长度
     *
     * @param imsi
     * @return
     */
    public static boolean isLegalIMSI(String imsi) {
        boolean isLegalIMSI = false;
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence) imsi);
        isLegalIMSI = matcher.matches() && (imsi.length() == 15);
        return isLegalIMSI;
    }

    /**
     * 判定手机号码是否合法:长度
     *
     * @param pNum
     * @return
     */
    public static boolean isLegalPhone(String pNum) {
        boolean isLegalPhone = false;
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence) pNum);
        isLegalPhone = matcher.matches() && (pNum.length() == 11);
        return isLegalPhone;
    }

    /**
     * 转换十六进制编码为字符串
     */
    public static String toStringHex(String s) {
        if ("0x".equals(s.substring(0, 2))) {
            s = s.substring(2);
        }
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            s = new String(baKeyword, "utf-8");//UTF-16le:Not  
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 从字符串中提取数字
     */
    public static String StringToNum(String s) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        return m.replaceAll("").trim();
    }

    /**
     * 将此类字符串转为set：
     * 1,2,3,4,5,
     *
     * @param serviceMenu
     * @return
     */
    public static HashSet<String> getStrSet(String serviceMenu) {
        HashSet<String> strSet = new HashSet<>();
        if (checkNotNull(serviceMenu)) {
            String array[] = serviceMenu.split(",");
            for (int i = 0; i < array.length; i++) {
                if (StringUtil.checkNotNull(array[i])) {
                    strSet.add(array[i]);
                }
            }

        }
        return strSet;
    }

    /**
     * 将mac地址转化为适配内容：分析原理 ，去掉":"，不足14位，前面补0
     * 例：(原地址CC:FA:00:C9:8F:01，适配后00CCFA00C98F01)
     *
     * @param mac
     * @return
     */
    public static String getFilledMac(String mac) {
        mac = BiaoDianC2E(mac);
        if (mac.contains(":")) {
            String macTemp = mac.replace(":", "");
            int length = 14 - macTemp.length();
            StringBuffer prefix = new StringBuffer();
            if (length != 0) {
                for (int i = 0; i < length; i++) {
                    prefix.append("0");
                }
                macTemp = prefix + macTemp;
            }
            mac = macTemp;
        }
        return mac;
    }

    /**
     * 将ip地址转化为适配内容：分析原理 ，以"."拆分，不足三位前面补0，补充到三位
     * 例：(原地址192.168.0.1，适配后192168000001)
     *
     * @param ip
     * @return
     */
    public static String getFilledIp(String ip) {
        if (ip.contains(".")) {
            String[] ipArray = ip.split("\\.");//split拆分"."需要转义，又学到了 
            StringBuffer ipStr = new StringBuffer();
            for (int i = 0; i < ipArray.length; i++) {
                if (ipArray[i].length() == 0) {
                    ipArray[i] = "000";
                } else if (ipArray[i].length() == 1) {
                    ipArray[i] = "00" + ipArray[i];
                } else if (ipArray[i].length() == 2) {
                    ipArray[i] = "0" + ipArray[i];
                }
                ipStr.append(ipArray[i]);
            }
            ip = ipStr.toString();
        }
        return ip;
    }


    public static String intStrAdd(String str1, String str2) {

        int strToInt1 = getStrToInt(str1);
        int strToInt2 = getStrToInt(str2);

        return Integer.toString(strToInt1 + strToInt2);
    }
}
