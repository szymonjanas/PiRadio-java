package com.janas.PiRadio.Support;

public class OSValidator {
    private static final String TAG = OSValidator.class.getSimpleName();

    public enum OS{
        linux,
        windows,
        unknown,
    }

    private static String systemName = System.getProperty("os.name").toLowerCase();

    public static OS getSystemName(){
        if (systemName.indexOf("win") >= 0) return OS.windows;
        else if (systemName.indexOf("nux")>=0) return OS.linux;
        else {
            System.out.println(TAG + ": System not recognized!");
            return OS.unknown;
        }
    }

}
