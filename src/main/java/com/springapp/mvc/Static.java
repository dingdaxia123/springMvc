package com.springapp.mvc;

/**
 * Created by dinghy on 2017/7/12.
 */
public class Static {

    private String sex;
    public static String name = "ding123";

    static class InnerClass1{
        public static String nameOne = "chen_static";

        public void display(){
            System.out.println("OutClass name:"+name);
        }
    }

    class InnerClass2{
        public String nameTwo = "chen_inner";

        public void display(){
            System.out.println("OutClass name:"+name);
        }
    }

    public void display(){
        System.out.println(InnerClass1.nameOne);
        new InnerClass1().display();

        Static.InnerClass2 inner2 = new Static().new InnerClass2();
        System.out.println(inner2.nameTwo);
        inner2.display();
    }

    public static void main(String[] args){
        Static outer = new Static();
        outer.display();
    }
}
