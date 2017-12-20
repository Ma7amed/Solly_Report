/**
 * Sample Skeleton for 'main_page.fxml' Controller Class
 */

package sample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.File;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.model.*;

public class MainPageController implements ActionListener {

    Model model;
    ObservableList<SollyTicket> ticketList;
    ObservableList<SollyWorkOrder> workOrderList;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tab_pane"
    private TabPane tab_pane; // Value injected by FXMLLoader

    @FXML // fx:id="tt_form"
    private GridPane tt_form; // Value injected by FXMLLoader

    @FXML // fx:id="tt_start_date_picker"
    private DatePicker tt_start_date_picker; // Value injected by FXMLLoader

    @FXML // fx:id="tt_start_hour_combo"
    private ComboBox<String> tt_start_hour_combo; // Value injected by FXMLLoader

    @FXML
    private ComboBox<String> tt_team_combo;

    @FXML // fx:id="wo_team_combo"
    private ComboBox<String> wo_team_combo; // Value injected by FXMLLoader

    @FXML // fx:id="tt_end_date_picker"
    private DatePicker tt_end_date_picker; // Value injected by FXMLLoader

    @FXML // fx:id="tt_end_hour_combo"
    private ComboBox<String> tt_end_hour_combo; // Value injected by FXMLLoader

    @FXML // fx:id="tt_queryBtn"
    private Button tt_queryBtn; // Value injected by FXMLLoader


    @FXML // fx:id="tt_table"
    private TableView<SollyTicket> tt_table; // Value injected by FXMLLoader

    @FXML // fx:id="wo_table"
    private TableView<SollyWorkOrder> wo_table; // Value injected by FXMLLoader

    @FXML // fx:id="tt_ticket_id"
    private TableColumn<SollyTicket, String> tt_ticket_id; // Value injected by FXMLLoader

    @FXML // fx:id="tt_ticket_title"
    private TableColumn<SollyTicket, String> tt_ticket_title; // Value injected by FXMLLoader

    @FXML // fx:id="tt_ticket_confirm_time"
    private TableColumn<SollyTicket, String> tt_ticket_confirm_time; // Value injected by FXMLLoader

    @FXML // fx:id="tt_ticket_operator"
    private TableColumn<SollyTicket, String> tt_ticket_operator; // Value injected by FXMLLoader

    @FXML // fx:id="tt_ticket_operator_group"
    private TableColumn<SollyTicket, String> tt_ticket_operator_group; // Value injected by FXMLLoader

    @FXML // fx:id="tt_ticket_originator"
    private TableColumn<SollyTicket, String> tt_ticket_originator; // Value injected by FXMLLoader

    @FXML // fx:id="tt_ticket_phase"
    private TableColumn<SollyTicket, String> tt_ticket_phase; // Value injected by FXMLLoader

    @FXML // fx:id="tt_ticket_business_status"
    private TableColumn<SollyTicket, String> tt_ticket_business_status; // Value injected by FXMLLoader

    @FXML // fx:id="tt_ticket_operation_time"
    private TableColumn<SollyTicket, String> tt_ticket_operation_time; // Value injected by FXMLLoader

    @FXML // fx:id="tt_ticket_creation_time"
    private TableColumn<SollyTicket, String> tt_ticket_creation_time; // Value injected by FXMLLoader

    @FXML
    private TableColumn<SollyTicket, String> tt_description;


    @FXML // fx:id="wo_form"
    private GridPane wo_form; // Value injected by FXMLLoader

    @FXML // fx:id="wo_start_date_picker"
    private DatePicker wo_start_date_picker; // Value injected by FXMLLoader

    @FXML // fx:id="wo_start_hour_combo"
    private ComboBox<String> wo_start_hour_combo; // Value injected by FXMLLoader

    @FXML // fx:id="wo_end_date_picker"
    private DatePicker wo_end_date_picker; // Value injected by FXMLLoader

    @FXML // fx:id="wo_end_hour_combo"
    private ComboBox<String> wo_end_hour_combo; // Value injected by FXMLLoader



    @FXML // fx:id="wo_parent_ticket_id"
    private TableColumn<SollyWorkOrder, String> wo_parent_ticket_id; // Value injected by FXMLLoader

    @FXML // fx:id="wo_ticket_originator"
    private TableColumn<SollyWorkOrder,String> wo_ticket_originator; // Value injected by FXMLLoader

    @FXML // fx:id="wo_workorder_id"
    private TableColumn<SollyWorkOrder,String> wo_workorder_id; // Value injected by FXMLLoader

    @FXML // fx:id="wo_workorder_originator"
    private TableColumn<SollyWorkOrder,String> wo_workorder_originator; // Value injected by FXMLLoader

    @FXML // fx:id="wo_workorder_title"
    private TableColumn<SollyWorkOrder,String> wo_workorder_title; // Value injected by FXMLLoader

    @FXML // fx:id="wo_workorder_operator"
    private TableColumn<SollyWorkOrder,String> wo_workorder_operator; // Value injected by FXMLLoader

    @FXML // fx:id="wo_workorder_operator_group"
    private TableColumn<SollyWorkOrder,String> wo_workorder_operator_group; // Value injected by FXMLLoader

    @FXML // fx:id="wo_workorder_phase"
    private TableColumn<SollyWorkOrder,String> wo_workorder_phase; // Value injected by FXMLLoader

    @FXML // fx:id="wo_workorder_business_status"
    private TableColumn<SollyWorkOrder,String> wo_workorder_business_status; // Value injected by FXMLLoader

    @FXML // fx:id="wo_workorder_operation_time"
    private TableColumn<SollyWorkOrder,String> wo_workorder_operation_time; // Value injected by FXMLLoader

    @FXML // fx:id="wo_workorder_operation_time"
    private TableColumn<SollyWorkOrder,String> wo_ticket_creation_time; // Value injected by FXMLLoader

    @FXML // fx:id="status_label"
    private Label tt_status_label; // Value injected by FXMLLoader

    @FXML // fx:id="progress_bar"
    private ProgressIndicator wo_progress_bar; // Value injected by FXMLLoader

    @FXML // fx:id="status_label"
    private Label wo_status_label; // Value injected by FXMLLoader

    @FXML // fx:id="progress_bar"
    private ProgressIndicator tt_progress_bar; // Value injected by FXMLLoader

    @FXML // fx:id="exportTTMenuItem"
    private MenuItem exportTTMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="tt_tab"
    private Tab tt_tab; // Value injected by FXMLLoader

    @FXML // fx:id="wo_tab"
    private Tab wo_tab; // Value injected by FXMLLoader


    @FXML // fx:id="tt_tab_image"
    private ImageView tt_tab_image; // Value injected by FXMLLoader

    @FXML // fx:id="wo_tab_image"
    private ImageView wo_tab_image; // Value injected by FXMLLoader

    @FXML // fx:id="wo_queryBtn"
    private Button wo_queryBtn; // Value injected by FXMLLoader

    @FXML // fx:id="loginPage"
    private AnchorPane loginPage; // Value injected by FXMLLoader

    @FXML // fx:id="usernameField"
    private TextField usernameField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordField"
    private PasswordField passwordField; // Value injected by FXMLLoader

    @FXML
    private ProgressIndicator login_progressIndicator;

    @FXML
    private GridPane login_form;

    @FXML // fx:id="loginImage"
    private ImageView loginImage; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tt_form != null : "fx:id=\"tt_form\" was not injected: check your FXML file 'main_page.fxml'.";
        assert tt_start_date_picker != null : "fx:id=\"tt_start_date_picker\" was not injected: check your FXML file 'main_page.fxml'.";
        assert tt_start_hour_combo != null : "fx:id=\"tt_start_hour_combo\" was not injected: check your FXML file 'main_page.fxml'.";
        assert tt_end_date_picker != null : "fx:id=\"tt_end_date_picker\" was not injected: check your FXML file 'main_page.fxml'.";
        assert tt_end_hour_combo != null : "fx:id=\"tt_end_hour_combo\" was not injected: check your FXML file 'main_page.fxml'.";
        assert tt_table != null : "fx:id=\"tt_table\" was not injected: check your FXML file 'main_page.fxml'.";
        assert tt_team_combo != null : "fx:id=\"tt_team_combo\" was not injected: check your FXML file 'main_page.fxml'.";
        assert wo_team_combo != null : "fx:id=\"wo_team_combo\" was not injected: check your FXML file 'main_page.fxml'.";

        setLoginImage();

        initTTTable();

        initWOTable();

        model = new Model();
        model.addActionListener(this);


        initComboBox();

     }


     @FXML
     public void doTTQuery() {

        // Confirm all Fields have value

        if(!validateTTForm()) {
            return;
        }

        // Get Form Data

         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00:00.000000");

         LocalDate startDate = tt_start_date_picker.getValue();
         System.out.println("MainPageController.doTTQuery, startDate: " + startDate);
         LocalTime startTime = LocalTime.parse(tt_start_hour_combo.getValue() + ":00:00");
         System.out.println("MainPageController.doTTQuery, startTime: " + startTime);

         LocalDateTime startDateTime = LocalDateTime.of(startDate,startTime);
         System.out.println("MainPageController.doTTQuery, startDateTime: " + startDateTime);


         LocalDate endDate = tt_end_date_picker.getValue();
         LocalTime endTime = LocalTime.parse(tt_end_hour_combo.getValue() + ":00:00");
         LocalDateTime endDateTime = LocalDateTime.of(endDate,endTime);

         String team_string = tt_team_combo.getValue();
         UserGroup team = UserGroup.valueOf(team_string);
         System.out.println("Team: " + team);

         // Update View
         tt_form.setDisable(true);
         tt_table.setDisable(true);
         tt_status_label.setText("");
         tt_progress_bar.setVisible(true);

         // Ask model to query
         model.queryTickets(startDateTime,endDateTime,team);

     }

    @FXML
    public void doWOQuery() {

        // Confirm all Fields have value

        if(!validateWOForm()) {
            return;
        }

        // Get Form Data

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00:00.000000");

        LocalDate startDate = wo_start_date_picker.getValue();
        LocalTime startTime = LocalTime.parse(wo_start_hour_combo.getValue() + ":00:00");
        LocalDateTime startDateTime = LocalDateTime.of(startDate,startTime);

        LocalDate endDate = wo_end_date_picker.getValue();
        LocalTime endTime = LocalTime.parse(wo_end_hour_combo.getValue() + ":00:00");
        LocalDateTime endDateTime = LocalDateTime.of(endDate,endTime);

        String team_string = wo_team_combo.getValue();
        UserGroup team = UserGroup.valueOf(team_string);
        System.out.println("Team: " + team);

        // Update View
        wo_form.setDisable(true);
        wo_table.setDisable(true);
        wo_status_label.setText("");
        wo_progress_bar.setVisible(true);

        // Ask model to query
        model.queryWorkOrders(startDateTime,endDateTime,team);

    }

    @FXML
    public void exportTT() {
        System.out.println("MainPageController.exportTT");

        if(ticketList.size() == 0 ) {
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save TT Data");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel Files", "*.xlsx")
        );
        File file = fileChooser.showSaveDialog(tt_table.getScene().getWindow());
        if(file != null) {
            System.out.println("MainPageController.exportTT, file: " + file);


            // Update View
            tt_form.setDisable(true);
            tt_status_label.setText("");
            tt_progress_bar.setVisible(true);
            tt_table.setDisable(true);
            model.writeTTData(new ArrayList<SollyTicket>(ticketList),file);
        }
    }

    @FXML
    public void exportWO() {

        System.out.println("MainPageController.exportWO");

        if(workOrderList.size() == 0 ) {
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save WO Data");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel Files", "*.xlsx")
        );
        File file = fileChooser.showSaveDialog(tt_table.getScene().getWindow());
        if(file != null) {
            System.out.println("MainPageController.exportWO, file: " + file);


            // Update View
            wo_form.setDisable(true);
            wo_status_label.setText("");
            wo_progress_bar.setVisible(true);
            wo_table.setDisable(true);
            model.writeWOData(new ArrayList<SollyWorkOrder>(workOrderList),file);
        }
    }

     private boolean validateTTForm() {

        boolean result=true;

         if(tt_start_date_picker.getValue() == null) {
             tt_start_date_picker.setStyle(" -fx-border-color: red;");
             result=false;
         } else {
             tt_start_date_picker.setStyle("");
         }

         if(tt_end_date_picker.getValue() == null) {
             tt_end_date_picker.setStyle(" -fx-border-color: red;");
             result=false;
         }else {
             tt_end_date_picker.setStyle("");
         }

         if(tt_team_combo.getValue() == null) {
             tt_team_combo.setStyle(" -fx-border-color: red;");
             result=false;
         } else {
             tt_team_combo.setStyle("");
         }
        return result;
     }

    private boolean validateWOForm() {

        boolean result=true;

        if(wo_start_date_picker.getValue() == null) {
            wo_start_date_picker.setStyle(" -fx-border-color: red;");
            result=false;
        } else {
            wo_start_date_picker.setStyle("");
        }

        if(wo_end_date_picker.getValue() == null) {
            wo_end_date_picker.setStyle(" -fx-border-color: red;");
            result=false;
        }else {
            wo_end_date_picker.setStyle("");
        }

        if(wo_team_combo.getValue() == null) {
            wo_team_combo.setStyle(" -fx-border-color: red;");
            result=false;
        } else {
            wo_team_combo.setStyle("");
        }

        return result;
    }

    public void initTTTable() {

        // set tables items
        ticketList = FXCollections.observableList(new ArrayList<SollyTicket>());

        tt_table.setItems(ticketList);

        // bind table columns
        tt_ticket_id.setCellValueFactory(new PropertyValueFactory<SollyTicket,String>("ticket_id"));
        tt_ticket_title.setCellValueFactory(new PropertyValueFactory<SollyTicket,String>("ticket_title"));
        tt_ticket_confirm_time.setCellValueFactory(new PropertyValueFactory<SollyTicket,String>("ticket_confirm_time"));
        tt_ticket_operator.setCellValueFactory(new PropertyValueFactory<SollyTicket,String>("ticket_operator"));
        tt_ticket_operator_group.setCellValueFactory(new PropertyValueFactory<SollyTicket,String>("ticket_operator_group"));
        tt_ticket_originator.setCellValueFactory(new PropertyValueFactory<SollyTicket,String>("ticket_originator"));
        tt_ticket_phase.setCellValueFactory(new PropertyValueFactory<SollyTicket,String>("ticket_phase"));
        tt_ticket_business_status.setCellValueFactory(new PropertyValueFactory<SollyTicket,String>("ticket_business_status"));
        tt_ticket_operation_time.setCellValueFactory(new PropertyValueFactory<SollyTicket,String>("ticket_operation_time"));
        tt_ticket_creation_time.setCellValueFactory(new PropertyValueFactory<SollyTicket,String>("ticket_creation_time"));
        tt_description.setCellValueFactory(new PropertyValueFactory<SollyTicket,String>("ticket_description"));




    }


    public void initWOTable() {

        // set tables items
        workOrderList = FXCollections.observableList(new ArrayList<SollyWorkOrder>());

        wo_table.setItems(workOrderList);

        // bind table columns
        wo_parent_ticket_id.setCellValueFactory(new PropertyValueFactory<SollyWorkOrder,String>("parent_ticket_id"));
        wo_ticket_originator.setCellValueFactory(new PropertyValueFactory<SollyWorkOrder,String>("ticket_originator"));
        wo_workorder_id.setCellValueFactory(new PropertyValueFactory<SollyWorkOrder,String>("workorder_id"));
        wo_workorder_originator.setCellValueFactory(new PropertyValueFactory<SollyWorkOrder,String>("workorder_originator"));
        wo_workorder_title.setCellValueFactory(new PropertyValueFactory<SollyWorkOrder,String>("workorder_title"));
        wo_workorder_operator.setCellValueFactory(new PropertyValueFactory<SollyWorkOrder,String>("workorder_operator"));
        wo_workorder_operator_group.setCellValueFactory(new PropertyValueFactory<SollyWorkOrder,String>("workorder_operator_group"));
        wo_workorder_phase.setCellValueFactory(new PropertyValueFactory<SollyWorkOrder,String>("workorder_phase"));
        wo_workorder_business_status.setCellValueFactory(new PropertyValueFactory<SollyWorkOrder,String>("workorder_business_status"));
        wo_workorder_operation_time.setCellValueFactory(new PropertyValueFactory<SollyWorkOrder,String>("workorder_operation_time"));
        wo_ticket_creation_time.setCellValueFactory(new PropertyValueFactory<SollyWorkOrder,String>("ticket_creation_time"));


    }


    public void initComboBox() {

        ObservableList<String> hourList = FXCollections.observableList(new ArrayList<String>());


        for(int i=0;i<=23;i++) {
            hourList.add(Integer.toString(i).length()==1?"0" + i:""+i);
        }

        tt_start_hour_combo.setItems(hourList);
        tt_end_hour_combo.setItems(hourList);
        tt_start_hour_combo.setValue("00");
        tt_end_hour_combo.setValue("00");

        wo_start_hour_combo.setItems(hourList);
        wo_end_hour_combo.setItems(hourList);
        wo_start_hour_combo.setValue("00");
        wo_end_hour_combo.setValue("00");

        ObservableList<String> teamList =  FXCollections.observableList(new ArrayList<String>());
//        teamList.add("2G");
//        teamList.add("3G");

        String[] teamArray = Util.getNames(UserGroup.class);
        teamList.addAll(teamArray);

        tt_team_combo.setItems(teamList);
        wo_team_combo.setItems(teamList);
    }





    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("MainPageController.actionPerformed, e: " + e.getActionCommand());

        if(e.getID()==1) {
            // tt query succeed
            ticketList.removeAll(ticketList);
            ticketList.addAll(model.getTicketList());
            tt_form.setDisable(false);
            tt_table.setDisable(false);
            tt_progress_bar.setVisible(false);
            tt_status_label.setText(e.getActionCommand());
        } else if (e.getID()==2) {
            // wo query succeed
            workOrderList.removeAll(workOrderList);
            workOrderList.addAll(model.getWorkOrderList());
            wo_form.setDisable(false);
            wo_table.setDisable(false);
            wo_progress_bar.setVisible(false);
            wo_status_label.setText(e.getActionCommand());
        } else if (e.getID()==3) {
            // tt export succeed
            tt_form.setDisable(false);
            tt_progress_bar.setVisible(false);
            tt_status_label.setText(e.getActionCommand());
            tt_table.setDisable(false);
        } else if (e.getID()==4) {
            // tt export succeed
            wo_form.setDisable(false);
            wo_progress_bar.setVisible(false);
            wo_status_label.setText(e.getActionCommand());
            wo_table.setDisable(false);
        } else if (e.getID()==20) {
            // wo query failed
            workOrderList.removeAll(workOrderList);
            wo_form.setDisable(false);
            wo_table.setDisable(false);
            wo_progress_bar.setVisible(false);
            wo_status_label.setText(e.getActionCommand());
        }else if(e.getID()==10) {
            // tt query failed
            ticketList.removeAll(ticketList);
            tt_form.setDisable(false);
            tt_table.setDisable(false);
            tt_progress_bar.setVisible(false);
            tt_status_label.setText(e.getActionCommand());
        }else if(e.getID()==5) {
            //login succeed
            ((Stage) loginPage.getScene().getWindow()).setResizable(true);
            loginPage.setVisible(false);
        }else if(e.getID()==6) {
            //login failed
            login_form.setDisable(false);
            login_progressIndicator.setVisible(false);
        }


    }

    @FXML
    private void tabChanged() {

        System.out.println("MainPageController.tabChanged");



        if (!tt_tab.isSelected()) {
            tt_tab_image.setImage(new Image(getClass().getResourceAsStream("images/magnifier_grey.png")));
            tt_tab_image.setScaleX(1);
            tt_tab_image.setScaleY(1);
        } else {
            tt_tab_image.setImage(new Image(getClass().getResourceAsStream("images/magnifier.png")));
            tt_tab_image.setScaleX(1.5);
            tt_tab_image.setScaleY(1.5);
        }

        if(wo_tab ==null ) {
            return;
        }

        if (!tt_tab.isSelected()) {
            wo_tab_image.setScaleX(1.5);
            wo_tab_image.setScaleY(1.5);
            wo_tab_image.setImage(new Image(getClass().getResourceAsStream("images/magnifier.png")));
        } else {
            wo_tab_image.setScaleX(1);
            wo_tab_image.setScaleY(1);
            wo_tab_image.setImage(new Image(getClass().getResourceAsStream("images/magnifier_grey.png")));
        }

    }

    @FXML
    private void woQueryMouseEntered() {

    }

    @FXML
    private void woQueryMouseExit() {

    }

    @FXML
    private void doLogin() {
        System.out.println("MainPageController.doLogin");
        if(!validateInput()) {
            return;
        }

        login_form.setDisable(true);
        login_progressIndicator.setVisible(true);
        model.doLogin(usernameField.getText(),passwordField.getText());

      //  ((Stage) loginPage.getScene().getWindow()).setResizable(true);
       // loginPage.setVisible(false);
    }


    private boolean validateInput() {

        boolean result=true;

        //validate input
        if(usernameField !=null && usernameField.getText().equals("")) {
            usernameField.setStyle(" -fx-border-color: red;");
            result=false;
        } else {
            usernameField.setStyle("");
        }

        if(passwordField !=null && passwordField.getText().equals("")) {
            passwordField.setStyle(" -fx-border-color: red;");
            result=false;
        } else {
            passwordField.setStyle("");
        }


        return result;
    }

    private void setLoginImage() {
        //loginImage

        ArrayList<Image> loginImages = new ArrayList<Image>();

        loginImages.add(new Image(getClass().getResourceAsStream("images/landscape.jpg")));
        loginImages.add(new Image(getClass().getResourceAsStream("images/autumn.jpg")));
        loginImages.add(new Image(getClass().getResourceAsStream("images/businessman.jpg")));
        loginImages.add(new Image(getClass().getResourceAsStream("images/fishingboats.jpg")));
        loginImages.add(new Image(getClass().getResourceAsStream("images/ODRAX20-min - Copy.jpg")));
        loginImages.add(new Image(getClass().getResourceAsStream("images/coloredboats.png")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();
        LocalDate ramadanDateStart = LocalDate.parse("2017-05-20",formatter);
        LocalDate ramadanDateEnd = LocalDate.parse("2017-06-25",formatter);

        if(currentDate.isAfter(ramadanDateStart) && currentDate.isBefore(ramadanDateEnd)) {
            // only if during ramadan
            System.out.println("MainPageController.setLoginImage ... adding ramadan");
            loginImages.removeAll(loginImages);
            loginImages.add(new Image(getClass().getResourceAsStream("images/Ramadan.png")));
        }

       // Math.random

        Image login_image = loginImages.get((int) ( Math.random()  *  (loginImages.size())  ) );
        loginImage.setImage(login_image);
        loginImage.setFitHeight(500);
       loginImage.setFitWidth(700);

    }


}



