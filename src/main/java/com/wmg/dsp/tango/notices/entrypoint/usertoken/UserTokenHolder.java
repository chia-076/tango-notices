package com.wmg.dsp.tango.notices.entrypoint.usertoken;

public class UserTokenHolder {

    private static final InheritableThreadLocal<String> userTokenThreadLocal = new InheritableThreadLocal<>();

    public static void set(String userToken) {
        userTokenThreadLocal.set(userToken);
    }

    public static void unset() {
        userTokenThreadLocal.remove();
    }

    public static String get() {
        return userTokenThreadLocal.get();
    }

}
