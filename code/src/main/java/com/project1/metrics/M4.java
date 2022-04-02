package com.project1.metrics;

import com.project1.model.NoStringVectorOfCharacteristics;
import com.project1.model.VectorOfCharacteristics;
import java.util.ArrayList;
import java.util.List;

public class M4 {

    private static int n = 2;

    public NoStringVectorOfCharacteristics convert(VectorOfCharacteristics vectorOfCharacteristics) {
        return null;
    }

    public static double countSimilarity(String a, String b) {
        double N = Math.max(a.length(), b.length());
        List<String> A = split(a);
        List<String> B = split(b);
        int sum = 0;
        for (int i = 0; i < A.size(); i++) {
            if (B.contains(A.get(i)))
                sum++;
        }
        return 1 / ( N - n + 1) * sum;
    }

    private static List<String> split(String string) {
        List<String> res = new ArrayList();
        for (int i = 0; i < string.length() - n + 1; i++)
            res.add(string.substring(i, i + n));
        return res;
    }

}
