package entity;

import java.util.Date;

public class Course {
    private int coz_id;
    private String coz_name;
    private String coz_place;
    private int coz_credit;
    private int inst_id;
    private int maj_id;
    private int tch_id;
    private String coz_time;
    private int coz_year;
    private int coz_semester;

    public Course() {
    }

    public Course(int coz_id) {
        this.coz_id = coz_id;
    }

    public Course(int coz_id, String coz_name, String coz_place, int coz_credit, int inst_id, int maj_id, int tch_id, String coz_time, int coz_year, int coz_semester) {
        this.coz_id = coz_id;
        this.coz_name = coz_name;
        this.coz_place = coz_place;
        this.coz_credit = coz_credit;
        this.inst_id = inst_id;
        this.maj_id = maj_id;
        this.tch_id = tch_id;
        this.coz_time = coz_time;
        this.coz_year = coz_year;
        this.coz_semester = coz_semester;
    }

    public int getCoz_id() {
        return coz_id;
    }

    public String getCoz_name() {
        return coz_name;
    }

    public String getCoz_place() {
        return coz_place;
    }

    public int getCoz_credit() {
        return coz_credit;
    }

    public int getInst_id() {
        return inst_id;
    }

    public int getMaj_id() {
        return maj_id;
    }

    public int getTch_id() {
        return tch_id;
    }

    public String getCoz_time() {
        return coz_time;
    }

    public int getCoz_year() {
        return coz_year;
    }

    public int getCoz_semester() {
        return coz_semester;
    }

    public void setCoz_name(String coz_name) {
        this.coz_name = coz_name;
    }

    public void setCoz_place(String coz_place) {
        this.coz_place = coz_place;
    }

    public void setCoz_credit(int coz_credit) {
        this.coz_credit = coz_credit;
    }

    public void setCoz_time(String coz_time) {
        this.coz_time = coz_time;
    }

    public void setCoz_year(int coz_year) {
        this.coz_year = coz_year;
    }

    public void setCoz_semester(int coz_semester) {
        this.coz_semester = coz_semester;
    }

    @Override
    public String toString() {
        return "Course{" +
                "coz_id=" + coz_id +
                ", coz_name='" + coz_name + '\'' +
                ", coz_place='" + coz_place + '\'' +
                ", coz_credit=" + coz_credit +
                ", inst_id=" + inst_id +
                ", maj_id=" + maj_id +
                ", tch_id=" + tch_id +
                ", coz_time=" + coz_time +
                ", coz_year=" + coz_year +
                ", coz_semester=" + coz_semester +
                '}';
    }
}
