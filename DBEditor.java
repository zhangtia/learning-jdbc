import java.util.Scanner;
import java.sql.*;

public class DBEditor {

    static String driver = "";
    static String url = "";
    static String user = "";
    static String password = "";

    public void getconnection() {
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

    public boolean testcnct() {
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

    public void work() {
        System.out.println();
        System.out.println("=== TYPE Q TO QUIT ===");
        System.out.println("Input Command Type");
        System.out.println("Command Types and Shortcuts:");
        System.out.println("Select - s");
        System.out.println("Insert - i");
        System.out.println("Update - u");
        System.out.println("Delete - d");
        System.out.println();
        System.out.print("Command Type : ");
        Scanner sc = new Scanner(System.in);
        String cmdtype = sc.nextLine();
        System.out.println();
        //if (cmdtype.equals("q") || cmdtype.equals("Q")) return;
        //else if (cmdtype.equals("s") || cmdtype.equals("S")) select();
        //else if (cmdtype.equals("i") || cmdtype.equals("I")) insert();
        //else if (cmdtype.equals("u") || cmdtype.equals("U")) update();
        //else if (cmdtype.equals("d") || cmdtype.equals("D")) delete();
        //else work();

        switch (cmdtype) {
            case "q":
                return;
            case "Q":
                return;
            case "s":
                select();
                break;
            case "S":
                select();
                break;
            case "i":
                insert();
                break;
            case "I":
                insert();
                break;
            case "u":
                update();
                break;
            case "U":
                update();
                break;
            case "d":
                delete();
                break;
            case "D":
                delete();
                break;
            default:
                work();

        }
    }

    public void commit(String command) {
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

    public void select() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Columns to select: ");
        String clmn = sc.nextLine();
        if (clmn.equals("")) clmn = "*";
        if (clmn.equalsIgnoreCase("q")) return;
        System.out.print("From table: ");
        String tbl = sc.nextLine();
        if (tbl.equalsIgnoreCase("q")) return;
        System.out.print("Where constraint: ");
        String constr = sc.nextLine();
        if (constr.equalsIgnoreCase("q")) return;
        if (!constr.equals("")) constr = " WHERE " + constr;
        String command = "SELECT " + clmn + " FROM " + tbl + constr;
        commit(command);
    }

    public void insert(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Table to insert: ");
        String tbl = sc.nextLine();
        if (tbl.equalsIgnoreCase("q")) return;
        System.out.print("Specific columns: ");
        String clmn = sc.nextLine();
        if (clmn.equalsIgnoreCase("q")) return;
        if (!clmn.equals("")) clmn = "(" + clmn + ")";
        System.out.print("Values to add: ");
        String constr = sc.nextLine();
        if (constr.equalsIgnoreCase("q")) return;
        if (!constr.equals("")) constr = "(" + constr + ")";
        String command = "INSERT INTO " + tbl + clmn + " VALUES " + constr;
        commit(command);
    }

    public void update(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Table to update: ");
        String tbl = sc.nextLine();
        if (tbl.equalsIgnoreCase("q")) return;
        System.out.print("Columns to update: ");
        String clmn = sc.nextLine();
        if (clmn.equalsIgnoreCase("q")) return;
        System.out.print("Where constraint: ");
        String constr = sc.nextLine();
        if (constr.equalsIgnoreCase("q")) return;
        String command = "UPDATE " + tbl + " SET " + clmn + " WHERE " + constr;
        commit(command);
    }

    public void delete(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Table to delete from: ");
        String tbl = sc.nextLine();
        if (tbl.equalsIgnoreCase("q")) return;
        System.out.print("Where constraints: ");
        String constr = sc.nextLine();
        if (constr.equalsIgnoreCase("q")) return;
        if (!constr.equals("")) constr = " WHERE " + constr;
        String command = "DELETE FROM " + tbl + constr;
        commit(command);
    }

    public static void main(String[] args){
        DBEditor tq = new DBEditor();
        tq.getconnection();
        while (!tq.testcnct()) tq.getconnection();
        tq.work();
    }

}
