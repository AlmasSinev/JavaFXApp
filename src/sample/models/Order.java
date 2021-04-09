package sample.models;

public class Order {

    private int id;
    private String node;
    private String admin;
    private String buyer;
    private int price;
    private String method;

    public Order(int id, String node, String admin, String buyer, int price, String method) {
        this.id = id;
        this.node = node;
        this.admin = admin;
        this.buyer = buyer;
        this.price = price;
        this.method = method;
    }

    public Order(String node, String admin, String buyer, int price, String method) {
        this.id = id;
        this.node = node;
        this.admin = admin;
        this.buyer = buyer;
        this.price = price;
        this.method = method;
    }

    public Order(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
