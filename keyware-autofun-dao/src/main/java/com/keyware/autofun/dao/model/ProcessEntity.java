package com.keyware.autofun.dao.model;

import java.io.Serializable;
public class ProcessEntity implements Serializable {
    /**
     * 进程id
     **/
    private String pid;
    /**
     * 进程名称
     **/
    private String name;
    /**
     * 优先级
     **/
    private String pri;
    /**
     * 线程数
     **/
    private String thd;
    /**
     * 句柄数
     **/
    private String hnd;

    /**
     * 专用虚拟内存
     **/
    private String priv;

    /**
     * 专用虚拟内存峰值
     **/
    private String privPK;


    /**
     * cpu时间
     **/
    private String cpuTime;

    /**
     * 运行时间
     **/
    private String elapsedTime;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getThd() {
        return thd;
    }

    public void setThd(String thd) {
        this.thd = thd;
    }

    public String getHnd() {
        return hnd;
    }

    public void setHnd(String hnd) {
        this.hnd = hnd;
    }

    public String getPriv() {
        return priv;
    }

    public void setPriv(String priv) {
        this.priv = priv;
    }

    public String getPrivPK() {
        return privPK;
    }

    public void setPrivPK(String privPK) {
        this.privPK = privPK;
    }

    public String getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(String cpuTime) {
        this.cpuTime = cpuTime;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
