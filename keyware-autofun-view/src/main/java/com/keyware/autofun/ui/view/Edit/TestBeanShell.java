package com.keyware.autofun.ui.view.Edit;

import bsh.EvalError;
import bsh.Interpreter;
import com.keyware.autofun.ui.common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;

public class TestBeanShell {

    private static final Logger logger = LoggerFactory.getLogger(TestBeanShell.class);

    public static void main(String[] args) throws Exception {

        String filepath = FileUtils.getProjectPath() + "\\keyware-autofun-view\\src\\main\\resources\\script\\test.txt";
        Interpreter interpreter = new Interpreter();
        List<String> list = FileUtils.readFileContent(filepath);
        interpreter.eval("import java.util.*;");
        interpreter.eval("import com.keyware.autofun.ui.common.af;");
        list.forEach(e -> {
            if (!StringUtils.isEmpty(e)) {
                try {
                    interpreter.eval(e);
                } catch (EvalError evalError) {
                    evalError.printStackTrace();
                }
            }

        });

    }
}
