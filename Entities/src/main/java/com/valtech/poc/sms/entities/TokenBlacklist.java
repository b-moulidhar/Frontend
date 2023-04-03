package com.valtech.poc.sms.entities;

import java.util.HashSet;
import java.util.Set;

public class TokenBlacklist {

    private static Set<String> blacklist = new HashSet<>();

    public static void add(String token) {
        blacklist.add(token);
    }

    public static boolean contains(String token) {
        return blacklist.contains(token);
    }

}
