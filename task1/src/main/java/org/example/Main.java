package org.example;

public class Main {
    public static void main(String[] args) {
        ArrayList_ArtyomGordich<String> arlist = new ArrayList_ArtyomGordich<>("Bbb", "aa", "ccc");
        System.out.println(arlist);
        System.out.println(arlist.get(2));
        System.out.println(arlist.set(1, "ff"));
        System.out.println(arlist.set(5, "ff"));
        arlist.add("ee");
        System.out.println(arlist.size());
        arlist.split(2);
        System.out.println(arlist.size());
        }
    }
