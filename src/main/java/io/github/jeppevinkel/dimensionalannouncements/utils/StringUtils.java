package io.github.jeppevinkel.dimensionalannouncements.utils;

public class StringUtils {
    public static String replaceTemplates(String text, String templatePattern, String replacementValue) {
        return text.replaceAll(templatePattern, replacementValue);
    }
}
