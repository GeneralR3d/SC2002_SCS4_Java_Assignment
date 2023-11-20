package com.ApachePOI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Entity.Student;

public class DataController{

    public static ArrayList<Student> students;
    public static ArrayList<Staff> staffs;
    public static ArrayList<Camp> camps;

    public static init(){
        File student_list = new File("student_list.xlsx");
        FileInputStream student_list_stream = new FileInputStream(student_list);
        XSSFWorkbook students_workbook = new XSSFWorkbook(student_list_stream);
        XSSFSheet student_sheet = students_workbook.getSheetAt(0);

        int rowCount = student_sheet.getPhysicalNumberOfRows();

        for(int i =1;i<rowCount;i++){
            XSSFRow row = student_sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            String userID = row.getCell(1).getStringCellValue().substring(0,row.getCell(1).index('@')-1);
            String faculty = row.getCell(2).getStringCellValue();
            Student currentStudent = new Student(name,userID,faculty);
            students.add(currentStudent);
        }

        students_workbook.close();
        student_list_stream.close();
        student_list.close();

        File staff_list = new File("staff_list.xlsx");
        FileInputStream staff_list_stream = new FileInputStream(staff_list);
        XSSFWorkbook staff_workbook = new XSSFWorkbook(staff_list_stream);
        XSSFSheet staff_sheet = staff_workbook.getSheetAt(0);

        int rowCount = staff_sheet.getPhysicalNumberOfRows();

        for(int i =1;i<rowCount;i++){
            XSSFRow row = staff_sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            String userID = row.getCell(1).getStringCellValue().substring(0,row.getCell(1).index('@')-1);
            String faculty = row.getCell(2).getStringCellValue();
            Staff currentStaff = new Staff(name,userID,faculty);
            staffs.add(currentStaff);
        }

        staff_workbook.close();
        staff_list_stream.close();
        staff_list.close();


        File camp_list = new File("camp_list.xlsx");
        FileInputStream camp_list_stream = new FileInputStream(camp_list);
        XSSFWorkbook camp_workbook = new XSSFWorkbook(camp_list_stream);
        XSSFSheet camp_sheet = camp_workbook.getSheetAt(0);

        int rowCount = camp_sheet.getPhysicalNumberOfRows();

        for(int i =1;i<rowCount;i++){

            //TODO
            XSSFRow row = camp_sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            String userID = row.getCell(1).getStringCellValue().substring(0,row.getCell(1).index('@')-1);
            String faculty = row.getCell(2).getStringCellValue();
            Staff currentStaff = new Staff(name,userID,faculty);
            staffs.add(currentStaff);
        }

        staff_workbook.close();
        staff_list_stream.close();
        staff_list.close();



    }
}