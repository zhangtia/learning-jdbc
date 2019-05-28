import java.util.*;
import java.sql.*;

public class JDBCClass {

    static String driver = "";
    static String url = "";
    static String user = "";
    static String password = "";


    private void getconnection() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input Driver: ");
        driver = "com.mysql.jdbc." + sc.nextLine();
        System.out.print("Input Address: ");
        String adr = sc.nextLine();
        System.out.print("Input DBname: ");
        url = "jdbc:mysql://" + adr + ":3306/" + sc.nextLine() + "?serverTimezone=UTC";
        System.out.print("Input User: ");
        user = sc.nextLine();
        System.out.print("Input Pass: ");
        password = sc.nextLine();
    }

    private boolean testcnct() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("***********************");
            System.out.println("* Connection success! *");
            System.out.println("***********************");
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid connection, Please input a valid connection");
            System.out.println("***************************************************");
            return false;
        } catch (SQLException e) {
            System.out.println("Invalid connection, Please input a valid connection");
            System.out.println("***************************************************");
            return false;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Closing error, Please input a valid connection");
            System.out.println("**********************************************");
            return false;
        }
        return true;
    }

    private SQLClass example1() {
        SQLClass entry = new SQLClass();
        entry.setChair("wooden");
        entry.setFire(31415);
        entry.setIdk("gonna buy airpods");
        return entry;
    }

    private SQLClass example2() {
        SQLClass entry = new SQLClass();
        entry.setChair("plastic");
        entry.setFire(27182);
        entry.setIdk("time to save money");
        return entry;
    }

    private void insertNew(SQLClass entry){
        String clmn = "";
        String vals = "";
        if (entry.hasKills()){
            if (clmn.equals("")) clmn = "(kills";
            else clmn = clmn + ", kills";
            if (vals.equals("")) vals = "(" + entry.getKills();
            else vals = vals + ", " + entry.getKills();
        }
        if (entry.hasFire()){
            if (clmn.equals("")) clmn = "(fire";
            else clmn = clmn + ", fire";
            if (vals.equals("")) vals = "(" + entry.getFire();
            else vals = vals + ", " + entry.getFire();
        }
        if (entry.hasChair()){
            if (clmn.equals("")) clmn = "(chair";
            else clmn = clmn + ", chair";
            if (vals.equals("")) vals = "('" + entry.getChair() + "'";
            else vals = vals + ", '" + entry.getChair() + "'";
        }
        if (entry.hasIdk()){
            if (clmn.equals("")) clmn = "(idk";
            else clmn = clmn + ", idk";
            if (vals.equals("")) vals = "('" + entry.getIdk() + "'";
            else vals = vals + ", '" + entry.getIdk() + "'";
        }
        if (!clmn.equals("")){
            clmn = clmn + ")";
            vals = vals + ")";
            String command = "INSERT INTO new" + clmn + " VALUES " + vals;
            commit(command);
        }
    }

    private void deleteNew(SQLClass entry){
        String vals = "";
        if (entry.hasKills()){
            if (vals.equals("")) vals = "kills = " + entry.getKills();
            else vals = vals + " AND kills = " + entry.getKills();
        }
        if (entry.hasFire()){
            if (vals.equals("")) vals = "fire = " + entry.getFire();
            else vals = vals + " AND fire = " + entry.getFire();
        }
        if (entry.hasChair()){
            if (vals.equals("")) vals = "chair = '" + entry.getChair() + "'";
            else vals = vals + " AND chair = '" + entry.getChair() + "'";
        }
        if (entry.hasIdk()){
            if (vals.equals("")) vals = "idk = '" + entry.getIdk() + "'";
            else vals = vals + " AND idk = '" + entry.getIdk() + "'";
        }
        if (!vals.equals("")){
            String command = "DELETE FROM new WHERE " + vals;
            commit(command);
        }
    }

    private void commit(String command) {
        String confirm = "";
        Scanner sc = new Scanner(System.in);
        while (!confirm.equalsIgnoreCase("Y") && !confirm.equalsIgnoreCase("N")) {
            System.out.println(command);
            System.out.println("FINAL CHECK, TYPE Y TO PROCEED, N TO DISCARD");
            confirm = sc.nextLine();
        }
        if (confirm.equalsIgnoreCase("N")) return;

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement ps = conn.prepareStatement(command);
            ps.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBCClass tq = new JDBCClass();
        tq.getconnection();
        while (!tq.testcnct()) tq.getconnection();
        SQLClass temp2 = tq.example2();
        SQLClass temp1 = tq.example1();
        tq.insertNew(temp2);
        tq.insertNew(temp1);
        tq.deleteNew(temp2);
    }
}
