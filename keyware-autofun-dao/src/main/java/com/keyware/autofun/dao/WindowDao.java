package com.keyware.autofun.dao;

import com.sun.jna.platform.win32.WinDef;

import java.util.List;

public interface WindowDao {
    List<String> getWindowList(String pid, String pname);


    String getWindowInfo(WinDef.HWND hwnd);

}
