public class SQLClass {

    private static boolean killschanged = false;
    private static boolean firechanged = false;
    private static boolean chairchanged = false;
    private static boolean idkchanged = false;

    private int kills;
    public boolean hasKills() {return killschanged;}
    public int getKills() {return kills;}
    public void setKills(int val) {
        this.kills = val;
        killschanged = true;
    }

    private double fire;
    public boolean hasFire() {return firechanged;}
    public double getFire() {return fire;}
    public void setFire(double val) {
        this.fire = val;
        firechanged = true;
    }

    private String chair;
    public boolean hasChair() {return chairchanged;}
    public String getChair() {return chair;}
    public void setChair(String val) {
        this.chair = val;
        chairchanged = true;
    }

    private String idk;
    public boolean hasIdk() {return idkchanged;}
    public String getIdk() {return idk;}
    public void setIdk(String val) {
        this.idk = val;
        idkchanged = true;
    }

}
