package Bean;

import java.io.Serializable;

//役割:処理後の返り値を受け取って格納する

public class RegisterBean implements Serializable{
    private String      name   ;
    private String      id         ;
    private String      pass     ;
    private String      pref      ;
    private String      sex       ;
    private int          tel        ;
    private String      mail      ;
    private String      job        ; 
    private boolean  goal_register;

    public RegisterBean() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPref() {
        return pref;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
    
    public boolean isGoal_register() {
        return goal_register;
    }

    public void setGoal_register(boolean goal_register) {
        this.goal_register = goal_register;
    }

}