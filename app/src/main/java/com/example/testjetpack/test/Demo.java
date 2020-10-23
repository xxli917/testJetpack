package com.example.testjetpack.test;

public class Demo extends BaseDemo {
    private int mA;
    public Demo(String type,int a) {
        super(type);
        mA = a;
    }

    public class Math{
        private int mA;
        private int mB;
        public Math(int a,int b){
            mA = a;
            mB = b;
        }
        public int add(){
            return mA+mB;
        }
    }
}
