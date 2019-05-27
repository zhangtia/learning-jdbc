import java.util.*;
import java.sql.*;

public class JDBCListMap {

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

    public List<Map<String,Object>> example() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> nmap = new HashMap<String, Object>();
        nmap.put("fire", 472);
        nmap.put("idk", 4);
        list.add(nmap);
        Map<String, Object> amap = new HashMap<String, Object>();
        amap.put("chair", 7356);
        amap.put("fire", 222222);
        amap.put("idk", 333333);
        list.add(amap);
        Map<String, Object> znmap = new HashMap<String, Object>();
        znmap.put("chair", 17246);
        znmap.put("fire", 999999999);
        list.add(znmap);
        return list;
    }

    public void insert(List<Map<String,Object>> list) {
        for (Map<String,Object> mp : list) {
            String columns = "";
            String values = "";
            if (mp.containsKey("kills")) {
                String k = mp.get("kills").toString();
                if (columns.equals("")) columns = "(kills";
                else columns = columns + ", kills";
                if (values.equals("")) values = "(" + k;
                else values = values + "," + k;
            }
            if (mp.containsKey("chair")) {
                String c = mp.get("chair").toString();
                if (columns.equals("")) columns = "(chair";
                else columns = columns + ", chair";
                if (values.equals("")) values = "(" + c;
                else values = values + "," + c;
            }
            if (mp.containsKey("fire")) {
                String f = mp.get("fire").toString();
                if (columns.equals("")) columns = "(fire";
                else columns = columns + ", fire";
                if (values.equals("")) values = "(" + f;
                else values = values + "," + f;
            }
            if (mp.containsKey("idk")) {
                String i = mp.get("idk").toString();
                if (columns.equals("")) columns = "(idk";
                else columns = columns + ", idk";
                if (values.equals("")) values = "(" + i;
                else values = values + "," + i;
            }
            if (!columns.equals("")) {
                columns = columns + ")";
                values = values + ")";
                String command = "INSERT INTO new" + columns + " VALUES " + values;
                commit(command);
            }
        }
    }

    public static void main(String[] args) {
        JDBCListMap tq = new JDBCListMap();
        tq.getconnection();
        while (!tq.testcnct()) tq.getconnection();
        tq.insert(tq.example());
    }
}
