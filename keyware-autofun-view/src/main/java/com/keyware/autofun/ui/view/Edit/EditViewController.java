package com.keyware.autofun.ui.view.Edit;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@FXMLController
public class EditViewController implements Initializable {

    @FXML
    private TreeView<String> tv;

    @FXML
    private TreeItem<String> item;


    @FXML
    void addProject(ActionEvent event) {
        Dialog dialog=new Dialog();

    }

    @FXML
    void importProject(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTreeView();
    }

    private void initTreeView() {
        item = new TreeItem<>("root");
        tv.setRoot(item);
        item.setExpanded(false);
        TreeItem<String> i1 = new TreeItem<>("电影");
        TreeItem<String> i2 = new TreeItem<>("音乐");
        TreeItem<String> i3 = new TreeItem<>("游戏");
        item.getChildren().addAll(i1, i2, i3);
        TreeItem<String> i4 = new TreeItem<>("荡寇风云");
        TreeItem<String> i5 = new TreeItem<>("变形金刚5");
        i1.setExpanded(false);
        i1.getChildren().addAll(i4, i5);

    }
}
