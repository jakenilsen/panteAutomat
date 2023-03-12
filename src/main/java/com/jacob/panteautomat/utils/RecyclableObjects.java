package com.jacob.panteautomat.utils;

public enum RecyclableObjects {
    CAN,
    BOTTLE,

    BASICOBJECT;

    public String getString() {
        switch (this) {
            case CAN: return "CAN";
            case BOTTLE: return "BOTTLE";
            case BASICOBJECT: return "BASICOBJECT";
            default: return null;
        }
    }
}
