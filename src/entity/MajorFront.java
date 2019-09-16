package entity;

public class MajorFront extends Major {
    private int class_num;
    private int stu_num;

    public MajorFront(Major major, int class_num, int stu_num) {
        super(major.getMaj_id(), major.getMaj_name(), major.getInst_id());
        this.class_num = class_num;
        this.stu_num = stu_num;
    }

    public int getClass_num() {
        return class_num;
    }

    public void setClass_num(int class_num) {
        this.class_num = class_num;
    }

    public int getStu_num() {
        return stu_num;
    }

    public void setStu_num(int stu_num) {
        this.stu_num = stu_num;
    }
}
