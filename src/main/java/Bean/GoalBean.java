package Bean;

import java.io.Serializable;

//役割:処理後の返り値を受け取って格納する

public class GoalBean implements Serializable{
    private String name;
    private String month_goal;
    private String step1;
    private String step2;
    private String step3;
    private String step4;
    private String step5;
    private String week_goal;
    private int      month;
    private int      week;
    private boolean goal_register;

    public GoalBean() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth_goal() {
        return month_goal;
    }

    public void setMonth_goal(String month_goal) {
        this.month_goal = month_goal;
    }

    public String getStep1() {
        return step1;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }

    public String getStep2() {
        return step2;
    }

    public void setStep2(String step2) {
        this.step2 = step2;
    }

    public String getStep3() {
        return step3;
    }

    public void setStep3(String step3) {
        this.step3 = step3;
    }

    public String getStep4() {
        return step4;
    }

    public void setStep4(String step4) {
        this.step4 = step4;
    }

    public String getStep5() {
        return step5;
    }

    public void setStep5(String step5) {
        this.step5 = step5;
    }

    public String getWeek_goal() {
        return week_goal;
    }

    public void setWeek_goal(String week_goal) {
        this.week_goal = week_goal;
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
    
    public boolean isGoal_register() {
        return goal_register;
    }

    public void setGoal_register(boolean goal_register) {
        this.goal_register = goal_register;
    }
    
}