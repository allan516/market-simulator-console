package model;

import helper.Utils;

public class Product {
    private static int counter = 1;

    private int code;
    private String name;
    private Double price;

   public Product(String name, Double price) {
       this.code = Product.counter;
       this.name = name;
       this.price = price;
       Product.counter += 1;
   }

   public int getCode() {
       return this.code;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString() {
       return "Código: " + this.getCode() +
                "\nNome: " + this.getName() +
               "\nPreço: " + Utils.doubleToString(this.getPrice());
    }
}
