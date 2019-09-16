package entity;

public class CourseFront extends Course {

    private String tch_name;

    public CourseFront(Course course, Teacher teacher) {
        super(course.getCoz_id(), course.getCoz_name(), course.getCoz_place(), course.getCoz_credit(), course.getInst_id(), course.getMaj_id(), course.getTch_id(), course.getCoz_time(), course.getCoz_year(), course.getCoz_semester());
        this.tch_name = teacher.getTch_name();
    }

    public String getTch_name() {
        return tch_name;
    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
    }
}
