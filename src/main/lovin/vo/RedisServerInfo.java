package lovin.vo;

/**
 * Created by bixin on 2017/12/19.
 */
public class RedisServerInfo {
    private String ip;
    private int port;
    private int db;
    private String pass;

    public RedisServerInfo(String ip, String pass, int db, int port) {
        this.ip = ip;
        this.pass = pass;
        this.db = db;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
