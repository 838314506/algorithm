package com.lzz.algorithm;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;

//@SpringBootApplication
public class AlgorithmApplication {

    public static void main(String[] args) {
        AlgorithmApplication a = new AlgorithmApplication();
//        a.m(3);
        int m = a.m(3);
    }

    public int m(int n){
        if (n == 1) return n;
        return n * m(n - 1);
    }

}
