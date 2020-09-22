package com.smietao.gdut.util;

import com.smietao.gdut.keyword.Keyword;

import java.util.List;

public class CalculateCosSim {

    public static Double calculateCosineSimilarity(List<Keyword> originalList, List<Keyword> plagiarizeList) {
        double v = 0;
        double v1 = 0;
        double v2 = 0;
        for (Keyword oriKeyword : originalList) {
            for (Keyword plaKeyword : plagiarizeList) {
                if (oriKeyword.getName().equals(plaKeyword.getName())) {
                    System.out.println("相同的关键词为" + oriKeyword.getName() );
                    double oriTfidfvalue = oriKeyword.getTfidfvalue();
                    double plaTfidfvalue = plaKeyword.getTfidfvalue();
                    // 如果高频词相等，计算
                    v1 += oriTfidfvalue * oriTfidfvalue;
                    v2 += plaTfidfvalue * plaTfidfvalue;
                    v += oriTfidfvalue * plaTfidfvalue;
                    // 计算完后跳出内层循环，找下一个词
                    break;
                }
            }
        }
        return v / Math.sqrt(v1 * v2);
    }
}
