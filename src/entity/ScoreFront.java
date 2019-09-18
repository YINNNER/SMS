package entity;

public class ScoreFront extends Course {
    private int stu_id;
    private float score;
    private String tch_name;
    private String inst_name;

    public ScoreFront(int stu_id, Course course, Score score, Teacher teacher, Institute institute) {
        super(course.getCoz_id(), course.getCoz_name(), course.getCoz_place(), course.getCoz_credit(), course.getInst_id(), course.getMaj_id(), course.getTch_id(), course.getCoz_time(), course.getCoz_year(), course.getCoz_semester());
        this.stu_id = stu_id;
        this.score = score.getScore();
        this.tch_name = teacher.getTch_name();
        this.inst_name = institute.getInst_name();
    }

    public String getInst_name() {
        return inst_name;
    }

    public void setInst_name(String inst_name) {
        this.inst_name = inst_name;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getTch_name() {
        return tch_name;
    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
    }
}
