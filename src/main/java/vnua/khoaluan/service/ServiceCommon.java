package vnua.khoaluan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ServiceCommon {
    @Autowired
    private MessageSource messageSource;
    public  String getMessage(String code) {
        return messageSource.getMessage(code, null, new Locale("vi"));
    }
    public  String getMessage(String code, String[] text) {
        return messageSource.getMessage(code, text, new Locale("vi"));
    }

}
