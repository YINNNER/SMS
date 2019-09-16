package entity;

public class Institute {
    private int inst_id;
    private String inst_name;

    public Institute() {
    }

    public Institute(int inst_id, String inst_name) {
        this.inst_id = inst_id;
        this.inst_name = inst_name;
    }

    public int getInst_id() {
        return inst_id;
    }

    public String getInst_name() {
        return inst_name;
    }

    public void setInst_name(String inst_name) {
        this.inst_name = inst_name;
    }

    @Override
    public String toString() {
        return "Institute{" +
                "inst_id=" + inst_id +
                ", inst_name='" + inst_name + '\'' +
                '}';
    }
}
