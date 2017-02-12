package ru.iammaxim.GaledwellLang;

/**
 * Created by maxim on 2/12/17 at 11:32 AM.
 */
public class Utils {
    public static String[] splitByComma(String src) {
        return src.split("[ \\t]*,[ \\t]*");
    }
}
