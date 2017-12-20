/**
 * Sample Skeleton for 'main_login.fxml' Controller Class
 */

package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainLoginController {
    final Delta dragDelta = new Delta();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginBtn"
    private Button loginBtn; // Value injected by FXMLLoader

    @FXML // fx:id="layout"
    private StackPane layout; // Value injected by FXMLLoader

    @FXML // fx:id="closeBtn"
    private Button closeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="userField"
    private TextField userField; // Value injected by FXMLLoader

    @FXML // fx:id="passField"
    private PasswordField passField; // Value injected by FXMLLoader

    @FXML // fx:id="loginForm"
    private VBox loginForm; // Value injected by FXMLLoader

    @FXML // fx:id="progressInd"
    private AnchorPane progressInd; // Value injected by FXMLLoader


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'main_login.fxml'.";


        layout.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = layout.getScene().getWindow().getX() - mouseEvent.getScreenX();
                dragDelta.y = layout.getScene().getWindow().getY() - mouseEvent.getScreenY();
            }
        });
        layout.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                layout.getScene().getWindow().setX(mouseEvent.getScreenX() + dragDelta.x);
                layout.getScene().getWindow().setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });

    }


    @FXML
    public void startMainPage() {

        if(!validateInput()) {
            return;
        }

        if(!checkPassword()){
            fakeLogin();
            return;
        }

        fakeLogin();


        Parent root = null;
        Stage primaryStage = new Stage();
        try {
            root = FXMLLoader.load(getClass().getResource("main_page.fxml"));
            primaryStage.setTitle("Solly Report");
            primaryStage.setScene(new Scene(root));
            Image applicationIcon = new Image(getClass().getResourceAsStream("images/app_icon.png"));
            primaryStage.getIcons().add(applicationIcon);
            primaryStage.setMinHeight(500);
            primaryStage.setMinWidth(700);
            loginBtn.getScene().getWindow().hide();
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void closeWindow() {

        closeBtn.getScene().getWindow().hide();

    }


    private boolean validateInput() {

        boolean result=true;

        //validate input
        if(userField !=null && userField.getText().equals("")) {
            userField.setStyle(" -fx-border-color: red;");
            result=false;
        } else {
            userField.setStyle("");
        }

        if(passField !=null && passField.getText().equals("")) {
            passField.setStyle(" -fx-border-color: red;");
            result=false;
        } else {
            passField.setStyle("");
        }


        return result;
    }


    private boolean checkPassword() {

        String userName=userField.getText();
        String password = passField.getText();

        if(userName.equals("solly") && password.equals("solly")) {
            return true;
        }else {
            return false;
        }

    }


    private void fakeLogin() {
        System.out.println("MainLoginController.fakeLogin");

        Task loginTask = new Task() {
            @Override
            protected Object call() throws Exception {
                progressInd.setVisible(true);
                loginForm.setDisable(true);
                Thread.sleep(2000);
                loginForm.setDisable(false);
                progressInd.setVisible(false);
                return null;
            }
        };

        Thread t1 = new Thread(loginTask);
        t1.setDaemon(true);
        t1.start();
    }








    class Delta { double x, y; }




}
