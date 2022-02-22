package com.example.newbarcode;

public class ItemID {


    public String picPro;
    public String factory;
    public String ingr;
    public String legal;
    public String name;
    public String price;
    public String sirim;





    public ItemID(String factory, String ingr, String legal, String name, String price, String sirim, String picPro) {
        this.factory = factory;
        this.ingr = ingr;
        this.legal = legal;
        this.name = name;
        this.price = price;
        this.sirim = sirim;
        this.picPro = picPro;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getIngr() {
        return ingr;
    }

    public void setIngr(String ingr) {
        this.ingr = ingr;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSirim() {
        return sirim;
    }

    public void setSirim(String sirim) {
        this.sirim = sirim;
    }

    public String getPicPro() { return picPro; }

    public void setPicPro(String picPro) { this.picPro = picPro; }







//    public ItemID(String factory, String ingr, String legal, String name, String price, String sirim)
//    {
//        this.factory = factory;
//        this.ingr = ingr;
//        this.legal = legal;
//        this.name = name;
//        this.price = price;
//        this.sirim = sirim;
//    }
}