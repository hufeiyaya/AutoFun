package com.keyware.autofun.service.thread.impl;

import com.keyware.autofun.dao.ThreadDao;
import com.keyware.autofun.dao.model.ThreadEntity;
import com.keyware.autofun.service.thread.IThreadService;
import javafx.scene.control.TreeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThreadServiceImpl implements IThreadService {

    private static final Logger logger = LoggerFactory.getLogger(ThreadServiceImpl.class);
    @Autowired
    private ThreadDao threadDao;

    @Override
    public List<TreeItem> getThreadList(String pid, String pname) {
        List<TreeItem> list = new ArrayList<>();
        List<ThreadEntity> result = new ArrayList<>();
        if (StringUtils.isEmpty(pid)) {
            result = threadDao.getThreadList();
        } else {
            result = threadDao.getThreadInfo(pid, pname);
        }

        for (ThreadEntity threadEntity : result) {
            if (!StringUtils.isEmpty(threadEntity)) {
                String value = "线程 " + threadEntity.getTid() + " " + threadEntity.getPname().toUpperCase();
                TreeItem<String> treeItem = new TreeItem(value);
                list.add(treeItem);
            }
        }
        return list;
    }
}
