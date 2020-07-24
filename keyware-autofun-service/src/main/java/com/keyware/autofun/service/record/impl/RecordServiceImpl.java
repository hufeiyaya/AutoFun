package com.keyware.autofun.service.record.impl;

import com.keyware.autofun.dao.ProcessDao;
import com.keyware.autofun.dao.model.ProcessEntity;
import com.keyware.autofun.service.process.IProcessService;
import com.keyware.autofun.service.record.IRecordService;
import javafx.scene.control.TreeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements IRecordService {

    private static final Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class);


    @Override
    public void start() {

    }

    @Override
    public void suspend() {

    }

    @Override
    public void proceed() {

    }

    @Override
    public void stop() {

    }
}
