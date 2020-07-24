package com.keyware.autofun.dao.impl;

import com.keyware.autofun.dao.ProcessDao;
import com.keyware.autofun.dao.model.ProcessEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessDaoImpl implements ProcessDao {
    private static final Logger logger = LoggerFactory.getLogger(ProcessDaoImpl.class);

    @Override
    public List<ProcessEntity> getProcessList() {
        List<ProcessEntity> list = new ArrayList<>();
        BufferedReader out = null;
        try {
            // 创建系统进程
            String cmd ="pslist -m -nobanner";
            Process p = Runtime.getRuntime().exec(cmd);
            out = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
            String readLine;
            while ((readLine = out.readLine()) != null) {
                ProcessEntity process = new ProcessEntity();
                String[]  arr=readLine.split("\\s+");
                //Name                Pid      VM      WS    Priv Priv Pk   Faults   NonP Page
                //Idle                  0       8       8      56      56        8      0    0
                if(arr.length==9)
                {
                    process.setName(arr[0]);
                    process.setPid(arr[1]);
                    process.setPriv(arr[4]);
                    process.setPrivPK(arr[5]);
                    list.add(process);
                }
            }

        } catch (IOException e) {
            logger.error("getProcessList has error", e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
