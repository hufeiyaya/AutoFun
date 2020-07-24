package com.keyware.autofun.dao.impl;

import com.keyware.autofun.dao.WindowDao;
import com.keyware.autofun.dao.common.MyUser32;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class WindowDaoImpl implements WindowDao {

    private static final Logger logger = LoggerFactory.getLogger(WindowDaoImpl.class);

    @Override
    public List<String> getWindowList(String pid, String pname) {
        List<String> result = new ArrayList<>();
        MyUser32.INSTANCE.EnumWindows(new WinUser.WNDENUMPROC() {
            int count = 0;
            @Override
            public boolean callback(WinDef.HWND hWnd, Pointer arg1) {

                byte[] windowText = new byte[512];
                count = MyUser32.INSTANCE.GetWindowTextA(hWnd, windowText, 512);
                String wText = Native.toString(windowText);

                if (wText.isEmpty()) {
                    return true;
                }
                String str = hWnd + " " + count + " " + wText;
                result.add(str);
                return true;
            }
        }, null);
        return result;
    }


    @Override
    public String getWindowInfo(WinDef.HWND hwnd) {
        if(null==hwnd)
        {
            hwnd = MyUser32.INSTANCE.GetForegroundWindow();
        }

        byte[] buf = new byte[MyUser32.INSTANCE.GetWindowTextLengthA(hwnd) + 1];
        MyUser32.INSTANCE.GetWindowTextA(hwnd, buf, buf.length);
        String title = new String(buf, Charset.forName("gbk"));

        return title;
    }


}
