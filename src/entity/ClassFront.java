package entity;

public class ClassFront extends Class {
    private int stu_num;

    public ClassFront(Class cls, int stu_num) {
        super(cls.getClass_id(), cls.getClass_name(), cls.getMaj_id(), cls.getInst_id());
        this.stu_num = stu_num;
    }

    public int getStu_num() {
        return stu_num;
    }

    public void setStu_num(int stu_num) {
        this.stu_num = stu_num;
    }
}
