package org.ee.jakarta.hotelmanagersystem.entity;

import java.sql.Date;

public class Booking {
    private int id;
    private int guestId;
    private String guestName;
    private int roomTypeId;
    private String roomTypeName;
    private Date checkIn;
    private Date checkOut;
    private String status;

    public Booking() {
    }

    public Booking(int id, int guestId, String guestName, int roomTypeId, String roomTypeName, Date checkIn, Date checkOut, String status) {
        this.id = id;
        this.guestId = guestId;
        this.guestName = guestName;
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
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

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
