package sample.models;

public class Organ {

    public int id;
    public String name;
    public String type;
    // private Donor donor;
    // private Surgeon surgeon;
    public String donor;
    public String surgeon;
    public String specification;
    public int price;

    public Organ(int id, String name, String type, String donor, String surgeon, String specification, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.donor = donor;
        this.surgeon = surgeon;
        this.specification = specification;
        this.price = price;
    }

    public Organ(){

    }

    public Organ(String name, String type, String donor, String surgeon, String specification, int price) {
        this.name = name;
        this.type = type;
        this.donor = donor;
        this.surgeon = surgeon;
        this.specification = specification;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getSurgeon() {
        return surgeon;
    }

    public void setSurgeon(String surgeon) {
        this.surgeon = surgeon;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
