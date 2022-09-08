package com.example.androidlearn.json;

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:45 2022/4/29
 */
public class Student {
    private String name;
    private int no;

    public Student(int no,String name){
        this.no=no;
        this.name = name;
    }
    private Add add;
    public Student(int no,String name,Add add){
        this.no=no;
        this.name = name;
        this.add =add;
    }
    public static class Add{
        private String id;
        public Add(String id){
            this.id=id;
        }
    }
}
