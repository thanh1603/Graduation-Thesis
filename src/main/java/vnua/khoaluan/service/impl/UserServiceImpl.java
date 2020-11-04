package vnua.khoaluan.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnua.khoaluan.bean.Result;
import vnua.khoaluan.common.Constant;
import vnua.khoaluan.common.StringUtil;
import vnua.khoaluan.common.Utils;
import vnua.khoaluan.entities.Catalog;
import vnua.khoaluan.entities.User;
import vnua.khoaluan.form.UserForm;
import vnua.khoaluan.service.IUserService;

import java.util.*;


@Service
@Transactional
public class UserServiceImpl implements IUserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired(required = true)
    private MongoTemplate mongoTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    public User findByEmail(String email) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));
            List<User> users = mongoTemplate.find(query, User.class);
            if (users.size() > 0) {
                return users.get(0);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    public Result registerUser(UserForm user) {
        Result result = new Result();
        try {
            // Validate email
            if(StringUtil.isEmptyWithTrim(user.getEmail())) {
                result.getMesStringByKey().put("emailError", getMessage(Constant.MSG_CODE.MSG_002));
            }else if(!Utils.isValidEmail(user.getEmail())) {
                result.getMesStringByKey().put("emailError", getMessage(Constant.MSG_CODE.MSG_003));
            }else if(findByEmail(user.getEmail()) != null) {
                result.getMesStringByKey().put("emailError", getMessage(Constant.MSG_CODE.MSG_010));
            }

            // Valid fullname
            if(StringUtil.isEmptyWithTrim(user.getFullName())) {
                result.getMesStringByKey().put("fullNameError", getMessage(Constant.MSG_CODE.MSG_004));
            }

            //Valid phone
            if(StringUtil.isEmptyWithTrim(user.getPhone())) {
                result.getMesStringByKey().put("phoneError", getMessage(Constant.MSG_CODE.MSG_005));
            }else if(!Utils.isValidPhoneNumber(user.getPhone())) {
                result.getMesStringByKey().put("phoneError", getMessage(Constant.MSG_CODE.MSG_006));
            }

            // Valid address
            if(StringUtil.isEmptyWithTrim(user.getAddress())) {
                result.getMesStringByKey().put("addressError", getMessage(Constant.MSG_CODE.MSG_007));
            }

            if(user.getPassWord() == null || user.getPassWord().trim().length() < 8) {
                result.getMesStringByKey().put("passWordError", getMessage(Constant.MSG_CODE.MSG_008));
            }else if(!user.getPassWord().equals(user.getRePassWord())) {
                result.getMesStringByKey().put("rePassWordError", getMessage(Constant.MSG_CODE.MSG_009));
            }

            if(result.getMesStringByKey().size() > 0) {
                result.setStatus(Constant.STATUS.ERROR);
                return  result;
            }

            User userUse = (User) user.clone();
            User userDb = new User();
            BeanUtils.copyProperties(userUse, userDb);
            if (userUse.getRoles() == null || userUse.getRoles().size() == 0) {
                userUse.setRoles(Arrays.asList(Constant.ROLE.USER));
            }
            userDb.setPassWord(passwordEncoder.encode(userDb.getPassWord()));
            mongoTemplate.save(userDb, "user");
            result.setStatus(Constant.STATUS.OK);
        } catch (Exception ex) {
            result.setStatus(Constant.STATUS.ERROR);
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public  String getMessage(String code) {
        return messageSource.getMessage(code, null, new Locale("vi"));
    }

    public List<User> findALL() {
        try {
            List<User> users = mongoTemplate.findAll(User.class);
            return users;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<User>();
        }
    }
}
