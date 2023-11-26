package control;

import java.io.*;
import java.util.StringTokenizer;

import entity.Camp;
import entity.CommitteeMember;
import entity.Faculty;
import entity.Staff;
import entity.Student;
import entity.User;

import java.util.ArrayList;


/**
 * Stores real-time data of all camps, staff, students and commitee members in memory
 * <div> Acts as run-time database 
 * <div> Reads tab-seperated values
 */
public class DataController {
    public static final String SEPARATOR = "\t";
    private static ArrayList<Camp> camps;
    private static ArrayList<Staff> staffs;
    private static ArrayList<Student> students;
    private static ArrayList<CommitteeMember> commMembers;

    /**
     * Reads array list of {@link Staff} and {@link Student} from text file and creates the objects in memory
     * <div> Currently hardcoded to read {@code staff_list.txt} and {@code student_list.txt}
     * 
     */
    public static void init() {
        camps = new ArrayList<Camp>();
        staffs = new ArrayList<Staff>();
        students = new ArrayList<Student>();
        commMembers = new ArrayList<CommitteeMember>();
        try {
            BufferedReader staffStream = new BufferedReader(new FileReader("staff_list.txt"));
            // skip first line
            String line = staffStream.readLine();
            while (true) {
                line = staffStream.readLine();
                if (line == null)
                    break;
                StringTokenizer token = new StringTokenizer(line, SEPARATOR);

                String name = token.nextToken().trim();
                String email = token.nextToken().trim();
                String userID = email.substring(0, email.indexOf('@'));
                Faculty faculty = Faculty.valueOf(token.nextToken().trim());

                Staff staff = new Staff(name, userID, faculty);
                staffs.add(staff);

            }
            staffStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("staff_list.txt not found!\n\r" + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("file error!\n\r" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }

        try {
            BufferedReader studentStream = new BufferedReader(new FileReader("student_list.txt"));
            // skip first line
            String line = studentStream.readLine();
            while (true) {
                line = studentStream.readLine();
                if (line == null)
                    break;
                StringTokenizer token = new StringTokenizer(line, SEPARATOR);

                String name = token.nextToken().trim();
                String email = token.nextToken().trim();
                String userID = email.substring(0, email.indexOf('@'));
                Faculty faculty = Faculty.valueOf(token.nextToken().trim());

                Student student = new Student(name, userID, faculty);
                students.add(student);

            }
            studentStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("student_list.txt not found!\n\r" + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("file error!\n\r" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }

    }

    /**
     * Only writes back name, userID and {@link Faculty} of staffs and students
     * <div> Does not save password changes or any information about created {@link Camp}s or {@link CommitteeMember}
     */
    public static void save() {
        try {
            BufferedWriter staffStream = new BufferedWriter(new FileWriter("staff_list.txt"));
            // skip first line
            staffStream.write("Name\tEmail\tFaculty\n");
            for (Staff staff : staffs) {

                String name = staff.getName();
                String email = staff.getUserID() + "@NTU.EDU.SG";
                String faculty = staff.getFaculty().toString();

                staffStream.write(name + "\t" + email + "\t" + faculty + "\r");

            }
            staffStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("staff_list.txt not found!\n\r" + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("file error!\n\r" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }

        try {
            BufferedWriter studentStream = new BufferedWriter(new FileWriter("student_list.txt"));
            // skip first line
            studentStream.write("Name\tEmail\tFaculty\n");
            for (CommitteeMember commMember : commMembers) {

                String name = commMember.getName();
                String email = commMember.getUserID() + "@NTU.EDU.SG";
                String faculty = commMember.getFaculty().toString();

                studentStream.write(name + "\t" + email + "\t" + faculty + "\r");
            }
            for (Student student : students) {

                String name = student.getName();
                String email = student.getUserID() + "@NTU.EDU.SG";
                String faculty = student.getFaculty().toString();

                studentStream.write(name + "\t" + email + "\t" + faculty + "\r");

            }
            studentStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("student_list.txt not found!\n\r" + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("file error!\n\r" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Returns all the camps created
     * @return ArrayList<Camp> {@link java.util.ArrayList}
     */
    public static ArrayList<Camp> getCamps() {
        return camps;
    }

    /**
     * Returns all staff
     * @return ArrayList<Staff> {@link java.util.ArrayList}
     */
    public static ArrayList<Staff> getStaffs() {
        return staffs;
    }

     /**
     * Returns all students
     * @return ArrayList<Student> {@link java.util.ArrayList}
     */
    public static ArrayList<Student> getStudents() {
        return students;
    }

     /**
     * Returns all commitee members
     * @return ArrayList<CommitteeMember> {@link java.util.ArrayList}
     */
    public static ArrayList<CommitteeMember> getCommMembers() {
        return commMembers;
    }

     /**
     * Returns all users including {@link Staff}, {@link Student} and {@link CommitteeMember}
     * @return ArrayList<User> all users are upcasted to super-class type {@link User}
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.addAll(getStaffs());
        users.addAll(getStudents());
        users.addAll(getCommMembers());
        return users;
    }

    /**
     * Adds a {@link Camp} to list of camps
     * @param camp
     */
    public static void addCamp(Camp camp) {
        camps.add(camp);
    }

    /**
     * Adds a {@link Staff} to list of staffs
     * @param staff
     */
    public static void addStaff(Staff staff) {
        staffs.add(staff);
    }

    /**
     * Adds a {@link Student} to list of students
     * @param student
     */
    public static void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Adds a {@link CommitteeMember} to list of committeeMembers
     * @param commMember
     */
    public static void addCommMember(CommitteeMember commMember) {
        commMembers.add(commMember);
    }

     /**
     * Removes a {@link Camp} from list of camps
     * @param camp
     */
    public static void removeCamp(Camp camp) {
        camps.remove(camp);
    }
     /**
     * Removes a {@link Staff} from list of staffs
     * @param staff
     */
    public static void removeStaff(Staff staff) {
        staffs.remove(staff);
    }
    /**
     * Removes a {@link Student} from list of students
     * @param student
     */
    public static void removeStudent(Student student) {
        students.remove(student);
    }
    /**
     * Removes a {@link CommitteeMember} from list of committeeMembers
     * @param commMember
     */
    public static void removeCommMember(CommitteeMember commMember) {
        commMembers.remove(commMember);
    }

    /**
     * Returns a {@link Camp} according to a campID
     * @param campID
     * @return {@link Camp} if found
     * @return {@code null} it not found
     */
    public static Camp findCamp(int campID) {
        for (int i = 0; i < camps.size(); i++) {
            Camp camp = camps.get(i);
            if (campID == camp.getCampID())
                return camp;
        }
        return null;
    }

    /**
     * Returns a {@link Staff} according to a userID
     * @param userID
     * @return {@link Staff} if found
     * @return {@code null} it not found
     */
    public static Staff findStaff(String userID) {
        for (int i = 0; i < students.size(); i++) {
            Staff staff = staffs.get(i);
            if (userID.equals(staff.getUserID()))
                return staff;
        }
        return null;
    }

    /**
     * Returns a {@link Student} according to a userID
     * @param userID
     * @return {@link Student} if found
     * @return {@code null} it not found
     */
    public static Student findStudent(String userID) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (userID.equals(student.getUserID()))
                return student;
        }
        return null;
    }

    /**
     * Returns a {@link CommitteeMember} according to a userID
     * @param userID
     * @return {@link CommitteeMember} if found
     * @return {@code null} it not found
     */
    public static CommitteeMember findCommMember(String userID) {
        for (int i = 0; i < students.size(); i++) {
            CommitteeMember commMember = commMembers.get(i);
            if (userID.equals(commMember.getUserID()))
                return commMember;
        }
        return null;
    }

     /**
     * Returns a {@link User} according to a userID
     * <div>Used in {@link UserController}
     * @param userID
     * @return {@link User} if found
     * @return {@code null} it not found
     */
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
