package com.yuanrong.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhonghang on 2018/4/11.
 */
public class StringUtil  extends StringUtils {

    public static int size(String str) {
        return isEmpty(str) ? 0 : str.length();
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(StringBuffer sb) {
        return sb == null || sb.length() == 0;
    }

    public static String format(String value) {
        return format(value, "");
    }

    public static String format(String value, String defaultValue) {
        return isEmpty(value) ? defaultValue : value.trim();
    }

    public static String formateString(String str, String... params) {
        for(int i = 0; i < params.length; ++i) {
            str = str.replace("{" + i + "}", params[i] == null ? "" : params[i]);
        }

        return str;
    }

    public static final int parseInt(String value) {
        return isInt(value) ? Integer.parseInt(value) : 0;
    }

    public static final int parseInt(Integer value) {
        return value != null ? value : 0;
    }

    public static final boolean isInt(String value) {
        if (isEmpty(value)) {
            return false;
        } else {
            try {
                Integer.parseInt(value);
                return true;
            } catch (NumberFormatException var2) {
                return false;
            }
        }
    }

    public static String camel4underline(String param) {
        Pattern p = Pattern.compile("[A-Z]");
        if (param != null && !param.equals("")) {
            StringBuilder builder = new StringBuilder(param);
            Matcher mc = p.matcher(param);

            for(int i = 0; mc.find(); ++i) {
                builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            }

            if ('_' == builder.charAt(0)) {
                builder.deleteCharAt(0);
            }

            return builder.toString();
        } else {
            return "";
        }
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static final String alert(String message, String action) {
        StringBuffer sb = new StringBuffer();
        if (isEmpty(message)) {
            sb.append("<script>");
        } else {
            message = message.replace("'", "‘");
            message = message.replace("\"", "“");
            sb.append("<script>layer.alert('" + message + "\\t\\n' ,function(){");
        }

        if ("close".equalsIgnoreCase(action)) {
            sb.append("window.close();");
        } else if ("back".equalsIgnoreCase(action)) {
            sb.append("history.go(-1);");
        } else if (action.contains("#parentForward")) {
            action = action.replace("#parentForward", "");
            sb.append("parent.document.location='" + action + "'");
        } else {
            sb.append("document.location='" + action + "'");
        }

        sb.append("});</script>");
        return sb.toString();
    }

    public static final String easyUIAlert(String message, String action) {
        StringBuffer sb = new StringBuffer();
        if (isEmpty(message)) {
            sb.append("<script>");
        } else {
            message = message.replace("'", "‘");
            message = message.replace("\"", "“");
            sb.append("<script>if (parent.$ && parent.$.messager) {\tparent.$.messager.progress('close');\tparent.$.messager.alert('提示', '" + message + "');" + "} else {" + "\t$.messager.progress('close');" + "\t$.messager.alert('提示', '" + message + "');" + "}");
        }

        if ("close".equalsIgnoreCase(action)) {
            sb.append("window.close();");
        } else if ("back".equalsIgnoreCase(action)) {
            sb.append("history.go(-1);");
        } else {
            sb.append("document.location='" + action + "'");
        }

        sb.append("</script>");
        return sb.toString();
    }

    public static Long parseLong(int recordCount) {
        try {
            long l = Long.parseLong(String.valueOf(recordCount));
            return l;
        } catch (Exception var3) {
            return 0L;
        }
    }

    public static String stringArrayToString(String[] str, String separator) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < str.length; ++i) {
            sb.append(str[i]);
            if (str.length != i + 1) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    public static String stringArrayToString(String[] str) {
        return stringArrayToString(str, "");
    }

    public static String charArrayToString(char[] ch, String separator) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < ch.length; ++i) {
            sb.append(ch[i]);
            if (ch.length != i + 1) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    public static String charArrayToString(char[] ch) {
        return charArrayToString(ch, " ");
    }

    public static String substrGB(String text, int length) {
        String sRet = "";
        if (isEmpty(text)) {
            return "";
        } else {
            if (text.length() <= length) {
                sRet = text;
            } else {
                sRet = text.substring(0, length) + "...";
            }

            return sRet;
        }
    }

    public static String substr(String text, int length) {
        String sRet = "";
        if (isEmpty(text)) {
            return "";
        } else {
            if (text.length() <= length) {
                sRet = text;
            } else {
                sRet = text.substring(0, length);
            }

            return sRet;
        }
    }

    public static String format(String str, boolean format) {
        str = format(str);
        if (isMessyCode(str)) {
            try {
                return new String(str.getBytes("ISO8859-1"), "UTF-8");
            } catch (Exception var3) {
                return "";
            }
        } else {
            return str;
        }
    }

    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = 0.0F;
        float count = 0.0F;

        for(int i = 0; i < ch.length; ++i) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    ++count;
                }

                ++chLength;
            }
        }

        float result = count / chLength;
        if ((double)result > 0.4D) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static final String getRandomString(int length) {
        String[] s = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        if (length < 1) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer(length);

            for(int i = 0; i < length; ++i) {
                int position = getRandomNumber(s.length - 1);
                Collections.shuffle(Arrays.asList(s));
                sb.append(s[position]);
            }

            return sb.toString();
        }
    }

    public static final int getRandomNumber(int max) {
        return getRandomNumber(0, max);
    }

    public static final int getRandomNumber(int min, int max) {
        if (min > max) {
            int k = min;
            min = max;
            max = k;
        }

        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

}
