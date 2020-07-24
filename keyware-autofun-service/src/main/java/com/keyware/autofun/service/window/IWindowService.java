package com.keyware.autofun.service.window;

import com.sun.jna.platform.win32.WinDef;
import javafx.scene.control.TreeItem;

import java.util.List;

public interface IWindowService {

    List<TreeItem> getWindowList(String pid, String pname);

    String getWindowInfo(WinDef.HWND hwnd);

}
