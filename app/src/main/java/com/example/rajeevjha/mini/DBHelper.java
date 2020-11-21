package com.example.rajeevjha.mini;

public class DBHelper {
    private String company;
    private String pname;
    private String price;
    private String quantity;
    private String barcode;

    public DBHelper() {

    }


    public DBHelper(String com, String pn, String pr, String quan, String bcode) {
        this.company = com;
        this.pname = pn;
        this.price = pr;
        this.quantity = quan;
        this.barcode=bcode;
    }

    @Override
    public String toString() {
       /* return  "Barcode="+barcode+"\t"+
                " Product=" + pname + "\t" +
                " Price=" + price + "\t" +
                " Company =" + company + "\t" +
                "Quantity =" + quantity;*/
        return
                 pname + "\t" +
                  price + "\t" +
                 company + "\t";



    }
    public String getpname() {
        return pname;
    }

    public void setpname(String pname) {
        this.pname = pname;
    }
    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;

    }
    public String getcompany() {
        return company;
    }

    public void setcompany(String company) {
        this.company = company;
    }
    public String getquantity() {
        return quantity;
    }

    public void setquantity(String quantity) {
        this.quantity = quantity;
    }
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String bcode) {
        this.barcode = bcode;
    }
    public static DBHelper valueOf(DBHelper value) {
        return null;
    }
}