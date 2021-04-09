package sample.models;

public class Surgeon {

    private int id;
    private String f_name;
    private String l_name;
    private int exp;
    private String email;
    private String password;

    public Surgeon(int id, String f_name, String l_name, int exp, String email, String password) {
        this.id = id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.exp = exp;
        this.email = email;
        this.password = password;
    }

    public Surgeon(String f_name, String l_name, int exp, String email, String password) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.exp = exp;
        this.email = email;
        this.password = password;
    }

    public Surgeon(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
