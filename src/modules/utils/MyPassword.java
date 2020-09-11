package modules.utils;

public class MyPassword {

    private String key;
    private String salt;

    public MyPassword() {
    }

    public MyPassword(String key, String salt) {
        this.key = key;
        this.salt = salt;
    }

    public String getKey() {
        return key;
    }

    public String getSalt() {
        return salt;
    }

    public void setPassword_hash(String key) {
        this.key = key;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String toFileString() {
        return key + "MyPass" + salt;
    }

    public static MyPassword parseMyPassword(String password) {
        String[] tokens = password.split("MyPass");
        return new MyPassword(tokens[0], tokens[1]);
    }
}
