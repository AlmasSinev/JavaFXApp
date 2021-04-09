package sample.models;

import java.util.Date;

public class OrderNode {

    private int id;
    private String code;
    private Date date;
    private int organId;
    private int surgeonId;
    private int price;

    public OrderNode(int id, String code, Date date, int organId, int surgeonId, int price) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.organId = organId;
        this.surgeonId = surgeonId;
        this.price = price;
    }

    public OrderNode( String code, Date date, int organId, int surgeonId, int price) {
        this.code = code;
        this.date = date;
        this.organId = organId;
        this.surgeonId = surgeonId;
        this.price = price;
    }

    public OrderNode(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOrganId() {
        return organId;
    }

    public void setOrganId(int organId) {
        this.organId = organId;
    }

    public int getSurgeonId() {
        return surgeonId;
    }

    public void setSurgeonId(int surgeonId) {
        this.surgeonId = surgeonId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
