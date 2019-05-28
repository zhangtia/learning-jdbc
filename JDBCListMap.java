import java.util.*;
import java.sql.*;

public class JDBCListMap {

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

    private void commit(String command) {

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
        System.out.println(command);
    }

    private List<Map<String,Object>> example() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> nmap = new HashMap<String, Object>();
        nmap.put("chair", '3');
        nmap.put("fire", 472);
        nmap.put("idk", "I need EDM when coding");
        list.add(nmap);
        Map<String, Object> amap = new HashMap<String, Object>();
        amap.put("chair", "yhkggi");
        //amap.put("kills", 55);
        amap.put("idk", 333333);
        list.add(amap);
        Map<String, Object> znmap = new HashMap<String, Object>();
        znmap.put("chair", 17246);
        znmap.put("fire", 999999999);
        znmap.put("idk", 611534);
        //znmap.put("kills", 5);
        list.add(znmap);
        Map<String, Object> emmap = new HashMap<String, Object>();
        list.add(emmap);
        return list;
    }

    private void insert(List<Map<String,Object>> list) {
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
                if (values.equals("")) values = "('" + c + "'";
                else values = values + ",'" + c + "'";
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
                if (values.equals("")) values = "('" + i + "'";
                else values = values + ",'" + i + "'";
            }
            if (!columns.equals("")) {
                columns = columns + ")";
                values = values + ")";
                String command = "INSERT INTO new" + columns + " VALUES " + values;
                commit(command);
            }
        }
    }

    private void delete(List<Map<String,Object>> list) {
        for (Map<String,Object> mp : list) {
            String values = "";
            if (mp.containsKey("kills")) {
                String k = mp.get("kills").toString();
                if (values.equals("")) values = "kills = " + k;
                else values = values + " AND kills = " + k;
            }
            if (mp.containsKey("chair")) {
                String c = mp.get("chair").toString();
                if (values.equals("")) values = "chair = '" + c + "'";
                else values = values + " AND chair = '" + c + "'";
            }
            if (mp.containsKey("fire")) {
                String f = mp.get("fire").toString();
                if (values.equals("")) values = "fire = " + f;
                else values = values + " AND fire = " + f;
            }
            if (mp.containsKey("idk")) {
                String i = mp.get("idk").toString();
                if (values.equals("")) values = "idk = '" + i + "'";
                else values = values + " AND idk = '" + i + "'";
            }
            if (!values.equals("")) {
                String command = "DELETE FROM new WHERE " + values;
                commit(command);
            }
        }
    }

    public static void main(String[] args) {
        JDBCListMap tq = new JDBCListMap();
        tq.getconnection();
        while (!tq.testcnct()) tq.getconnection();
        tq.insert(tq.example());
        tq.delete(tq.example());
    }
}
