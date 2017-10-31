package mainPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author MOKLESUR RAHMAN
 * @version 01
 */
public class DbHelper {

    private String sName;
    private String fName;
    private String fContact;
    private String gender;
    private String birth;
    private String address;
    private String roll;
    private String sClass;
    private String season;
    private String aFees;
    private String aDate;
    Connection conn = null;

    Sql s = new Sql();

    public DbHelper(String sName, String fName, String fContact, String gender, String birth, String address, String roll, String sClass, String season, String afees, String aDate) {
        conn = s.ConnectDb();
        this.sName = sName;
        this.fName = fName;
        this.fContact = fContact;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.roll = roll;
        this.sClass = sClass;
        this.season = season;
        this.aFees = afees;
        this.aDate = aDate;
    }

    public DbHelper() {
        conn = s.ConnectDb();
    }

    public void insert() {
        String sql = "INSERT INTO admission(sname, fname, fcontact, sgender, birth, address, roll, class,season, afees, adate) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sName);
            pstmt.setString(2, fName);
            pstmt.setString(3, fContact);
            pstmt.setString(4, gender);
            pstmt.setString(5, birth);
            pstmt.setString(6, address);
            pstmt.setString(7, roll);
            pstmt.setString(8, sClass);
            pstmt.setString(9, season);
            pstmt.setString(10, aFees);
            pstmt.setString(11, aDate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void insert(String Fname, String email, String Uname, String Upass, String databaseName) {
        String sql = "INSERT INTO " + databaseName + "(fname, email, puser, ppassword) VALUES(?,?,?,?)";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Fname);
            pstmt.setString(2, email);
            pstmt.setString(3, Uname);
            pstmt.setString(4, Upass);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void selectForLogin(String userName, String userPass, String formName) {
        String sql = "SELECT id, puser, ppassword FROM p_login WHERE puser='" + userName + "' AND ppassword='" + userPass + "'";

        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if ((userName.equals("")) || userPass.equals("")) {
                JOptionPane.showMessageDialog(null, "PLEASE FILL USERNAME AND PASSWORD.");
            } else if (rs.next()) {
                if (formName.equals("p")) {
                    Principle_Data p = new Principle_Data();
                    p.setVisible(true);
                    PLogin pl = new PLogin();
                    pl.setVisible(false);
                } else if (formName.equals("s")) {
                    Student_Data sd = new Student_Data();
                    sd.setVisible(true);
                    SLogin sl = new SLogin();
                    sl.setVisible(false);
                } else if (formName.equals("t")) {
                    Teacher_Data td = new Teacher_Data();
                    td.setVisible(true);
                    TLogin tl = new TLogin();
                    tl.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "USERNAME OR PASSWORD INCORRECT");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
}
