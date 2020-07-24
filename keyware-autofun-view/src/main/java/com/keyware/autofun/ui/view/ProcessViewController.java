package com.keyware.autofun.ui.view;

import com.keyware.autofun.service.task.IAsyncTaskService;
import com.keyware.autofun.ui.common.TitleUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 进程面板
 */
@FXMLController
public class ProcessViewController {
    private static final Logger logger = LoggerFactory.getLogger(ProcessViewController.class);
    @Autowired
    private IAsyncTaskService asyncTask;
    @Autowired
    private ThreadViewController threadView;
    @FXML
    private Pane pane;

    public void initView(TabPane content) {
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        TreeItem<String> treeItem = new TreeItem("加载中，请稍候...");
        root.getChildren().setAll(treeItem);
        asyncTask.task(root);
        TreeTableColumn<String, String> column = new TreeTableColumn<>();
        column.setMinWidth(800);

        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> p) ->
                new ReadOnlyStringWrapper(p.getValue().getValue()));

        TreeTableView<String> treeTableView = new TreeTableView<>(root);
        treeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 1) {
                    TreeItem<String> item = treeTableView.getSelectionModel().getSelectedItem();
                    if (null == item || null == item.getValue())
                        return;
                    String[] arr = item.getValue().split(" ");
                    String pid = arr[1];
                    String name = arr[2];
                    threadView.initView(content, pid, name);
                }
            }
        });
        treeTableView.getColumns().add(column);
        treeTableView.setShowRoot(true);
        pane = new StackPane();
        pane.getChildren().addAll(treeTableView);
        Tab tab = new Tab();
        tab.setText(TitleUtils.getInstance().getName("进程"));
        tab.setContent(pane);
        tab.setClosable(true);
        content.getTabs().add(tab);
        content.getSelectionModel().select(tab);
    }

}