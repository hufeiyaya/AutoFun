package com.keyware.autofun.service.task.impl;

import com.keyware.autofun.service.process.IProcessService;
import com.keyware.autofun.service.task.IAsyncTaskService;
import com.keyware.autofun.service.thread.IThreadService;
import javafx.scene.control.TreeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableAsync
public class AsyncTaskServiceImpl implements IAsyncTaskService {

    @Autowired
    private IProcessService processService;

    @Autowired
    private IThreadService threadService;

    @Async
    @Override
    public void task(TreeItem<String> root)
    {
        List list = processService.getProcessList();
        root.getChildren().setAll(list);
    }

    @Async
    @Override
    public void threadTask(TreeItem<String> root,String pid,String pname) {

        List list = threadService.getThreadList(pid,pname);
        root.getChildren().setAll(list);
    }
}
