/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author macintoch
 */
public class StudentJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String derbyClientDriver = "org.apache.derby.jdbc.ClientDriver";
        //load driver
        Class.forName(derbyClientDriver);
        
        String url = "jdbc:derby://localhost:1527/student"; //url of DB
        //String url="jdbc:mysql://localhost:3306/employee?serverTimezone=UTC";
        String user = "app";
        //String user = "root";
        String passwd = "app";
        //String passwd = "root";
        Connection con = DriverManager.getConnection(url, user, passwd);
        Statement stmt = con.createStatement();
        Student std = new Student(2, "Monoka", 3.65);
        //insertStudent(con, std);
        //updateStudentGpa(con,std);
        deleteStudent(con,std);
        stmt.close();
        con.close();
    }
    
    public static void insertStudent(Connection con, Student std) throws SQLException {
      String sql = "insert into student (id, name, gpa)" + 
               " values (?,?,?)";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setInt(1, std.getId());
       ps.setString(2, std.getName());
       ps.setDouble(3, std.getGpa());
       int result = ps.executeUpdate();
        //display result
        System.out.println("Insert " + result + " row");
   } 
   public static void deleteStudent(Connection con, Student std) throws SQLException {
       String sql = "delete from student where id = ?";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setInt(1, std.getId());
       int result = ps.executeUpdate();
        //display result
        System.out.println("delete " + result + " row");
   }
   public static void updateStudentGpa(Connection con, Student std) throws SQLException {
       String sql = "update student set gpa  = ? where id = ? ";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setDouble(1, std.getGpa());
       ps.setInt(2, std.getId());
       int result = ps.executeUpdate();
        //display result
        System.out.println("update " + result + " row");
   }
    
}
