package com.makotomiyamoto.ntenchant.meta;

import java.util.HashMap;

public abstract class Roman {
    private static HashMap<Integer, String> keys = new HashMap<>();
    static {
        keys.put(1, "I");
        keys.put(2, "II");
        keys.put(3, "III");
        keys.put(4, "IV");
        keys.put(5, "V");
        keys.put(6, "VI");
        keys.put(7, "VII");
        keys.put(8, "VIII");
        keys.put(9, "IX");
        keys.put(10, "X");
    }
    public static String toRoman(int num) {
        return keys.get(num);
    }
}
