package com.keyware.autofun.service.task;

import javafx.scene.control.TreeItem;

public interface IAsyncTaskService {

    void task(TreeItem<String> root);

    void threadTask(TreeItem<String> root,String pid,String pname);
}
