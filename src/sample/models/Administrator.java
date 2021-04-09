package sample.models;

public class Administrator {

    private int id;
    private String f_name;
    private String l_name;
    private String email;
    private String phone;
    private String password;

    public Administrator(int id, String f_name, String l_name, String email, String phone, String password) {
        this.id = id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Administrator(String f_name, String l_name, String email, String phone, String password) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Administrator(){}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
