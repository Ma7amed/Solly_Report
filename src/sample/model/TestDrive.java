package sample.model;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by m80028770 on 8/8/2017.
 */
public class TestDrive {

    private final static String USERNAME="inoc_sdm_servicedesk_1";
    private final static String PASSWORD="DB_Service_!23";
    private final static String IP="10.74.155.13";
    private final static String PORT="1522";
    private final static String SID="inoc";
    private static Connection conn = null;


    public static void main2(String[] args) {



            String sql_select_solly_tt
                    ="select content from T_FORMINSTANCE where FORMINSTANCEID=32384700";

        System.out.println("Connecting ....");

        Statement stmt= null;

            try {
                // Connect
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn= DriverManager.getConnection(
                        "jdbc:oracle:thin:@" + IP + ":" + PORT +":" + SID,USERNAME,PASSWORD);

                // Query


                stmt = conn.createStatement();
                ResultSet rs=stmt.executeQuery(sql_select_solly_tt);

                // Get Results
                while(rs.next()) {

                    String content = rs.getString("content");
                    content = rs.wasNull() ? "" : content;

                    System.out.println("TestDrive.main content: " + content);

                    Pattern pattern = Pattern.compile("FaultSolutionType.*/value",Pattern.MULTILINE);
                    Matcher matcher = pattern.matcher(content);
                    while (matcher.find()) {
                        System.out.print("Start index: " + matcher.start());
                        System.out.print(" End index: " + matcher.end() + " ");
                        System.out.println(matcher.group());
                    }
                }
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




    }


    public static void main(String[] args) {

        String x = "<form>\n" +
                "  <widgets>\n" +
                "    <widget widgetId='TextInput4'>\n" +
                "      <value><![CDATA[System]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='FaultSolutionType'>\n" +
                "      <value><![CDATA[FaultAutoRecovery]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='RadioTabs1_FLEProcessTTOptModeResolved_DropDownList4'>\n" +
                "      <value><![CDATA[A8cf4652f-2bce-4aeb-a5bf-4df15a88064c]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='process_userId'>\n" +
                "      <value><![CDATA[11]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='RadioTabs1_FLEProcessTTOptModeResolved_DropDownList5'>\n" +
                "      <value><![CDATA[5057dafd-beda-4e68-93a6-9d4b6d8daef6]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='process_status'>\n" +
                "      <value><![CDATA[running]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='business_status'>\n" +
                "      <value><![CDATA[BusinessStatusResovled]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='FaultSolutionDescription'>\n" +
                "      <value><![CDATA[the alarm is cleared Automatically,Maintainer check and find the system is normal!]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='RadioTabs1_FLEProcessTTOptModeResolved_DateTime1'>\n" +
                "      <value><![CDATA[2017-04-01 09:32:22]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='RadioTabs1'>\n" +
                "      <value><![CDATA[FLEProcessTTOptModeResolved]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='proc_department'>\n" +
                "      <value><![CDATA[group:7002]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='FaultReasonType'>\n" +
                "      <value><![CDATA[Fault AutoRecovery]]></value>\n" +
                "    </widget>\n" +
                "    <widget widgetId='FaultReasonDesciption'>\n" +
                "      <value><![CDATA[Maintainer check the system and find that the alarm is cleared Automatically!]]></value>\n" +
                "    </widget>\n" +
                "  </widgets>\n" +
                "</form>";

//        System.out.println(x);


//        Pattern pattern = Pattern.compile(".*escription.*\n.*\n.*",Pattern.MULTILINE);
//        Matcher matcher = pattern.matcher(x);
//        while (matcher.find()) {
//            System.out.print("Start index: " + matcher.start());
//            System.out.print(" End index: " + matcher.end() + " ");
//            out = matcher.group();
//            System.out.println(out);
//
//        }
        String out = "";
        Matcher matcher = Pattern.compile(".*escription.*\n.*\n.*",Pattern.MULTILINE).matcher(x);
        if(matcher.find()) {
            out = matcher.group();
            out = Pattern.compile(".*\n.*CDATA.").matcher(out).replaceAll("");
            out = Pattern.compile("..><.*\n.*").matcher(out).replaceAll("");
            System.out.println(out);
        }
    }


    }



