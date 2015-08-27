package com.shurrik.codegen.util;

public class CharacterCaseUtils {
    private static final char SEPARATOR = '_';  
    
    public static String toUnderlineCase(String s) {  
        if (s == null) {  
            return null;  
        }  
  
        StringBuilder sb = new StringBuilder();  
        boolean upperCase = false;  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
  
            boolean nextUpperCase = true;  
  
            if (i < (s.length() - 1)) {  
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));  
            }  
  
            if ((i >= 0) && Character.isUpperCase(c)) {  
                if (!upperCase || !nextUpperCase) {  
                    if (i > 0) sb.append(SEPARATOR);  
                }  
                upperCase = true;  
            } else {  
                upperCase = false;  
            }  
  
            sb.append(Character.toLowerCase(c));  
        }  
  
        return sb.toString();  
    }  
  
    public static String toCamelCase(String s) {  
        if (s == null) {  
            return null;  
        }  
  
        s = s.toLowerCase();  
  
        StringBuilder sb = new StringBuilder(s.length());  
        boolean upperCase = false;  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
  
            if (c == SEPARATOR) {  
                upperCase = true;  
            } else if (upperCase) {  
                sb.append(Character.toUpperCase(c));  
                upperCase = false;  
            } else {  
                sb.append(c);  
            }  
        }  
  
        return sb.toString();  
    }  
  
    public static String toCapitalizeCamelCase(String s) {  
        if (s == null) {  
            return null;  
        }  
        s = toCamelCase(s);  
        return s.substring(0, 1).toUpperCase() + s.substring(1);  
    }  
  
    public static void main(String[] args) {  
        System.out.println(CharacterCaseUtils.toUnderlineCase("ISOCertifiedStaff"));  
        System.out.println(CharacterCaseUtils.toUnderlineCase("CertifiedStaff"));  
        System.out.println(CharacterCaseUtils.toUnderlineCase("userId"));  
        System.out.println(CharacterCaseUtils.toCamelCase("iso_certified_staff"));  
        System.out.println(CharacterCaseUtils.toCamelCase("certified_staff"));  
        System.out.println(CharacterCaseUtils.toCamelCase("user_id"));  
    }  
}
