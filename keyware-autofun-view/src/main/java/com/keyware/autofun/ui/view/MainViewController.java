package com.keyware.autofun.ui.view;


import com.keyware.autofun.ui.view.Edit.EditViewController;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@FXMLController
public class MainViewController {

    @Autowired
    private WindowViewController windowView;

    @Autowired
    private ProcessViewController processView;

    @Autowired
    private ThreadViewController threadView;

    @Autowired
    private SearchViewController searchView;

    @Autowired
    private EditViewController editView;

    @FXML
    private TabPane content;


    @FXML
    void openWindow(ActionEvent event) throws IOException {
        windowView.initView(content);
    }

    @FXML
    void openProcess(ActionEvent event) {
        processView.initView(content);
    }

    @FXML
    void openThread(ActionEvent event) {
        threadView.initView(content, null, null);
    }

    @FXML
    void openSearchWin(ActionEvent event) {
        searchView.initView();
    }

    @FXML
    void editWindow(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/EditView.fxml"));
        Tab tab = new Tab();
        tab.setText("编辑");
        tab.setContent(root);
        tab.setClosable(true);
        content.getTabs().add(tab);
        content.getSelectionModel().select(tab);
    }


}
