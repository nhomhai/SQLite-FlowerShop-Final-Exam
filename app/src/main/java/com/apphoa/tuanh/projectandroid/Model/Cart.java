package com.apphoa.tuanh.projectandroid.Model;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by TuAnh on 4/17/2018.
 */

public class Cart extends Application {
    private static  ArrayList<CartItem> cart ;

    public static  ArrayList<CartItem> getCart() {
        if(cart == null)
            cart = new ArrayList<>();
        return cart;
    }

    public  void setCart(ArrayList<CartItem> cart) {
        this.cart = cart;
    }

    public static CartItem getFlower(int index){
        if(cart == null)
        {
            cart = new ArrayList<>();
            return  null;
        }
        return cart.get(index);
    }
    public static void  AddToCart(Flower fl){
        if(cart == null)
            cart = new ArrayList<>();
        CartItem c = Contains(fl);
        if(c == null)
        {
            CartItem cartItem = new CartItem(fl,1);
            cart.add(cartItem);
        }
        else c.setCount(c.getCount()+1);
        //Toast.makeText(g,"da them vao gio hang",Toast.LENGTH_SHORT).show();
    }
    public static int getCartSize(){
        return cart.size();
    }
    public  static CartItem Contains(Flower fl){
        if(cart == null) return null;
        for (CartItem c : cart){
            if(c.getFlower().equals(fl))
                return c;
        }
        return  null;
    }
    public static void Remove(int index){
        if(cart == null)
            cart = new ArrayList<>();
        cart.remove(index);
    }
    public  static double Sum(){
        if (cart==null) return 0;
        double sum =0;
        for(CartItem c : cart){
            {
                Flower f = c.getFlower();
                double gia = Double.parseDouble(f.getGia());
                sum += (double)c.getCount()*gia;
            }
        }
        return sum;
    }
    public static void EmptyCart(){
        cart = null;
    }

}
