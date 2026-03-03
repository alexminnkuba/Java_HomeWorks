package org.ee.jakarta.hotelmanagersystem.entity;

public class RoomType {
    private  int id;
    private String typeName;

    public RoomType() {
    }

    public RoomType(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
