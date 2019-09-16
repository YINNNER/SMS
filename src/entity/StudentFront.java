package entity;

public class StudentFront extends Student {
    private String inst_name;
    private String maj_name;
    private String class_name;

    public StudentFront(Student student, Institute institute, Major major, Class cls) {
        super(student.getStu_id(), student.getInst_id(), student.getMaj_id(), student.getClass_id(), student.getStu_name(), student.getStu_sex(), student.getStu_birth_date(), student.getStu_birth_place(), student.getStu_political());
        this.inst_name = institute.getInst_name();
        this.maj_name = major.getMaj_name();
        this.class_name = cls.getClass_name();
    }

    public String getInst_name() {
        return inst_name;
    }

    public void setInst_name(String inst_name) {
        this.inst_name = inst_name;
    }

    public String getMaj_name() {
        return maj_name;
    }

    public void setMaj_name(String maj_name) {
        this.maj_name = maj_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
