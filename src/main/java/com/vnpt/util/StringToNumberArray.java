package com.vnpt.util;

public class StringToNumberArray {
    public static long[] convert(String str){
        String[] words = str.split(",");
        int size = words.length;
        long[] arr = new long[size];
        for(int i=0; i<size; i++) {
            arr[i] = Long.parseLong(words[i]);
        }
        return arr;
    }
}
