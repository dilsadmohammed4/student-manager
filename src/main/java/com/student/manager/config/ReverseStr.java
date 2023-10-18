package com.student.manager.config;

public class ReverseStr {
    public static void main(String[] args) {
        String str="Dilsad";
        StringBuilder sb=new StringBuilder(str);
        sb.reverse();
        System.out.println(sb);
        StringBuffer sf=new StringBuffer(sb);
        sf.reverse();
        System.out.println(sf);
    }
}
