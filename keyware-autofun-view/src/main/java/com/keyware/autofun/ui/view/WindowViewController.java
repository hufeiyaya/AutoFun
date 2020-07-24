package com.keyware.autofun.ui.view;

import com.keyware.autofun.service.window.IWindowService;
import com.keyware.autofun.ui.common.TitleUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 窗口面板
 */
@FXMLController
public class WindowViewController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(WindowViewController.class);
    @Autowired
    private IWindowService windowService;
    @FXML
    private Pane pane;


    public void initView(TabPane content) {
        final TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        List list = windowService.getWindowList(null, null);
        root.getChildren().setAll(list);

        TreeTableColumn<String, String> column = new TreeTableColumn<>();
        column.setSortable(false);
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
                    String hwmn = arr[1];
                    System.out.println(hwmn);
                }
            }
        });

        treeTableView.getColumns().add(column);
        treeTableView.setShowRoot(true);
        pane = new StackPane();
        pane.getChildren().addAll(treeTableView);
        Tab tab = new Tab();
        tab.setText(TitleUtils.getInstance().getName("窗口"));
        tab.setContent(pane);
        tab.setClosable(true);
        content.getTabs().add(tab);
        //选中此Tab
        content.getSelectionModel().select(tab);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
