package com.keyware.autofun.ui.view;

import com.keyware.autofun.dao.common.MyUser32;
import com.keyware.autofun.service.window.IWindowService;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;

/**
 * 搜索面板
 */
@FXMLController
public class SearchViewController {
    private static final Logger logger = LoggerFactory.getLogger(SearchViewController.class);

    @Autowired
    private IWindowService windowService;

    private TextField titleFiled;
    private TextField handleField;
    private TextField squarField;
    private TextField styleField;
    private TextField clsField;

    public void initView() {

        Stage stage = new Stage();
        stage.setTitle("搜索窗口");

//        Label lable = new Label("查找程序工具");
//        lable.setAlignment(Pos.CENTER);
//        Pane title=new StackPane();
//        title.getChildren().addAll(lable);
        Pane pane = generateCircleNode("+");
        addNodeDragListener(pane);
        pane.setMaxHeight(100);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(5);
        grid.setPadding(new Insets(5, 5, 5, 5));
        createContent(grid);

        VBox root = new VBox(pane, grid);
        VBox.setVgrow(pane, Priority.ALWAYS);


        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void createContent(GridPane grid) {
        Label handle = new Label("句柄:");
        grid.add(handle, 0, 2);

        handleField = new TextField();
        grid.add(handleField, 1, 2);

        Label title = new Label("标题:");
        grid.add(title, 0, 3);

        titleFiled = new TextField();
        grid.add(titleFiled, 1, 3);

        Label cls = new Label("类:");
        grid.add(cls, 0, 4);

        clsField = new TextField();
        grid.add(clsField, 1, 4);

        Label style = new Label("样式:");
        grid.add(style, 0, 5);

        styleField = new TextField();
        grid.add(styleField, 1, 5);


        Label squar = new Label("矩形:");
        grid.add(squar, 0, 6);

        squarField = new TextField();
        grid.add(squarField, 1, 6);

    }

    private Pane generateCircleNode(String data) {
        Pane node = new StackPane();

        Circle circle = new Circle(15);
        circle.setStyle("-fx-fill: rgb(51,184,223)");

        Text text = new Text(data);
        text.setStyle("-fx-fill: rgb(93,93,93);-fx-font-weight: bold;");

        node.getChildren().addAll(circle, text);

        return node;
    }

    public void addNodeDragListener(Node node) {
        new DragListener(node).enableDrag(node);
    }

    class DragListener implements EventHandler<MouseEvent> {
        private double xOffset = 0;
        private double yOffset = 0;
        private Node node;

        public DragListener(Node node) {
            this.node = node;
        }

        @Override
        public void handle(MouseEvent event) {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                xOffset = event.getX();
                yOffset = event.getY();
            } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                double x = node.getTranslateX() + event.getX() - xOffset;
                double y = node.getTranslateY() + event.getY() - yOffset;
                listener();
                node.setTranslateX(x);
                node.setTranslateY(y);
            }
            //鼠标按压释放时 将点位恢复到初始坐标
            node.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
                node.setTranslateX(0);
                node.setTranslateY(0);
            });
        }

        public void enableDrag(Node node) {
            node.setOnMousePressed(this);
            node.setOnMouseDragged(this);
        }
    }

    /**
     * 监听鼠标坐标位置，获取坐标位置的窗口信息
     */
    private void listener() {
        WinDef.POINT point = new WinDef.POINT();
        MyUser32.INSTANCE.GetCursorPos(point);
        WinDef.HWND hwnd = MyUser32.INSTANCE.WindowFromPoint(point.x, point.y);
        setHandleField(hwnd.toString());
        WinDef.HWND s = MyUser32.INSTANCE.RealChildWindowFromPoint(hwnd, point.x, point.y);
        System.out.println(s);
        WinDef.RECT rect = new WinDef.RECT();
        MyUser32.INSTANCE.GetWindowRect(hwnd, rect);
        setSquar(rect.toString());
        String title = windowService.getWindowInfo(hwnd);
        setTitle(title);
        WinUser.WINDOWINFO info = new WinUser.WINDOWINFO();
        MyUser32.INSTANCE.GetWindowInfo(hwnd, info);
        setStyle(String.valueOf(info.dwStyle));
        WinUser.WNDCLASSEX m = new WinUser.WNDCLASSEX();
        byte[] className = new byte[512];
        MyUser32.INSTANCE.GetClassNameA(hwnd, className, 512);
        String name = new String(className, Charset.forName("gbk"));
        setClassName(name);

    }

    //设置句柄
    private void setHandleField(String handle) {
        handleField.setText(handle);
    }

    //设置标题
    private void setTitle(String title) {
        titleFiled.setText(title);
    }

    //设置类名
    private void setClassName(String name) {
        clsField.setText(name);
    }

    //设置样式
    private void setStyle(String style) {
        styleField.setText(style);
    }

    //设置矩形
    private void setSquar(String rect) {
        squarField.setText(rect);
    }


}
