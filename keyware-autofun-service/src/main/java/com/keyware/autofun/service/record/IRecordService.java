package com.keyware.autofun.service.record;

import javafx.scene.control.TreeItem;

import java.util.List;

public interface IRecordService {

    /**
     * 开始
     */
    void start();

    /**
     * 暂停
     */
    void suspend();

    /**
     * 继续
     */
    void proceed();

    /**
     * 停止
     */
    void stop();
}
