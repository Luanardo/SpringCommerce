package com.springcommerce.midterm.util;

import java.util.UUID;

public class ImageUtil {

    public static String generateUniqueFileName() {
        long timestamp = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        return timestamp + "_" + uuid;
    }
}
