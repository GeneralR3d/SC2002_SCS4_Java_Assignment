package control;


import java.io.*;
import java.util.StringTokenizer;

import java.util.ArrayList;

import Entity.*;

public class DataController {
  public static final String SEPARATOR = "\t";
  private static ArrayList<Camp> camps;
  private static ArrayList<Staff> staffs;
  private static ArrayList<Student> students;
  private static ArrayList<CommitteeMember> commMembers;

  /**
   * Reads array list of staff and students from text file
   * @param filename
   */
  public static void init(){
    try{
      BufferedReader staffStream = new BufferedReader(new FileReader("../staff_list.txt"));
      //skip first line
      String line = staffStream.readLine();
      while(true){
        line = staffStream.readLine();
        if(line == null) break;
        StringTokenizer token = new StringTokenizer(line,SEPARATOR);

        String name = token.nextToken().trim();
        String email = token.nextToken().trim();
        String userID = email.substring(0,email.indexOf('@'));
        Faculty faculty = Faculty.valueOf(token.nextToken().trim());

        Staff staff = new Staff(name, userID, faculty);
        staffs.add(staff);

      }
      staffStream.close();
    }
    catch(FileNotFoundException e){
      System.out.println("staff_list.txt not found!\n\r"+ e.getMessage());
      System.exit(0);
    }
    catch(IOException e){
      System.out.println("file error!\n\r"+ e.getMessage());
      e.printStackTrace();
      System.exit(0);
    }

    try{
      BufferedReader studentStream = new BufferedReader(new FileReader("../student_list.txt"));
      //skip first line
      String line = studentStream.readLine();
      while(true){
        line = studentStream.readLine();
        if(line == null) break;
        StringTokenizer token = new StringTokenizer(line,SEPARATOR);

        String name = token.nextToken().trim();
        String email = token.nextToken().trim();
        String userID = email.substring(0,email.indexOf('@'));
        Faculty faculty = Faculty.valueOf(token.nextToken().trim());

        Student student = new Student(name, userID, faculty);
        students.add(student);

      }
      studentStream.close();
    }
    catch(FileNotFoundException e){
      System.out.println("student_list.txt not found!\n\r"+ e.getMessage());
      System.exit(0);
    }
    catch(IOException e){
        System.out.println("file error!\n\r"+ e.getMessage());
        e.printStackTrace();
        System.exit(0);
    }
  

  }

  public static void save(){
    try{
      BufferedWriter staffStream = new BufferedWriter(new FileWriter("../staff_list.txt"));
      //skip first line
      String line = staffStream.skip();
      for(Staff staff : staffs){

        String name = staff.getName();
        String email = staff.getUserID()+"@NTU.EDU.SG";
        String faculty = staff.getFaculty().toString();

        staffStream.write(name+"\t"+email+"\t"+faculty+"\n\r");

      }
      staffStream.close();
    }
    catch(FileNotFoundException e){
      System.out.println("staff_list.txt not found!\n\r"+ e.getMessage());
      System.exit(0);
    }
    catch(IOException e){
      System.out.println("file error!\n\r"+ e.getMessage());
      e.printStackTrace();
      System.exit(0);
    }

    try{
      BufferedWriter studentStream = new BufferedWriter(new FileWriter("../staff_list.txt"));
      //skip first line
      String line = studentStream.skip();
      for(Student student : students){

        String name = student.getName();
        String email = student.getUserID()+"@NTU.EDU.SG";
        String faculty = student.getFaculty().toString();

        studentStream.write(name+"\t"+email+"\t"+faculty+"\n\r");

      }
      studentStream.close();
    }
    catch(FileNotFoundException e){
      System.out.println("student_list.txt not found!\n\r"+ e.getMessage());
      System.exit(0);
    }
    catch(IOException e){
      System.out.println("file error!\n\r"+ e.getMessage());
      e.printStackTrace();
      System.exit(0);
    }
  

  }



  
  /** 
   * @return ArrayList<Camp>
   */
  public static ArrayList<Camp> getCamps() {
    return camps;
  }

  public static ArrayList<Staff> getStaffs() {
    return staffs;
  }

  public static ArrayList<Student> getStudents() {
    return students;
  }

  public static ArrayList<CommitteeMember> getCommMembers() {
    return commMembers;
  }

  public static ArrayList<User> getUsers() {
    ArrayList<User> users = new ArrayList<User>();
    users.addAll(getStaffs());
    users.addAll(getStudents());
    users.addAll(getCommMembers());
    return users;
  }

  public static void addCamp(Camp camp) {
    camps.add(camp);
  }

  public static void addStaff(Staff staff) {
    staffs.add(staff);
  }

  public static void addStudent(Student student) {
    students.add(student);
  }

  public static void addCommMember(CommitteeMember commMember) {
    commMembers.add(commMember);
  }

  public static void removeCamp(Camp camp) {
    camps.remove(camp);
  }

  public static void removeStaff(Staff staff) {
    staffs.remove(staff);
  }

  public static void removeStudent(Student student) {
    students.remove(student);
  }

  public static void removeCommMember(CommitteeMember commMember) {
    commMembers.remove(commMember);
  }

  public static Camp findCamp(int campID) {
    for (int i = 0; i < camps.size(); i++) {
      Camp camp = camps.get(i);
      if (campID == camp.getCampID())
        return camp;
    }
    return null;
  }

  public static Staff findStaff(String userID) {
    for (int i = 0; i < students.size(); i++) {
      Staff staff = staffs.get(i);
      if (userID.equals(staff.getUserID()))
        return staff;
    }
    return null;
  }

  public static Student findStudent(String userID) {
    for (int i = 0; i < students.size(); i++) {
      Student student = students.get(i);
      if (userID.equals(student.getUserID()))
        return student;
    }
    return null;
  }

  public static CommitteeMember findCommMember(String userID) {
    for (int i = 0; i < students.size(); i++) {
      CommitteeMember commMember = commMembers.get(i);
      if (userID.equals(commMember.getUserID()))
        return commMember;
    }
    return null;
  }

  public static User findUser(String userID) {
    ArrayList<User> users = getUsers();
    User user;
    for (int i = 0; i < users.size(); i++) {
      user = users.get(i);
      if (userID.equals(user.getUserID()))
        return user;
    }
    return null;
  }

}
