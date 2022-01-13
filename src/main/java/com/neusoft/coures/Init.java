package com.neusoft.coures;

import com.neusoft.coures.bean.StudentBean;
import com.neusoft.coures.tool.PrintLog;
import com.neusoft.coures.view.View;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Init {
    //登录账号信息
    public static StudentBean student;

    private View view = new View();

    /**
     * 初始化所有配置
     */
    public void initAll() {
        //初始化日志文件
        this.initLog();
        //初始化读取xml中登录账号信息
        this.initXML();
    }

    /**
     * 初始化XML文件，读取账号信息
     */
    public void initXML() {
        //创建构造器
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("init.xml");
        try {
            //读入文件
            Document document = (Document) builder.build(xmlFile);
            //获得根节点
            Element rootNode = document.getRootElement();
            //获得根节点中student子节点数组
            List list = rootNode.getChildren("student");
            //遍历数组将账号信息封装
            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                String vpnUserName = node.getChildText("vpnUserName");
                String vpnPassWord = node.getChildText("vpnPassWord");
                String hwUserName = node.getChildText("hwUserName");
                String hwPassWord = node.getChildText("hwPassWord");
                Init.student = new StudentBean(vpnUserName, vpnPassWord, hwUserName, hwPassWord);
            }
        } catch (IOException io) {
            //重置配置文件
            this.resetXML();
            Init.student = new StudentBean();
        } catch (JDOMException jdomex) {
            //如果有同名文件夹，对命名冲突文件夹改名，若没有则修复文件
            if (xmlFile.isDirectory()) {
                String xmlNewName = String.valueOf(System.currentTimeMillis());
                if (xmlFile.renameTo(new File(xmlNewName))) {
                    PrintLog.print("账号配置文件命名冲突！\n已将 init.xml 重命名为 " + xmlNewName);
                }
                this.resetXML();
            } else {
                PrintLog.print("配置文件无法正确解析！\n配置文件可能格式错误或损坏");
                //输入是否需要修复配置文件
                if (view.dsu("是否需要重置配置文件？")) {
                    if (this.resetXML()) {
                        PrintLog.print("配置文件修复成功。");
                    }
                }
            }
            Init.student = new StudentBean();
        }
    }

    /**
     * 重置XML配置文件
     * @return 是否重置成功
     */
    private boolean resetXML(){
//        //使用xml写入器重写配置文件
//        Element rootE = new Element("parameters");//创建根元素
//        Element e1 = new Element("student");
//        Element evpnUserName1 = new Element("vpnUserName");
//        Element evpnPassWord1 = new Element("vpnPassWord");
//        Element ehwUserName1 = new Element("hwUserName");
//        Element ehwPassWord1 = new Element("hwPassWord");
//        e1.addContent(evpnUserName1).addContent(evpnPassWord1).addContent(ehwUserName1).addContent(ehwPassWord1);//添加子元素
//        rootE.addContent(e1);
//        Document d = new Document(rootE);//根据根元素创建Document,以便后续用
//        XMLOutputter xop = new XMLOutputter();//用来创建xml文件，输出xml元素的
//        try {
//            xop.output(d, new FileOutputStream("init.xml"));
//        } catch (IOException e) {
//            PrintLog.print("账号配置文件初始化加载失败！\n当前目录或文件无权限，建议重新下载程序到其他目录运行");
//        }

        //使用FileWriter重写配置文件
        try {
            FileWriter XMLWriter = new FileWriter(new File("init.xml"), false);
            XMLWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            XMLWriter.write("<parameters>\n");
            XMLWriter.write("    <student>\n");
            XMLWriter.write("        //vpn用户名\n");
            XMLWriter.write("        <vpnUserName></vpnUserName>\n");
            XMLWriter.write("        //vpn密码\n");
            XMLWriter.write("        <vpnPassWord></vpnPassWord>\n");
            XMLWriter.write("        //作业平台用户名\n");
            XMLWriter.write("        <hwUserName></hwUserName>\n");
            XMLWriter.write("        //作业平台密码\n");
            XMLWriter.write("        <hwPassWord></hwPassWord>\n");
            XMLWriter.write("    </student>\n");
            XMLWriter.write("</parameters>");
            XMLWriter.flush();
            return true;
        } catch (IOException e) {
            PrintLog.print("账号配置文件初始化加载失败！\n当前目录或文件无权限，建议重新下载程序到其他目录运行");
            return false;
        }
    }

    /**
     * 初始化日志文件
     */
    public void initLog() {
        File logFile = new File("coures.log");
        //对命名冲突文件夹改名
        if (logFile.isDirectory()) {
            String logNewName = String.valueOf(System.currentTimeMillis());
            if (logFile.renameTo(new File(logNewName))) {
                PrintLog.print("日志文件命名冲突！\n已将 coures.log 重命名为 " + logNewName);
            }
        }
        try {
            PrintLog.fileWriter = new FileWriter(logFile, true);
        } catch (IOException e) {
            PrintLog.print("日志文件初始化加载失败，将不会打印日志！\n当前目录或文件无权限，建议重新下载程序到其他目录运行");
        }
    }
}
