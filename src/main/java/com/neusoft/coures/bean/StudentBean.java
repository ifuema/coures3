package com.neusoft.coures.bean;

public class StudentBean {
    //vpn用户名
    private String vpnUserName;
    //vpn密码
    private String vpnPassWord;
    //作业平台用户名
    private String hwUserName;
    //作业平台密码
    private String hwPassWord;

    public StudentBean() {
        this.vpnUserName = "";
        this.vpnPassWord = "";
        this.hwUserName = "";
        this.hwPassWord = "";
    }

    public StudentBean(String vpnUserName, String vpnPassWord, String hwUserName, String hwPassWord) {
        this.vpnUserName = vpnUserName;
        this.vpnPassWord = vpnPassWord;
        this.hwUserName = hwUserName;
        this.hwPassWord = hwPassWord;
    }

    public String getVpnUserName() {
        return vpnUserName;
    }

    public void setVpnUserName(String vpnUserName) {
        this.vpnUserName = vpnUserName;
    }

    public String getVpnPassWord() {
        return vpnPassWord;
    }

    public void setVpnPassWord(String vpnPassWord) {
        this.vpnPassWord = vpnPassWord;
    }

    public String getHwUserName() {
        return hwUserName;
    }

    public void setHwUserName(String hwUserName) {
        this.hwUserName = hwUserName;
    }

    public String getHwPassWord() {
        return hwPassWord;
    }

    public void setHwPassWord(String hwPassWord) {
        this.hwPassWord = hwPassWord;
    }
}
