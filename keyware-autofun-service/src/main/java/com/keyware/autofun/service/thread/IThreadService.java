package com.keyware.autofun.service.thread;

import javafx.scene.control.TreeItem;

import java.util.List;

public interface IThreadService {

    List<TreeItem> getThreadList(String pid, String pname);
}
