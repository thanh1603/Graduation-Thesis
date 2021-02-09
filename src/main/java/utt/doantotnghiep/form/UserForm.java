package utt.doantotnghiep.form;

import utt.doantotnghiep.entities.User;

public class UserForm  extends User implements Cloneable {
    private String rePassWord;

    public String getRePassWord() {
        return rePassWord;
    }

    public void setRePassWord(String rePassWord) {
        this.rePassWord = rePassWord;
    }

    @Override
    public  Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
