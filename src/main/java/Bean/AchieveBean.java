package Bean;

import java.io.Serializable;

//役割:処理後の返り値を受け取って格納する

public class AchieveBean implements Serializable{
    private String name;
    private int      month;
    private int      week;
    private boolean step1;
    private boolean step2;
    private boolean step3;
    private boolean step4;
    private boolean step5;
    
    public AchieveBean() {
        super();
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getWeek() {
        return week;
    }
    public void setWeek(int week) {
        this.week = week;
    }
    public boolean isStep1() {
        return step1;
    }
    public void setStep1(boolean step1) {
        this.step1 = step1;
    }
    public boolean isStep2() {
        return step2;
    }
    public void setStep2(boolean step2) {
        this.step2 = step2;
    }
    public boolean isStep3() {
        return step3;
    }
    public void setStep3(boolean step3) {
        this.step3 = step3;
    }
    public boolean isStep4() {
        return step4;
    }
    public void setStep4(boolean step4) {
        this.step4 = step4;
    }
    public boolean isStep5() {
        return step5;
    }
    public void setStep5(boolean step5) {
        this.step5 = step5;
    }
}
