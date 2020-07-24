package com.keyware.autofun.service.window.impl;

import com.keyware.autofun.dao.WindowDao;
import com.keyware.autofun.service.window.IWindowService;
import com.sun.jna.platform.win32.WinDef;
import javafx.scene.control.TreeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WindowServiceImpl implements IWindowService {

    private static final Logger logger = LoggerFactory.getLogger(WindowServiceImpl.class);
    @Autowired
    private WindowDao windowDao;

    @Override
    public List<TreeItem> getWindowList(String pid,String pname) {
        List<TreeItem> list = new ArrayList<>();
        List<String> result = windowDao.getWindowList(pid,pname);
        for (String str:result) {
                String value ="窗口 " + str ;
                TreeItem<String> treeItem = new TreeItem(value);
                list.add(treeItem);
        }
        return list;
    }

    @Override
    public String getWindowInfo(WinDef.HWND hwnd) {
        return  windowDao.getWindowInfo(hwnd);
    }

}
