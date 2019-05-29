import java.util.*;
import java.sql.*;

public class JDBCListMap {

    // ======== Pre-fill in for testing =========
    static String driver = "";
    static String url = "";
    static String user = "";
    static String password = "";
    // ======== Pre-fill in for testing =========
    
    private static int batchsize = 10;
    
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

    private void commit(List<List<String>> command, String sql) {

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //String sql = "INSERT INTO new (chair,fire,idk) VALUES (?, ?, ?);";


        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            int counter = 0;
            for (List<String> entry : command) {
                ++counter;
                for (int i = 1; i <= entry.size(); ++i) {
                    ps.setString(i,entry.get(i-1));
                }
                System.out.println(ps);
                ps.addBatch();
                if (counter%batchsize == 0) ps.executeBatch();
            }
            ps.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        amap.put("fire", 55);
        amap.put("idk", 333333);
        list.add(amap);
        Map<String, Object> znmap = new HashMap<String, Object>();
        znmap.put("chair", 17246);
        znmap.put("fire", 999999999);
        znmap.put("idk", 611534);
        //znmap.put("kills", 5);
        list.add(znmap);
        Map<String, Object> emmap = new HashMap<String, Object>();
        emmap.put("fire", 6);
        list.add(emmap);
        return list;
    }

    private void insert(List<Map<String,Object>> list) {
        List<List<String>> command = new ArrayList<>();
        for (Map<String,Object> mp : list) {
            List<String> entry = new ArrayList<>();
            //String a = "null";
            String b = "null";
            String c = "null";
            String d = "null";
            //if (mp.containsKey("kills")) {a = mp.get("kills").toString();}
            if (mp.containsKey("chair")) {b = mp.get("chair").toString();}
            if (mp.containsKey("fire")) {c = mp.get("fire").toString();}
            if (mp.containsKey("idk")) {d = mp.get("idk").toString();}
            //entry.add(a);
            entry.add(b);
            entry.add(c);
            entry.add(d);
            command.add(entry);
        }
        commit(command, "INSERT INTO new (chair,fire,idk) VALUES (?, ?, ?);");
    }

    private void delete(List<Map<String,Object>> list) {
        List<List<String>> command = new ArrayList<>();
        for (Map<String,Object> mp : list) {
            List<String> entry = new ArrayList<>();
            String b = "null";
            String c = "null";
            String d = "null";
            if (mp.containsKey("chair")) {b = mp.get("chair").toString();}
            if (mp.containsKey("fire")) {c = mp.get("fire").toString();}
            if (mp.containsKey("idk")) {d = mp.get("idk").toString();}
            entry.add(b);
            entry.add(c);
            entry.add(d);
            command.add(entry);
        }
        commit(command, "DELETE FROM new WHERE chair = ? AND FIRE = ? AND IDK = ?;");
    }

    public static void main(String[] args) {
        JDBCListMap tq = new JDBCListMap();
        tq.getconnection();
        while (!tq.testcnct()) tq.getconnection();
        tq.insert(tq.example());
        tq.delete(tq.example());
    }
}
