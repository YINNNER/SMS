package entity;

public class Score {
    private int stu_id;
    private int coz_id;
    private Float score;

    public Score() {
    }

    public Score(int stu_id, int coz_id) {
        this.stu_id = stu_id;
        this.coz_id = coz_id;
    }

    public Score(int stu_id, int coz_id, float score) {
        this.stu_id = stu_id;
        this.coz_id = coz_id;
        this.score = score;
    }

    public int getStu_id() {
        return stu_id;
    }

    public int getCoz_id() {
        return coz_id;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "stu_id=" + stu_id +
                ", coz_id=" + coz_id +
                ", score=" + score +
                '}';
    }
}
