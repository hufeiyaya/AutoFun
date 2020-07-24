package com.keyware.autofun.ui.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TitleUtils {

    private static TitleUtils instance = new TitleUtils();

    private TitleUtils() {
    }

    public static TitleUtils getInstance() {
        return instance;
    }

    private static AtomicInteger count;

    private Map<String, Object> map = new HashMap<>();

    public String getName(String name) {
        if (map.containsKey(name)) {
            count = (AtomicInteger) map.get(name);
            count.getAndIncrement();
        } else {
            count = new AtomicInteger(1);
        }
        map.put(name, count);
        String result = name + map.get(name);
        return result;
    }


}