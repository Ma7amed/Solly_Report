package sample.model;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by m80028770 on 4/22/2017.
 */
public class ExcelWritter {


    private int MAX_ROW_COUNT = 3000;


    public ExcelWritter() {

    }


    public void writeTTData2(ArrayList<SollyTicket> ticketList, File file) {
        System.out.println("ExcelWritter.writeTTData");

        OPCPackage pkg = null;
        try {
            pkg = OPCPackage.open(file);
            XSSFWorkbook wb = new XSSFWorkbook(pkg);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("Sheet1");

        Row header = sheet1.createRow(0);

        header.createCell(0).setCellValue("Ticket ID");
        header.createCell(1).setCellValue("Title");
        header.createCell(2).setCellValue("Confirm Time");
        header.createCell(3).setCellValue("Operator");
        header.createCell(4).setCellValue("Operator Group");
        header.createCell(5).setCellValue("Originator");
        header.createCell(6).setCellValue("Phase");
        header.createCell(7).setCellValue("Business Status");
        header.createCell(8).setCellValue("Operation TIme");
        header.createCell(9).setCellValue("Creation TIme");


        for (int i = 0; i < ticketList.size(); i++) {
            System.out.println("ExcelWritter.writeTTData, writing raw: " + i);
            Row row = sheet1.createRow(i + 1);

            row.createCell(0).setCellValue(ticketList.get(i).getTicket_id());
            row.createCell(1).setCellValue(ticketList.get(i).getTicket_title());
            row.createCell(2).setCellValue(ticketList.get(i).getTicket_confirm_time());
            row.createCell(3).setCellValue(ticketList.get(i).getTicket_operator());
            row.createCell(4).setCellValue(ticketList.get(i).getTicket_operator_group());
            row.createCell(5).setCellValue(ticketList.get(i).getTicket_originator());
            row.createCell(6).setCellValue(ticketList.get(i).getTicket_phase());
            row.createCell(7).setCellValue(ticketList.get(i).getTicket_business_status());
            row.createCell(8).setCellValue(ticketList.get(i).getTicket_operation_time());
            row.createCell(9).setCellValue(ticketList.get(i).getTicket_creation_time());


        }


        try {
            pkg.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ExcelWritter.writeTTData: done");
    }


    public void writeTTDataByRange(ArrayList<SollyTicket> ticketList, File file, int start, int limit) {


    }

    public void writeTTData(ArrayList<SollyTicket> ticketList, File file) {
        System.out.println("ExcelWritter.writeTTData");

        System.out.println("ExcelWritter.writeTTData ... checking records count");



            Workbook wb = new XSSFWorkbook();
            Sheet sheet1 = wb.createSheet("Sheet1");

            Row header = sheet1.createRow(0);

            header.createCell(0).setCellValue("Ticket ID");
            header.createCell(1).setCellValue("Title");
            header.createCell(2).setCellValue("Confirm Time");
            header.createCell(3).setCellValue("Operator");
            header.createCell(4).setCellValue("Operator Group");
            header.createCell(5).setCellValue("Originator");
            header.createCell(6).setCellValue("Phase");
            header.createCell(7).setCellValue("Business Status");
            header.createCell(8).setCellValue("Operation TIme");
            header.createCell(9).setCellValue("Creation TIme");
            header.createCell(9).setCellValue("Description");


            for (int i = 0; i < ticketList.size(); i++) {
                System.out.println("ExcelWritter.writeTTData, writing raw: " + i);
                Row row = sheet1.createRow(i + 1);

                row.createCell(0).setCellValue(ticketList.get(i).getTicket_id());
                row.createCell(1).setCellValue(ticketList.get(i).getTicket_title());
                row.createCell(2).setCellValue(ticketList.get(i).getTicket_confirm_time());
                row.createCell(3).setCellValue(ticketList.get(i).getTicket_operator());
                row.createCell(4).setCellValue(ticketList.get(i).getTicket_operator_group());
                row.createCell(5).setCellValue(ticketList.get(i).getTicket_originator());
                row.createCell(6).setCellValue(ticketList.get(i).getTicket_phase());
                row.createCell(7).setCellValue(ticketList.get(i).getTicket_business_status());
                row.createCell(8).setCellValue(ticketList.get(i).getTicket_operation_time());
                row.createCell(9).setCellValue(ticketList.get(i).getTicket_creation_time());
                row.createCell(9).setCellValue(ticketList.get(i).getTicket_description());



            }


            try {
                System.out.println("ExcelWritter.writeTTData: FileOutputStream");
                FileOutputStream fileOut = new FileOutputStream(file);
                System.out.println("ExcelWritter.writeTTData: write");
                wb.write(fileOut);
                System.out.println("ExcelWritter.writeTTData: close");
                fileOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



        System.out.println("ExcelWritter.writeTTData: done");
    }

    public void writeWOData(ArrayList<SollyWorkOrder> workOrderList, File file) {
        System.out.println("ExcelWritter.writeWOData");

        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("Sheet1");

        Row header = sheet1.createRow(0);

        header.createCell(0).setCellValue("Parent Ticket ID");
        header.createCell(1).setCellValue("Ticket Originator");
        header.createCell(2).setCellValue("Workorder ID");
        header.createCell(3).setCellValue("WO Originator");
        header.createCell(4).setCellValue("WO Title");
        header.createCell(5).setCellValue("WO Operator");
        header.createCell(6).setCellValue("WO Operator Group");
        header.createCell(7).setCellValue("WO Phase");
        header.createCell(8).setCellValue("WO Business Status");
        header.createCell(9).setCellValue("WO Opeartion Time");
        header.createCell(10).setCellValue("Ticket Creation Time");


        for (int i = 0; i < workOrderList.size(); i++) {
            Row row = sheet1.createRow(i + 1);

            row.createCell(0).setCellValue(workOrderList.get(i).getParent_ticket_id());
            row.createCell(1).setCellValue(workOrderList.get(i).getTicket_originator());
            row.createCell(2).setCellValue(workOrderList.get(i).getWorkorder_id());
            row.createCell(3).setCellValue(workOrderList.get(i).getWorkorder_originator());
            row.createCell(4).setCellValue(workOrderList.get(i).getWorkorder_title());
            row.createCell(5).setCellValue(workOrderList.get(i).getWorkorder_operator());
            row.createCell(6).setCellValue(workOrderList.get(i).getWorkorder_operator_group());
            row.createCell(7).setCellValue(workOrderList.get(i).getWorkorder_phase());
            row.createCell(8).setCellValue(workOrderList.get(i).getWorkorder_business_status());
            row.createCell(9).setCellValue(workOrderList.get(i).getWorkorder_operation_time());
            row.createCell(10).setCellValue(workOrderList.get(i).getTicket_creation_time());


        }


        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        File file = new File("C:\\Users\\m80028770.CHINA\\test1.xlsx");
        ArrayList<SollyTicket> ticketList = new ArrayList<SollyTicket>();

        SollyTicket t1 = new SollyTicket("TT-1234fffff", "A", "B", "C", "D", "E",
                "F", "G", "H", "Y");
        SollyTicket t2 = new SollyTicket("TT-11423vvvv", "I", "J", "K", "L",
                "M", "N", "O", "P", "Y");

        ticketList.add(t1);
        ticketList.add(t2);


        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("Sheet1");

        Row header = sheet1.createRow(0);

        header.createCell(0).setCellValue("Ticket ID");
        header.createCell(1).setCellValue("Title");
        header.createCell(2).setCellValue("Confirm Time");
        header.createCell(3).setCellValue("Operator");
        header.createCell(4).setCellValue("Operator Group");
        header.createCell(5).setCellValue("Originator");
        header.createCell(6).setCellValue("Phase");
        header.createCell(7).setCellValue("Business Status");
        header.createCell(8).setCellValue("Operation TIme");
        header.createCell(9).setCellValue("Creation TIme");

        for (int i = 0; i < ticketList.size(); i++) {
            Row row = sheet1.createRow(i + 1);

            row.createCell(0).setCellValue(ticketList.get(i).getTicket_id());
            row.createCell(1).setCellValue(ticketList.get(i).getTicket_title());
            row.createCell(2).setCellValue(ticketList.get(i).getTicket_confirm_time());
            row.createCell(3).setCellValue(ticketList.get(i).getTicket_operator());
            row.createCell(4).setCellValue(ticketList.get(i).getTicket_operator_group());
            row.createCell(5).setCellValue(ticketList.get(i).getTicket_originator());
            row.createCell(6).setCellValue(ticketList.get(i).getTicket_phase());
            row.createCell(7).setCellValue(ticketList.get(i).getTicket_business_status());
            row.createCell(8).setCellValue(ticketList.get(i).getTicket_operation_time());
            row.createCell(9).setCellValue(ticketList.get(i).getTicket_creation_time());
        }


        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
