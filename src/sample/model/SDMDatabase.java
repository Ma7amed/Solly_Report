package sample.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SDMDatabase {

    private final String USERNAME="inoc_sdm_servicedesk_1";
    private final String PASSWORD="DB_Service_!23";
    private final String IP="10.74.155.13";
    private final String PORT="1522";
    private final String SID="inoc";

    ArrayList<SollyTicket> ticketList;
    ArrayList<SollyWorkOrder> workOrderList;



    private Connection conn = null;

    //SQL Queries




    //Constructor
    public SDMDatabase() {


    }






    public String queryTickets(LocalDateTime startDateTime, LocalDateTime endDateTime,UserGroup team)  {

        String startDateString = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String startTimeString = startDateTime.format(DateTimeFormatter.ofPattern("HH:00:00.000000"));
        String endDateString = endDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endTimeString = endDateTime.format(DateTimeFormatter.ofPattern("HH:00:00.000000"));

        String sql_select_solly_tt
                ="select  " +
                "t2.orderid \"ticket_id\" " +
                ",t2.title_ \"ticket_title\" " +
                ",case " +
                "when t2.processstatus!='completed' then '' " +
                "else to_char(t2.TT005_SUBMIT_TT005+2/24,'yyyy-mm-dd hh24:mi:ss') end \"ticket_confirm_time\" " +
//                ",t1.operatorname \"ticket_operator\" " +
                ",(select u1.fullname from tbl_um_user u1 where u1.username=t1.operatorname) \"ticket_operator\" " +
                ",t1.defaultgroupname \"ticket_operator_group\" " +
                ",(select g.groupname from TBL_UM_DEFAULTGROUP_USER dg " +
                "left join tbl_um_group g on g.group_id=dg.group_id " +
                "where 'user:' || dg.user_id=t2.originator " +
                ") \"ticket_originator\" " +
                ",case  " +
                "when t1.nodeid='WO002' then 'Create WO' " +
                "when t1.nodeid='WO003' then 'Analyze WO' " +
                "when t1.nodeid='WO004' then 'Handle WO' " +
                "when t1.nodeid='WO005' then 'Process WO' " +
                "when t1.nodeid='WO006' then 'Confirm WO' " +
                "when t1.nodeid='TT002' then 'Create TT' " +
                "when t1.nodeid='TT003' then 'Handle TT' " +
                "when t1.nodeid='TT004' then 'Process TT' " +
                "when t1.nodeid='TT005' then 'Confirm TT' " +
                "when t1.nodeid='TT21' then 'Handle TT-SL' " +
                "when t1.nodeid='TT33' then 'Handle TT-THLE' " +
                "else t1.nodeid END \"ticket_phase\" " +
                ",t1.BUSINESSSTATUS \"ticket_business_status\" " +
                ",to_char(t1.SUBMITTIME+2/24,'yyyy-mm-dd hh24:mi:ss')  \"ticket_operation_time\" " +
                ",to_char(t2.CREATETIME+2/24,'yyyy-mm-dd hh24:mi:ss')  \"ticket_creation_time\" " +
                ",f.content " +
                "from INOC_SDM_SDMREPORT_1.V_REPORT_TICKET_TASK t1 " +
                "inner join TBL_TROUBLETICKET_DATASOURCE t2 on t1.ticketid=t2.ticketid " +
                "left join t_ticket_task t3 on t3.ticketid = t2.ticketid and t3.taskid = t1.taskid " +
                "left join T_FORMINSTANCE f on f.forminstanceid = t3.forminstanceid " +
                //"where t1.SUBMITTIME+2/24 >= to_timestamp('2017-03-01'||'08:00:00.000000','yyyy-mm-dd hh24:mi:ss.ff') " +
                "where t1.SUBMITTIME+2/24 >= to_timestamp('" + startDateString + "'||'" + startTimeString + "','yyyy-mm-dd hh24:mi:ss.ff') " +
                //"and t1.SUBMITTIME+2/24 <= to_timestamp('2017-04-01'||'08:00:00.000000','yyyy-mm-dd hh24:mi:ss.ff') " +
                "and t1.SUBMITTIME+2/24 <= to_timestamp('" + endDateString + "'||'" + endTimeString + "','yyyy-mm-dd hh24:mi:ss.ff') " +
                "and t1.operatorname not in ('System') " ;
                //"and t1.defaultgroupname like '%L1%' " +
                //"and (t1.defaultgroupname like '%L1%' or  t1.defaultgroupname like 'HW_FO_PM%') ";

                System.out.println("SDMDatabase.queryTickets Team: " + team);

                String team_condition = generate_team_condition_sql(team);

                sql_select_solly_tt = sql_select_solly_tt + team_condition + " order by t2.orderid,t1.taskid";
//                "and (t1.defaultgroupname like '%L2%') " +



        System.out.println("SDMDatabase.queryTickets, sql_select_solly_tt: " + sql_select_solly_tt);

        ticketList = new ArrayList<SollyTicket>();
        Statement stmt= null;

        try {
            // Connect
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(
                    "jdbc:oracle:thin:@" + IP + ":" + PORT +":" + SID,USERNAME,PASSWORD);

            // Query

            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql_select_solly_tt);

            // Get Results
            while(rs.next()) {

                String ticket_id = rs.getString("ticket_id");
                ticket_id = rs.wasNull()?"":ticket_id;

                String ticket_title = rs.getString("ticket_title");
                ticket_title = rs.wasNull()?"":ticket_title;

                String ticket_confirm_time = rs.getString("ticket_confirm_time");
                ticket_confirm_time = rs.wasNull()?"":ticket_confirm_time;

                String ticket_operator = rs.getString("ticket_operator");
                ticket_operator=rs.wasNull()?"":ticket_operator;

                String ticket_operator_group = rs.getString("ticket_operator_group");
                ticket_operator_group=rs.wasNull()?"":ticket_operator_group;

                String ticket_originator = rs.getString("ticket_originator");
                ticket_originator = rs.wasNull()?"":ticket_originator;

                String ticket_phase = rs.getString("ticket_phase");
                ticket_phase=rs.wasNull()?"":ticket_phase;

                String ticket_business_status = rs.getString("ticket_business_status");
                ticket_business_status = rs.wasNull()?"":ticket_business_status;

                String ticket_operation_time = rs.getString("ticket_operation_time");
                ticket_operation_time = rs.wasNull()?"":ticket_operation_time;

                String ticket_creation_time = rs.getString("ticket_creation_time");
                ticket_creation_time = rs.wasNull()?"":ticket_creation_time;

                String content = rs.getString("content");
                content = rs.wasNull()?"":content;

                String out = "";
                Matcher matcher = Pattern.compile(".*escription.*\n.*\n.*",Pattern.MULTILINE).matcher(content);
                if(matcher.find()) {
                    out = matcher.group();
                    out = Pattern.compile(".*\n.*CDATA.").matcher(out).replaceAll("");
                    out = Pattern.compile("..><.*\n.*").matcher(out).replaceAll("");
//                    System.out.println(out);
                }else {
//                    out = content;
                }


                SollyTicket sollyTicket = new SollyTicket(
                        ticket_id,
                        ticket_title,
                        ticket_confirm_time,
                        ticket_operator,
                        ticket_operator_group,
                        ticket_originator,
                        ticket_phase,
                        ticket_business_status,
                        ticket_operation_time,
                        ticket_creation_time
                );
                sollyTicket.setTicket_description(out);

                //System.out.println("SDMDatabase.queryTickets: " + sollyTicket);
                ticketList.add(sollyTicket);
            }
            System.out.println("SDMDatabase.queryTickets, TicketCount: " + ticketList.size());
            //rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return "Found: " + ticketList.size() + " TTs";

    }

    public String queryWorkOrders(LocalDateTime startDateTime, LocalDateTime endDateTime,UserGroup team)  {

            String startDateString = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String startTimeString = startDateTime.format(DateTimeFormatter.ofPattern("HH:00:00.000000"));
            String endDateString = endDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String endTimeString = endDateTime.format(DateTimeFormatter.ofPattern("HH:00:00.000000"));

        String sql_select_solly_wo=
                "select  " +
                        "t2.PARENTORDERID_ \"parent_ticket_id\", " +
                        "g2.groupname \"ticket_originator\", " +
                        "t2.orderid \"workorder_id\", " +
                        "(select g.groupname from TBL_UM_DEFAULTGROUP_USER dg " +
                        "left join tbl_um_group g on g.group_id=dg.group_id " +
                        "where 'user:' || dg.user_id=t2.originator " +
                        ") \"workorder_originator\" " +
                        ",t2.CREATETITLE_ \"workorder_title\" " +
//                        ",t1.operatorname \"workorder_operator\" " +
                        ",(select u1.fullname from tbl_um_user u1 where u1.username=t1.operatorname) \"workorder_operator\" " +
                        ",t1.defaultgroupname \"workorder_operator_group\" " +
                        ",case  " +
                        "when t1.nodeid='WO002' then 'Create WO' " +
                        "when t1.nodeid='WO003' then 'Analyze WO' " +
                        "when t1.nodeid='WO004' then 'Handle WO' " +
                        "when t1.nodeid='WO005' then 'Process WO' " +
                        "when t1.nodeid='WO006' then 'Confirm WO' " +
                        "else t1.nodeid END \"workorder_phase\" " +
                        ",t1.BUSINESSSTATUS \"workorder_business_status\" " +
                        ",to_char(t1.SUBMITTIME+2/24,'yyyy-mm-dd hh24:mi:ss') \"workorder_operation_time\" " +
                        ",to_char(tt.createtime+2/24,'yyyy-mm-dd hh24:mi:ss') \"ticket_creation_time\" " +
                        ",f.content " +
                        "from INOC_SDM_SDMREPORT_1.V_REPORT_TICKET_TASK t1 " +
                        "inner join TBL_WO_DATASOURCE t2 on t1.ticketid=t2.ticketid " +
                        "left join TBL_TROUBLETICKET_DATASOURCE tt on tt.orderid=t2.PARENTORDERID_ " +
                        "left join TBL_UM_DEFAULTGROUP_USER dug on 'user:' || dug.user_id=tt.ORIGINATOR " +
                        "left join tbl_um_group g2 on g2.group_id = dug.group_id " +
                        "left join t_ticket_task t3 on t3.ticketid = t2.ticketid and t3.taskid = t1.taskid " +
                        "left join T_FORMINSTANCE f on f.forminstanceid = t3.forminstanceid " +
                        //"where t1.SUBMITTIME+2/24 >= to_timestamp('2017-03-01'||'08:00:00.000000','yyyy-mm-dd hh24:mi:ss.ff') " +
                        //"and t1.SUBMITTIME+2/24 <= to_timestamp('2017-04-01'||'08:00:00.000000','yyyy-mm-dd hh24:mi:ss.ff') " +
                        "where t1.SUBMITTIME+2/24 >= to_timestamp('" + startDateString + "'||'" + startTimeString + "','yyyy-mm-dd hh24:mi:ss.ff') " +
                        "and t1.SUBMITTIME+2/24 <= to_timestamp('" + endDateString + "'||'" + endTimeString + "','yyyy-mm-dd hh24:mi:ss.ff') " +
                        "and t1.operatorname not in ('System') " ;
                        //"and t1.defaultgroupname like '%L1%' " +
//                       "and (t1.defaultgroupname like '%L1%' or  t1.defaultgroupname like 'HW_FO_PM%') " +
                        //"and  t1.defaultgroupname = 'HW_BO_L2_TX') " +
//                        "order by t2.orderid,t1.taskid";
                        String team_condition = generate_team_condition_sql(team);

                        sql_select_solly_wo = sql_select_solly_wo + team_condition + " order by t2.orderid,t1.taskid";

        System.out.println("SDMDatabase.queryWorkOrders, sql_select_solly_wo: " + sql_select_solly_wo);

        workOrderList = new ArrayList<SollyWorkOrder>();

        Statement stmt= null;
        try {
            // Connect
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(
                    "jdbc:oracle:thin:@" + IP + ":" + PORT +":" + SID,USERNAME,PASSWORD);

            // Query

            stmt = conn.createStatement();
            System.out.println("ResultSet rs=stmt.executeQuery(sql_select_solly_wo)");
            ResultSet rs=stmt.executeQuery(sql_select_solly_wo);

            System.out.println("getting results");
            // Get Results
            while(rs.next()) {


                String parent_ticket_id= rs.getString("parent_ticket_id");
                parent_ticket_id = rs.wasNull()?"":parent_ticket_id;

                String ticket_originator= rs.getString("ticket_originator");
                ticket_originator = rs.wasNull()?"":ticket_originator;

                String workorder_id= rs.getString("workorder_id");
                workorder_id = rs.wasNull()?"":workorder_id;

                String workorder_originator= rs.getString("workorder_originator");
                workorder_originator = rs.wasNull()?"":workorder_originator;

                String workorder_title= rs.getString("workorder_title");
                workorder_title = rs.wasNull()?"":workorder_title;

                String workorder_operator= rs.getString("workorder_operator");
                workorder_operator = rs.wasNull()?"":workorder_operator;

                String workorder_operator_group= rs.getString("workorder_operator_group");
                workorder_operator_group = rs.wasNull()?"":workorder_operator_group;

                String workorder_phase= rs.getString("workorder_phase");
                workorder_phase = rs.wasNull()?"":workorder_phase;

                String workorder_business_status= rs.getString("workorder_business_status");
                workorder_business_status = rs.wasNull()?"":workorder_business_status;

                String workorder_operation_time= rs.getString("workorder_operation_time");
                workorder_operation_time = rs.wasNull()?"":workorder_operation_time;

                String ticket_creation_time= rs.getString("ticket_creation_time");
                ticket_creation_time = rs.wasNull()?"":ticket_creation_time;


                SollyWorkOrder sollyWorkOrder = new SollyWorkOrder(
                parent_ticket_id,
                ticket_originator,
                workorder_id,
                workorder_originator,
                workorder_title,
                workorder_operator,
                workorder_operator_group,
                workorder_phase,
                workorder_business_status,
                workorder_operation_time,
                ticket_creation_time
                );


                workOrderList.add(sollyWorkOrder);

               // System.out.println("SDMDatabase.queryTickets: " + sollyWorkOrder);
               // workOrderList.add(sollyWorkOrder);
            }
            System.out.println("SDMDatabase.queryTickets, TicketCount: " + workOrderList.size());




        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
            try {

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return "Found: " + workOrderList.size() + " WOs";


    }



    public boolean doLogin(String userName,String password)  {

        /*
        System.out.println("SDMDatabase.doLogin: " +userName + ", " + password);
        if(!userName.equals("admin") || !password.equals("admin")) {
            System.out.println("SDMDatabase.doLogin: wrong UN/PWD");
            return false;
        }


        boolean loginResult = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //LocalDate expireDate = LocalDate.parse("2017-07-03",formatter);
        LocalDate expireDate = LocalDate.parse("2018-01-01",formatter);


        String sql_select_login= "select to_char(sysdate,'yyyy-mm-dd') \"current_date\"\n" +
                "from dual";



        Statement stmt= null;
        try {
            // Connect
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(
                    "jdbc:oracle:thin:@" + IP + ":" + PORT +":" + SID,USERNAME,PASSWORD);

            // Query

            stmt = conn.createStatement();
            System.out.println("ResultSet rs=stmt.executeQuery(sql_select_solly_wo)");
            ResultSet rs=stmt.executeQuery(sql_select_login);

            System.out.println("getting results");
            // Get Results
            while(rs.next()) {


                String current_date_string= rs.getString("current_date");

                if(current_date_string ==null || current_date_string.equals("")) {
                    throw new Exception();
                }

                LocalDate currentDate = LocalDate.parse(current_date_string,formatter);


                System.out.println("SDMDatabase.doLogin: " + currentDate.isAfter(expireDate) );

                if(currentDate.isAfter(expireDate)) {
                    //login failed
                    loginResult = false;
                }else {
                    //login succeed
                    loginResult = true;
                }


                System.out.println("SDMDatabase.doLogin, Current Date: " + current_date_string);
                System.out.println("SDMDatabase.doLogin, Expire Date: " + expireDate.toString());



            }



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
            try {

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return loginResult;
        */
        // AFTER REMOVING VALIDATION ... TOOL WILL RUN FOREVER :)
        return true;
    }



    public ArrayList<SollyTicket> getTicketList() {
        return ticketList;
    }

    public ArrayList<SollyWorkOrder> getWorkOrderList() {
        return workOrderList;
    }


    private String generate_team_condition_sql(UserGroup team) {
        String team_condition = "";
        switch(team) {
            case L1_2G_3G:
                team_condition = "and  t1.defaultgroupname in ('HW_FO_L1_2G','HW_FO_L1_3G') ";
                break;
            case L1_TX:
                team_condition = "and  t1.defaultgroupname in ('HW_FO_L1_TX') ";
                break;
            case L1_Core:
                team_condition = "and  t1.defaultgroupname in ('HW_BO_L1_CS&IGW','HW_BO_L1_PS','HW_FO_L1_Vas') ";
                break;
            case L1_Other:
                team_condition = "and  t1.defaultgroupname like '%_L1_%' and t1.defaultgroupname not in ('HW_FO_L1_2G','HW_FO_L1_3G','HW_FO_L1_TX','HW_BO_L1_CS&IGW','HW_BO_L1_PS','HW_FO_L1_Vas') ";
                break;
            case L2_2G_3G:
                team_condition = "and  t1.defaultgroupname in ('HW_BO_L2_3G','HW_BO_L2_2G') ";
                break;
            case L2_TX:
                team_condition = "and  t1.defaultgroupname in ('HW_BO_L2_TX') ";
                break;
            case L2_Core:
                team_condition = "and  t1.defaultgroupname in ('HW_BO_L2_IPBB','HW_BO_L2_CS','HW_BO_L2_IGW','HW_BO_L2_PS','HW_BO_L2_VAS') ";
                break;
            case L2_Other:
                team_condition = "and  t1.defaultgroupname like '%_L2_%' and t1.defaultgroupname not in ('HW_BO_L2_3G','HW_BO_L2_2G','HW_BO_L2_TX','HW_BO_L2_IPBB','HW_BO_L2_CS','HW_BO_L2_IGW','HW_BO_L2_PS','HW_BO_L2_VAS') ";
                break;

        }
        return team_condition;
    }


}
