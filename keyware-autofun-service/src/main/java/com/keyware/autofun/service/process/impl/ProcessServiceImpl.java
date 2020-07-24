package com.keyware.autofun.service.process.impl;

import com.keyware.autofun.dao.ProcessDao;
import com.keyware.autofun.dao.model.ProcessEntity;
import com.keyware.autofun.service.process.IProcessService;
import javafx.scene.control.TreeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessServiceImpl implements IProcessService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);
    @Autowired
    private ProcessDao processDao;


    @Override
    public List<TreeItem> getProcessList() {
        List<TreeItem> list = new ArrayList<>();
        List<ProcessEntity> result = processDao.getProcessList();
        for (ProcessEntity processEntity : result) {
            String value = "进程 " + processEntity.getPid() + " " + processEntity.getName().toUpperCase();
            TreeItem<String> treeItem = new TreeItem(value);
            list.add(treeItem);
        }
        return list;
    }
}
