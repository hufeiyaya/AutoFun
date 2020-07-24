package com.keyware.autofun.ui.view.Record;

import com.keyware.autofun.service.record.IRecordService;
import com.keyware.autofun.service.window.IWindowService;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 脚本录制
 */
@Service
public class RecordView {
    private static final Logger logger = LoggerFactory.getLogger(RecordView.class);

    @Autowired
    private IRecordService recordService;


    public void initView() {
        Stage stage = new Stage();
        stage.setTitle("录制");

        Pane pane = new StackPane();
        //页面
        Scene scene = new Scene(pane, 700, 300);
        stage.setScene(scene);
        stage.show();
    }


}
