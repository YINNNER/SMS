package entity;

import java.util.Date;

public class Student {
    private int stu_id;
    private int inst_id;
    private int maj_id;
    private int class_id;

    private String stu_name;
    private String stu_sex;
    private Date stu_birth_date;
    private String stu_birth_place;
    private String stu_political;

    public int getStu_id() {
        return stu_id;
    }

    public int getInst_id() {
        return inst_id;
    }

    public int getMaj_id() {
        return maj_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public String getStu_sex() {
        return stu_sex;
    }

    public Date getStu_birth_date() {
        return stu_birth_date;
    }

    public String getStu_birth_place() {
        return stu_birth_place;
    }

    public String getStu_political() {
        return stu_political;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public void setStu_sex(String stu_sex) {
        this.stu_sex = stu_sex;
    }

    public void setStu_birth_date(Date stu_birth_date) {
        this.stu_birth_date = stu_birth_date;
    }

    public void setStu_birth_place(String stu_birth_place) {
        this.stu_birth_place = stu_birth_place;
    }

    public void setStu_political(String stu_political) {
        this.stu_political = stu_political;
    }

    public Student(int stu_id, int inst_id, int maj_id, int class_id, String stu_name, String stu_sex, Date stu_birth_date, String stu_birth_place, String stu_political) {
        this.stu_id = stu_id;
        this.inst_id = inst_id;
        this.maj_id = maj_id;
        this.class_id = class_id;
        this.stu_name = stu_name;
        this.stu_sex = stu_sex;
        this.stu_birth_date = stu_birth_date;
        this.stu_birth_place = stu_birth_place;
        this.stu_political = stu_political;
    }

    public Student(int stu_id) {
        this.stu_id = stu_id;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_id=" + stu_id +
                ", inst_id=" + inst_id +
                ", maj_id=" + maj_id +
                ", class_id=" + class_id +
                ", stu_name='" + stu_name + '\'' +
                ", stu_sex='" + stu_sex + '\'' +
                ", stu_birth_date=" + stu_birth_date +
                ", stu_birth_place='" + stu_birth_place + '\'' +
                ", stu_political='" + stu_political + '\'' +
                '}';
    }
}
