package org.ee.jakarta.hotelmanagersystem.entity;

import java.math.BigDecimal;

public class Room {
    private int id;
    private String roomNumber;
    private int roomTypeId;
    private String roomTypeName;     // для отображения
    private int floor;
    private BigDecimal pricePerNight;
    private RoomStatus status;
    private String amenities;
    private String notes;

    public Room() {
    }

    public Room(int id, String roomNumber, int roomTypeId, String roomTypeName, int floor, BigDecimal pricePerNight, RoomStatus status, String amenities, String notes) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
        this.floor = floor;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.amenities = amenities;
        this.notes = notes;
    }

    public Room(RoomStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatusDisplay() {
        return status != null ? status.getDisplayName() : "—";
    }
}
