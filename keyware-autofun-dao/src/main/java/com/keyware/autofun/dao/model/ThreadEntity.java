package com.keyware.autofun.dao.model;

import java.io.Serializable;

public class ThreadEntity implements Serializable {

    /**
     * 进程id
     **/
    private String pid;
    /**
     * 线程id
     */
    private String tid;
    /**
     * 进程名称
     **/
    private String tname;
    /**
     * 所属进程名称
     */
    private String pname;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }


}
