package com.smietao.gdut;

import com.smietao.gdut.keyword.Keyword;
import com.smietao.gdut.keyword.TFIDFAnalyzer;
import com.smietao.gdut.util.CalculateCosSim;
import com.smietao.gdut.util.ReadTxt;

import java.io.*;
import java.text.DecimalFormat;
import java.util.List;


public class App {
    public static void main(String[] args) throws IOException {
        int length = args.length;
        if (length != 3) {
            // 参数不是三个，说明命令入参错误
            throw new RuntimeException("命令行入参数量有误！");
        }
        // 原文绝对路径
        String orgPath = args[0];
        // 抄袭文绝对路径
        String orgAddPath = args[1];
        // 答案文件输出绝对路径
        String outPutPath = args[2];
        File orgFile = new File(orgPath);
        File orgAddFile = new File(orgAddPath);
        File outPutFile = new File(outPutPath);
        // 读取txt文件中的内容 转化为String类型
        String original = ReadTxt.txt2String(orgFile).trim();
        String plagiarize = ReadTxt.txt2String(orgAddFile).trim();
        // 定义关键词个数
        int topN = 20;
        // 提取文章中的20个关键词和词频
        TFIDFAnalyzer tfidfAnalyzer = new TFIDFAnalyzer();
        List<Keyword> originalList = tfidfAnalyzer.analyze(original, topN);
        List<Keyword> plagiarizeList = tfidfAnalyzer.analyze(plagiarize, topN);

        // 输出答案到结果文件中
        FileOutputStream fos = new FileOutputStream(outPutFile);
        OutputStreamWriter dos = new OutputStreamWriter(fos);
        // 计算重复率
        Double repetitiveRate = CalculateCosSim.calculateCosineSimilarity(originalList, plagiarizeList);
        // 精确到小数点后2位
        DecimalFormat format = new DecimalFormat("0.00");
        String repetitiveRateStr = format.format(repetitiveRate);
        // 写入ans.txt文件
        dos.write(repetitiveRateStr);
        dos.close();
    }
}
