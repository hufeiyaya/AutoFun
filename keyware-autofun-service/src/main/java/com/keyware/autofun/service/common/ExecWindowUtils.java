package com.keyware.autofun.service.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecWindowUtils {

    public static void readBat(String path,String cmd)
    {
        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.directory(new File(path));
        int runningStatus = 0;
        String s = null;
        try {
            Process p = pb.start();
            try {
                runningStatus = p.waitFor();
            } catch (InterruptedException e) {
            }

        } catch (IOException e) {
        }
        if (runningStatus != 0) {
        }
        return;
    }


	public static void runCmd(String cmd) {
        BufferedReader br = null;
        try {  
            Process p = Runtime.getRuntime().exec(cmd);
            br = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
            String line = null;  
            StringBuilder sb = new StringBuilder();  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }  
            System.out.println(sb.toString());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
        finally  
        {  
            if (br != null)  
            {  
                try {  
                    br.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
    public static void main(String[] args) {

    }
}
