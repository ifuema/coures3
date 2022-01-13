package com.neusoft.coures.tool;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintLog {
    public static FileWriter fileWriter;
    public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 打印带时间的信息并保存到日志文件
     * @param msg
     */
    public static void print(String msg) {
        msg = sdf.format(new Date()) + "\n" + msg;
        try {
            PrintLog.fileWriter.write(msg + "\n");
            PrintLog.fileWriter.flush();
        } catch (Exception e) {
        } finally {
            System.out.println(msg);
        }
    }
}
