package lk.ijse.HelthCare.entity;

public class Patient {
    private String pId;
    private String name;
    private String address;
    private String contact;

    public Patient(String pId, String name, String address, String contact) {
        this.pId = pId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
