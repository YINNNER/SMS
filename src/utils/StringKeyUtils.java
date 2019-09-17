package utils;

import java.util.ArrayList;
import java.util.List;

public class StringKeyUtils {
    public static String encode(int year, int semester) {
        return year + String.valueOf(semester);
    }

    public static List<Integer> decode(String concatString) {
        String yearString = concatString.substring(0, 4);
        String semesterString = concatString.substring(4, 5);

        List<Integer> keys = new ArrayList<>();
        keys.add(Integer.parseInt(yearString));
        keys.add(Integer.parseInt(semesterString));
        return keys;
    }

    public static void main(String[] args) {
        System.out.println(StringKeyUtils.encode(2019, 3));
        System.out.println(StringKeyUtils.decode("20193"));
    }
}
