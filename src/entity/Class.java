package entity;

public class Class {
    private int class_id;
    private String class_name;
    private int maj_id;
    private int inst_id;

    public int getClass_id() {
        return class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public int getMaj_id() {
        return maj_id;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Class(int class_id, String class_name, int maj_id, int inst_id) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.maj_id = maj_id;
        this.inst_id = inst_id;
    }

    public Class(int class_id) {
        this.class_id = class_id;
    }

    public Class() {
    }
}
