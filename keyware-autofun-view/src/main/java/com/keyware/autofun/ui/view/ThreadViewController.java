package com.keyware.autofun.ui.view;

import com.keyware.autofun.service.task.IAsyncTaskService;
import com.keyware.autofun.ui.common.TitleUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 进程面板
 */
@FXMLController
public class ThreadViewController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(ThreadViewController.class);
    @Autowired
    private IAsyncTaskService asyncTask;
    @FXML
    private Pane pane;

    public void initView(TabPane content, String pid, String pname) {
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        TreeItem<String> treeItem = new TreeItem("加载中，请稍候...");
        root.getChildren().setAll(treeItem);
        asyncTask.threadTask(root, pid, pname);
        TreeTableColumn<String, String> column = new TreeTableColumn<>();
        column.setMinWidth(800);
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> p) ->
                new ReadOnlyStringWrapper(p.getValue().getValue()));

        TreeTableView<String> treeTableView = new TreeTableView<>(root);
        treeTableView.getColumns().add(column);
        treeTableView.setShowRoot(true);
        pane = new StackPane();
        pane.getChildren().addAll(treeTableView);
        Tab tab = new Tab();
        tab.setText(TitleUtils.getInstance().getName("线程"));
        tab.setContent(treeTableView);
        tab.setClosable(true);
        content.getTabs().add(tab);
        content.getSelectionModel().select(tab);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
