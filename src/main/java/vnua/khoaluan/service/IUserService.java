package vnua.khoaluan.service;

import vnua.khoaluan.bean.Result;
import vnua.khoaluan.entities.User;
import vnua.khoaluan.form.UserForm;

import java.util.List;

public interface IUserService {
    User findByEmail (String email);

    Result registerUser(UserForm user);

    List<User> findALL();
}
