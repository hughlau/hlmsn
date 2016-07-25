package com.hl.util;

import java.util.UUID;

public class NumberUtil {

	public static String getUUID(){
        UUID uuid  =  UUID.randomUUID();
        return uuid.toString().replaceAll("\\-", "");
    }
}
