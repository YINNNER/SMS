package entity;

public class AnalysisFront {
    private int year;
    private int semester;
    private int credit;
    private float gpa;

    public AnalysisFront(int year, int semester, int credit, float gpa) {
        this.year = year;
        this.semester = semester;
        this.credit = credit;
        this.gpa = gpa;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }
}
