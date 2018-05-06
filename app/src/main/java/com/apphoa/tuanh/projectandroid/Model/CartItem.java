package com.apphoa.tuanh.projectandroid.Model;

/**
 * Created by TuAnh on 4/20/2018.
 */

public class CartItem  {
    private Flower flower;
    int count;

    public CartItem(Flower flower, int count) {
        this.flower = flower;
        this.count = count;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
