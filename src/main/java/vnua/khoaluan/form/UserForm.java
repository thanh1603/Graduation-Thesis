package vnua.khoaluan.form;

import vnua.khoaluan.entities.User;

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
