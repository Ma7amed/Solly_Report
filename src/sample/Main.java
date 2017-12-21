package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main_page.fxml"));
        primaryStage.setTitle("TT&WO Detail Query Tool");
        primaryStage.setScene(new Scene(root));
        Image applicationIcon = new Image(getClass().getResourceAsStream("images/analytics.png"));
        primaryStage.getIcons().add(applicationIcon);
        primaryStage.setHeight(500);
        primaryStage.setWidth(700);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(700);
        primaryStage.setResizable(false);

       // primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
