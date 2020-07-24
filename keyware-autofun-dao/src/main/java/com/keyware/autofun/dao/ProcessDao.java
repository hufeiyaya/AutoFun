package com.keyware.autofun.dao;

import com.keyware.autofun.dao.model.ProcessEntity;

import java.util.List;

public interface ProcessDao {
    /**
     *   获取当前系统中所有进程
     */
    List<ProcessEntity> getProcessList();
}
