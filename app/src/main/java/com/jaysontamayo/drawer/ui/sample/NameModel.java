package com.jaysontamayo.drawer.ui.sample;

public class NameModel {
    public int nameID;
    public String lastName;
    public String firstName;

    public NameModel(int nameID, String lastName, String firstName){
        this.nameID = nameID;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "NameModel{" +
                "nameID=" + nameID +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
