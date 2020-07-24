package com.keyware.autofun.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.keyware.autofun.ui", "com.keyware.autofun.service", "com.keyware.autofun.dao"})
public class AutoFunApplication extends Application {

    private static final Logger logger = LoggerFactory.getLogger(AutoFunApplication.class);

    private ConfigurableApplicationContext context;
    private Parent rootNode;

    public static void main(String[] args) {
        //设置JNA编码
        System.setProperty("jna.encoding", "GBK");
        launch(args);
    }

    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(AutoFunApplication.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/MainView.fxml"));
        loader.setControllerFactory(context::getBean);
        rootNode = loader.load();
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AutoFun");
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }

        });
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }


}
