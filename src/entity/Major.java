package entity;

public class Major {
    private int maj_id;
    private String maj_name;
    private int inst_id;

    public Major() {
    }

    public Major(int maj_id, String maj_name, int inst_id) {
        this.maj_id = maj_id;
        this.maj_name = maj_name;
        this.inst_id = inst_id;
    }

    public int getMaj_id() {
        return maj_id;
    }

    public String getMaj_name() {
        return maj_name;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setMaj_name(String maj_name) {
        this.maj_name = maj_name;
    }
}
