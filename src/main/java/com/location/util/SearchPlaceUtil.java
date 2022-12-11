package com.location.util;

public class SearchPlaceUtil {
    public static String removeHtmlTag(String str) {
        return str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
    }

    public static String removeSpace(String str) {
        return str.replaceAll("\\s+","");
    }

}
