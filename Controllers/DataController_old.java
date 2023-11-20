package com.ApachePOI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Entity.*;

public class DataController{

    public static ArrayList<Student> students;
    public static ArrayList<Staff> staffs;
    public static ArrayList<Camp> camps;

    public static void init(){
        FileInputStream student_list_stream = new FileInputStream(new File("student_list.xlsx"));
        XSSFWorkbook students_workbook = new XSSFWorkbook(student_list_stream);
        XSSFSheet student_sheet = students_workbook.getSheetAt(0);


        for(int i = 1, rowCount = student_sheet.getPhysicalNumberOfRows() ; i<rowCount ; i++){
            XSSFRow row = student_sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            String userID = row.getCell(1).getStringCellValue().substring(0,row.getCell(1).index('@')-1);
            Faculty faculty = Faculty.valueOf(row.getCell(2).getStringCellValue());
            Student currentStudent = new Student(name,userID,faculty);
            students.add(currentStudent);
        }

        students_workbook.close();
        student_list_stream.close();

        FileInputStream staff_list_stream = new FileInputStream(new File("staff_list.xlsx"));
        XSSFWorkbook staff_workbook = new XSSFWorkbook(staff_list_stream);
        XSSFSheet staff_sheet = staff_workbook.getSheetAt(0);


        for(int i = 1 , rowCount = staff_sheet.getPhysicalNumberOfRows(); i < rowCount; i++){
            XSSFRow row = staff_sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            String userID = row.getCell(1).getStringCellValue().substring(0,row.getCell(1).index('@')-1);
            Faculty faculty = Faculty.valueOf(row.getCell(2).getStringCellValue());
            Staff currentStaff = new Staff(name,userID,faculty);
            staffs.add(currentStaff);
        }

        staff_workbook.close();
        staff_list_stream.close();


        // File camp_list = new File("camp_list.xlsx");
        // FileInputStream camp_list_stream = new FileInputStream(camp_list);
        // XSSFWorkbook camp_workbook = new XSSFWorkbook(camp_list_stream);
        // XSSFSheet camp_sheet = camp_workbook.getSheetAt(0);

        // int rowCount = camp_sheet.getPhysicalNumberOfRows();

        // for(int i =1;i<rowCount;i++){

        //     //TODO
        //     XSSFRow row = camp_sheet.getRow(i);
        //     String name = row.getCell(0).getStringCellValue();
        //     String userID = row.getCell(1).getStringCellValue().substring(0,row.getCell(1).index('@')-1);
        //     String faculty = row.getCell(2).getStringCellValue();
        //     Staff currentStaff = new Staff(name,userID,faculty);
        //     staffs.add(currentStaff);
        // }

        // staff_workbook.close();
        // staff_list_stream.close();

    }

    public static ArrayList<Student> getAllStudents(){
        return students;
    }

    public static ArrayList<Staff> getAllStaff(){
        return staffs;
    }

    public static ArrayList<Camp> getAllCamps(){
        return camps;
    }
}
