package entity;

public class InstituteFront extends Institute{
    private int maj_num;
    private int stu_num;

    public InstituteFront(Institute institute, int maj_num, int stu_num) {
        super(institute.getInst_id(), institute.getInst_name());
        this.maj_num = maj_num;
        this.stu_num = stu_num;
    }

    public int getMaj_num() {
        return maj_num;
    }

    public void setMaj_num(int maj_num) {
        this.maj_num = maj_num;
    }

    public int getStu_num() {
        return stu_num;
    }

    public void setStu_num(int stu_num) {
        this.stu_num = stu_num;
    }
}
