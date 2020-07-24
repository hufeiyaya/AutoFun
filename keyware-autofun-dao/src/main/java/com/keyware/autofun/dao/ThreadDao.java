package com.keyware.autofun.dao;

import com.keyware.autofun.dao.model.ThreadEntity;

import java.util.List;

public interface ThreadDao {
    List<ThreadEntity> getThreadList();

    List<ThreadEntity> getThreadInfo(String pid,String pname);
}
