package com.example.newbarcode;

public class CertedStatus {
    public String title;
    public String ingredient;
    public String certificate;
    public String pict;



    public CertedStatus() {
    }
    public CertedStatus(String title,String ingredient,String certificate,String pict){
        this.title = title;
        this.ingredient = ingredient;
        this.certificate = certificate;
        this.pict=pict;

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }
}
