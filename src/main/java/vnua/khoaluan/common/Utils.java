package vnua.khoaluan.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isValidEmail(String input) {
        input = input.trim();

        if (Utils.isEmpty(input)) {
            return false;
        }

        Pattern mail = Pattern.compile(
                "^((\"[\\w-\\s]+\")|([\\w-]+(?:\\.[\\w-]+)*)|(\"[\\w-\\s]+\")([\\w-]+(?:\\.[\\w-]+)*))(@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$)|(@\\[?((25[0-5]\\.|2[0-4][0-9]\\.|1[0-9]{2}\\.|[0-9]{1,2}\\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\]?$)");
        Matcher m = mail.matcher(input);

        if (!m.find()) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        if (null == str)
            return true;
        if (str.trim().equals(""))
            return true;
        return false;
    }

    public  static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("0\\d{9}$");
        Pattern pattern2 = Pattern.compile("0\\d{10}$");

        Matcher matcher1 = pattern.matcher(phoneNumber);
        Matcher matcher2= pattern2.matcher(phoneNumber);
        if (matcher1.matches()) {
            return true;
        }
        if (matcher2.matches()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isValidPhoneNumber("01211134567850"));
    }

    public static File multipartToFile(MultipartFile multipart, String path) throws IllegalStateException, IOException
    {
        File director = new File(path);
        if(!director.exists()) {
            director.mkdirs();
        }
        File convFile = new File( path + "//" + multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
}
