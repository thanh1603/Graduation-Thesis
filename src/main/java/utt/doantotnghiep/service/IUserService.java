package utt.doantotnghiep.service;

import utt.doantotnghiep.bean.Result;
import utt.doantotnghiep.entities.User;
import utt.doantotnghiep.form.UserForm;

import java.util.List;

public interface IUserService {
    User findByEmail (String email);

    Result registerUser(UserForm user);

    List<User> findALL();
}
