package entity;

public class Teacher {
    private int tch_id;
    private String tch_name;
    private String tch_sex;

    public int getTch_id() {
        return tch_id;
    }

    public String getTch_name() {
        return tch_name;
    }

    public String getTch_sex() {
        return tch_sex;
    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
    }

    public void setTch_sex(String tch_sex) {
        this.tch_sex = tch_sex;
    }
}
