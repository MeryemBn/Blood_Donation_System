package models;

public class BloodGroupList {
    private int id;
    private String bloodGroup;
    private int quantity;

    public BloodGroupList() {
    }
    public BloodGroupList(String bloodGroup, int quantity) {
        this.bloodGroup = bloodGroup;
        this.quantity = quantity;
    }
    public BloodGroupList(int id, String bloodGroup, int quantity) {
        this.id = id;
        this.bloodGroup = bloodGroup;
        this.quantity = quantity;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
