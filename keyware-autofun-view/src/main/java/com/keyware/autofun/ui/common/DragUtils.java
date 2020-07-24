package com.keyware.autofun.ui.common;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class DragUtils {
    public static  void addNodeDragListener(Node node)  {
       new DragListener(node).enableDrag(node);
    }

    static class DragListener implements EventHandler<MouseEvent> {
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
}
