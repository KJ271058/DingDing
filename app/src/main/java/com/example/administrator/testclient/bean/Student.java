package com.example.administrator.testclient.bean;

public class Student {

    private String stuid;
    private String stupassword;
    private String stuname;
    private String stuhead;
    private String stumobile;
    private String stucarid;
    private String stusex;
    private String stusign;
    private Integer stumoney;
    private String stuclass;

    @Override
    public String toString() {
        return "Student{" +
                "stuid='" + stuid + '\'' +
                ", stupassword='" + stupassword + '\'' +
                ", stuname='" + stuname + '\'' +
                ", stuhead='" + stuhead + '\'' +
                ", stumobile='" + stumobile + '\'' +
                ", stucarid='" + stucarid + '\'' +
                ", stusex='" + stusex + '\'' +
                ", stusign='" + stusign + '\'' +
                ", stumoney=" + stumoney +
                ", stuclass='" + stuclass + '\'' +
                '}';
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getStupassword() {
        return stupassword;
    }

    public void setStupassword(String stupassword) {
        this.stupassword = stupassword;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStuhead() {
        return stuhead;
    }

    public void setStuhead(String stuhead) {
        this.stuhead = stuhead;
    }

    public String getStumobile() {
        return stumobile;
    }

    public void setStumobile(String stumobile) {
        this.stumobile = stumobile;
    }

    public String getStucarid() {
        return stucarid;
    }

    public void setStucarid(String stucarid) {
        this.stucarid = stucarid;
    }

    public String getStusex() {
        return stusex;
    }

    public void setStusex(String stusex) {
        this.stusex = stusex;
    }

    public String getStusign() {
        return stusign;
    }

    public void setStusign(String stusign) {
        this.stusign = stusign;
    }

    public Integer getStumoney() {
        return stumoney;
    }

    public void setStumoney(Integer stumoney) {
        this.stumoney = stumoney;
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }


}
