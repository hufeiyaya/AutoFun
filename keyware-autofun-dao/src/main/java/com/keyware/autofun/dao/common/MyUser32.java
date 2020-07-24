package com.keyware.autofun.dao.common;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;

public interface MyUser32 extends StdCallLibrary, WinUser {

    MyUser32 INSTANCE = (MyUser32) Native.loadLibrary("user32", MyUser32.class);

    /**
     * 获取窗口数量
     */
    int GetWindowTextA(HWND var1, byte[] var2, int var3);

    /**
     * 获取窗口内容
     */
    int GetWindowTextLengthA(HWND var1);

    /**
     * 获取枚举窗口信息
     */
    boolean EnumWindows(WNDENUMPROC var1, Pointer var2);

    /**
     * 根据坐标获取窗口的句柄
     */
    HWND WindowFromPoint(int x, int y);

    HWND WindowFromPoint(POINT p);

    /**
     * 获取子窗口
     *
     * @param hwndParent
     * @param x
     * @param y
     * @return
     */
    HWND RealChildWindowFromPoint(HWND hwndParent, int x, int y);

    /**
     * 获取当前鼠标位置
     *
     * @param var1
     * @return
     */
    boolean GetCursorPos(POINT var1);

    /**
     * 获取最上层窗口句柄
     */
    HWND GetTopWindow(HWND hwnd);

    /**
     * 获取最前面的窗口
     */
    HWND GetForegroundWindow();

    /**
     * 获取窗口坐标
     */
    boolean GetWindowRect(HWND var1, RECT var2);

    /**
     * 获取窗口信息
     */
    boolean GetWindowInfo(HWND var1, WINDOWINFO var2);

    /**
     * 获取窗口类名称
     */
    int GetClassNameA(HWND hwnd, byte[] var2, int length);


    /**
     * 1 所有窗口列表
     * 1.1 窗口详细信息
     * 1.2 根据条件获取窗口信息
     * 2 所有进程列表
     * 3 所有线程列表
     *
     */

}