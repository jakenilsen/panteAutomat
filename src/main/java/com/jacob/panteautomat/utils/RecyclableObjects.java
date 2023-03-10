package com.jacob.panteautomat.utils;

public enum RecyclableObjects {
    CAN,
    BOTTLE;

    public String getString() {
        switch (this) {
            case CAN: return "CAN";
            case BOTTLE: return "BOTTLE";
            default: return null;
        }
    }
}
