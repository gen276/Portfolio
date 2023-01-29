package Bean;

import java.io.Serializable;

//役割:処理後の返り値を受け取って格納する

public class Goal_ListBean implements Serializable{
    private String   date;
    private String M_goal;
    private String W_goal;
    private int Step_num;
    private String Step_content;
    private String achieve;
    
    public Goal_ListBean() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getM_goal() {
        return M_goal;
    }

    public void setM_goal(String m_goal) {
        M_goal = m_goal;
    }

    public String getW_goal() {
        return W_goal;
    }

    public void setW_goal(String w_goal) {
        W_goal = w_goal;
    }

    public int getStep_num() {
        return Step_num;
    }

    public void setStep_num(int step_num) {
        Step_num = step_num;
    }

    public String getStep_content() {
        return Step_content;
    }

    public void setStep_content(String step_content) {
        Step_content = step_content;
    }

    public String getAchieve() {
        return achieve;
    }

    public void setAchieve(String achieve) {
        this.achieve = achieve;
    }

}   