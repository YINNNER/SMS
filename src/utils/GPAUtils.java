package utils;

public class GPAUtils {
    public static float getGPAByScore(float score) {
        float gpa;
        if (score >= 90) {
            gpa = 4.0f;
        }
        else if (score >= 85)
            gpa = 3.7f;
        else if (score >= 82)
            gpa = 3.3f;
        else if (score >= 78)
            gpa = 3.0f;
        else if (score >= 75)
            gpa = 2.7f;
        else if (score >= 72)
            gpa = 2.3f;
        else if (score >= 68)
            gpa = 2.0f;
        else if (score >= 64)
            gpa = 1.5f;
        else if (score >= 60)
            gpa = 1.0f;
        else
            gpa = 0f;
        return gpa;
    }

    public static void main(String[] args) {
        float gpa = GPAUtils.getGPAByScore(65);
        System.out.println(gpa);
    }
}
