package org.ee.jakarta.hotelmanagersystem.entity;

public enum RoomStatus {
    FREE("Свободен"),
    OCCUPIED("Занят"),
    CLEANING("Уборка"),
    MAINTENANCE("Ремонт / Обслуживание"),
    OUT_OF_ORDER("Неисправен / Выключен");

    private final String displayName;

    RoomStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static RoomStatus fromString(String text) {
        if (text != null) {
            for (RoomStatus b : values()) {
                if (b.name().equalsIgnoreCase(text) || b.displayName.equalsIgnoreCase(text)) {
                    return b;
                }
            }
        }
        return FREE;
    }
}
