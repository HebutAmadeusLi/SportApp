package com.example.myapplication;

public class LogAccount {
    public static String account = "";

    public static boolean isLogin() {
        return !account.equals("");
    }

    public static void login(String acc) {
        account = acc;
    }

    public static void logout() {
        account = "";
    }
}
