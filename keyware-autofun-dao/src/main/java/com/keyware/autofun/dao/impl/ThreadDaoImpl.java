package com.keyware.autofun.dao.impl;

import com.google.gson.Gson;
import com.keyware.autofun.dao.ProcessDao;
import com.keyware.autofun.dao.ThreadDao;
import com.keyware.autofun.dao.model.ProcessEntity;
import com.keyware.autofun.dao.model.ThreadEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ThreadDaoImpl implements ThreadDao {
    private static final Logger logger = LoggerFactory.getLogger(ThreadDaoImpl.class);

    @Autowired
    private ProcessDao processDao;

    @Override
    public List<ThreadEntity> getThreadList() {
        List<ProcessEntity> plist = processDao.getProcessList();
        List<ThreadEntity> threadlist = new ArrayList<>();
        for (ProcessEntity p : plist) {
            threadlist.addAll(getThreadInfo(p.getPid(),p.getName()));
        }
        return threadlist;

    }


    public List<ThreadEntity> getThreadInfo(String pid,String pname) {
        List<ThreadEntity> tlist = new ArrayList<>();
        BufferedReader out = null;
        try {
            // 创建系统进程
            String cmd = "pslist -d -nobanner " + pid;
            Process p = Runtime.getRuntime().exec(cmd);
            out = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
            String readLine;
            while ((readLine = out.readLine()) != null) {
                String[] arr = readLine.trim().split("\\s+");
                ThreadEntity threadEntity=new ThreadEntity();
                if(arr.length==7)
                {
                    threadEntity.setPid(pid);
                    threadEntity.setTid(arr[0]);
                    threadEntity.setPname(pname);
                    threadEntity.setTname("null");
                    tlist.add(threadEntity);
                }
                System.out.println(new Gson().toJson(threadEntity));


            }
        } catch (IOException e) {
            logger.error("getThreadList has error", e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tlist;
    }

}
