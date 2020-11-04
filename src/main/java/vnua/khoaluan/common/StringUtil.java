package vnua.khoaluan.common;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public class StringUtil {

    private StringUtil() {
    }

    public static boolean isEmpty(String value) {
        return StringUtils.isEmpty(value);
    }

    public static boolean isEmptyWithTrim(String value) {
        if(value == null) {
            return true;
        }
        return StringUtils.isEmpty((value.trim()));
    }

    public static String addDataString(String str, Integer digit) {

        if (str == null) {
            str = "";
        }

        if (str.length() < digit) {
            int pad = digit - str.length();
            for (int i = 0; i < pad; i++) {
                str = str + " ";
            }
            return str;

        } else if (str.length() > digit && digit > 0) {
            str = str.substring(0, digit);
        }

        return str;
    }

    public static String addDataInteger(Integer number, Integer digit) {

        if (number == null) {
            number = 0;
        }
        if (number.toString().length() > digit) {
            number = Integer.valueOf(number.toString().substring(0, digit));
        }
        String format = "%1$0" + digit + "d";
        String str = String.format(format, number);

        return str;
    }

    public static String addDataIntegerSign(Integer number, Integer digit) {

        if (number == null) {
            number = 0;
        }
        if (number.toString().length() > digit) {
            number = Integer.valueOf(number.toString().substring(0, digit));
        }
        String format = "%1$+0" + digit + "d";
        String str = String.format(format, number);

        return str;
    }

    public static String nullToEmpty(Integer number) {
        if (number == null) {
            return "";
        }
        return number.toString();
    }

    public static String nullToEmpty(BigDecimal number) {
        if (number == null) {
            return "";
        }
        return number.toString();
    }

    public static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String subString(String str, int start, int length) {

        if (str == null || "".equals(str.trim())) {
            return "";
        }
        if (str.length() < start + length) {
            return "";
        }

        return str.substring(start, start + length);
    }

    public static String getStr(Object object) {

        // 1．判定、及び戻り値の返却
        // #1 対象文字列 = null
        if (object == null) {
            // ""（空白）を返却する
            return "";
            // #1 上記以外
        } else {
            // 対象文字列を返却する
            return object.toString();

            // #1 END
        }

    }

    public static Boolean includeLineBreak(String input) {
        if (isEmpty(input)) {
            return false;
        }
        if (input.matches("(.*(?:\\r\\n|\\n).*)+")) {
            return true;
        }
        return false;
    }

    public static String escapeLineBreak(String input) {
        if (isEmpty(input)) {
            return input;
        }
        if (input.matches("(.*\\r\\n.*)+")) {
            input = input.replaceAll("\\r\\n", "\\\\\\r\\\\\\n");
        } else if (input.matches("(.*\\n.*)+")) {
            input = input.replaceAll("\\n", "\\\\\\n");
        }
        return input;
    }


    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}
