package com.neusoft.coures.view;

import com.neusoft.coures.tool.PrintLog;

import java.util.Scanner;

public class View {
     private Scanner sc = new Scanner(System.in);

    /**
     * 双选器
     * @param msg 提示信息，格式：是否XXX
     * @return
     */
     public boolean dsu(String msg) {
         while (true) {
             PrintLog.print(msg + "\nYes(Y) / NO(N)");
             String i = sc.next();
             if (i.equalsIgnoreCase("Yes") || i.equalsIgnoreCase("Y")) {
                 return true;
             } else if (i.equalsIgnoreCase("No") || i.equalsIgnoreCase("N")) {
                 return false;
             }
             PrintLog.print("输入错误，请重新输入！");
         }
     }
}
