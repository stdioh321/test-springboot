package com.api.parkingcontrol.utils;

import java.util.UUID;

public class Utils {
    public static boolean isValidUUID(String uuidString) {
        try {
            UUID.fromString(uuidString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
