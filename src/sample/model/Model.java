package sample.model;

import javafx.concurrent.Task;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by m80028770 on 4/22/2017.
 */
public class Model {

    SDMDatabase tt_db;
    SDMDatabase wo_db;
    ExcelWritter ew;

    ArrayList<ActionListener> listenerList;

    public static ActionEvent QUERY_TT_SUCCEED = new ActionEvent(new Object(),1,"QUERY_TT_SUCCEED");

    public Model() {

        listenerList = new ArrayList<ActionListener>();

        tt_db=new SDMDatabase();
        wo_db = new SDMDatabase();

        ew = new ExcelWritter();
    }

    public void queryTickets(LocalDateTime startDateTime, LocalDateTime endDateTime,UserGroup team)  {

        Task queryTTTask = new Task() {
            @Override
            protected Object call() throws Exception {
              return tt_db.queryTickets(startDateTime,endDateTime,team);

            }
        };

        queryTTTask.setOnSucceeded(event -> {

            System.out.println("Model.queryTickets: Succeed");
            for(ActionListener listener:listenerList) {
                listener.actionPerformed(new ActionEvent(this,1,(String) queryTTTask.getValue()));
            }
        });

        queryTTTask.setOnFailed(event -> {

            System.out.println("Model.queryTickets: Failed");
            for(ActionListener listener:listenerList) {
                listener.actionPerformed(new ActionEvent(this,10,"Query TT Failed"));
            }
        });

        Thread t1 = new Thread(queryTTTask);
        t1.setDaemon(true);
        t1.start();



    }

    public void queryWorkOrders(LocalDateTime startDateTime, LocalDateTime endDateTime,UserGroup team)  {

        Task queryWOTask = new Task() {
            @Override
            protected Object call() throws Exception {
                return wo_db.queryWorkOrders(startDateTime,endDateTime,team);

            }
        };

        queryWOTask.setOnSucceeded(event -> {
            System.out.println("Model.queryWorkOrder: Succeed");
            for(ActionListener listener:listenerList) {
                listener.actionPerformed(new ActionEvent(this,2,(String) queryWOTask.getValue()));
            }
        });

        queryWOTask.setOnFailed(event -> {
            System.out.println("Model.queryWorkOrders: Failed");
            for(ActionListener listener:listenerList) {
                listener.actionPerformed(new ActionEvent(this,20,"Query WO Failed"));
            }
        });

        Thread t1 = new Thread(queryWOTask);
        t1.setDaemon(true);
        t1.start();



    }

    public void addActionListener(ActionListener listener) {
        this.listenerList.add(listener);
    }

    public ArrayList<SollyTicket> getTicketList() {
        return tt_db.getTicketList();
    }

    public ArrayList<SollyWorkOrder> getWorkOrderList() {
        return wo_db.getWorkOrderList();
    }

    public void writeTTData(ArrayList<SollyTicket> ticketList,File file) {

        System.out.println("Model.writeTTData");

        Task writeTTDataTask = new Task() {
            @Override
            protected Object call() throws Exception {

                ew.writeTTData(ticketList,file);

                return null;
            }
        };

        writeTTDataTask.setOnSucceeded(event -> {
            System.out.println("Model.writeTTData");
            for(ActionListener listener:listenerList) {
                listener.actionPerformed(new ActionEvent(this,3,"Export TT Succeed"));
            }
        });

        Thread writeJob = new Thread(writeTTDataTask);
        writeJob.setDaemon(true);
        writeJob.start();

    }

    public void writeWOData(ArrayList<SollyWorkOrder> workOrderList,File file) {

        System.out.println("Model.writeWOData");

        Task writeWOTask = new Task() {
            @Override
            protected Object call() throws Exception {

                ew.writeWOData(workOrderList,file);

                return null;
            }
        };

        writeWOTask.setOnSucceeded(event -> {
            System.out.println("Model.writeWOData");
            for(ActionListener listener:listenerList) {
                listener.actionPerformed(new ActionEvent(this,4,"Export WO Succeed"));
            }
        });

        Thread writeJob = new Thread(writeWOTask);
        writeJob.setDaemon(true);
        writeJob.start();

    }

    public void doLogin(String userName, String password) {
        System.out.println("Model.doLogin");
        Task loginTask = new Task() {
            @Override
            protected Object call() throws Exception {
                Thread.sleep(2000);
                return tt_db.doLogin(userName,password);
            }
        };

        loginTask.setOnSucceeded(event -> {
            System.out.println("Model.doLogin: succeed");

            System.out.println("Model.doLogin: "+ (boolean) loginTask.getValue());
            if(((boolean) loginTask.getValue())) {
            // login succeed
                for(ActionListener listener:listenerList) {
                    listener.actionPerformed(new ActionEvent(this,5,"Login Succeed"));
                }
            }else {
                //login failed
                for(ActionListener listener:listenerList) {
                    listener.actionPerformed(new ActionEvent(this,6,"Login Failed"));
                }
            }


        });

        loginTask.setOnFailed(event -> {
            System.out.println("Model.doLogin: failed");

            for(ActionListener listener:listenerList) {
                listener.actionPerformed(new ActionEvent(this,6,"Login Failed"));
            }
        });

        Thread t1 = new Thread(loginTask);
        t1.setDaemon(true);
        t1.start();
    }

}
