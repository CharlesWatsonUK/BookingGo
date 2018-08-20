package com.BookingGo;

public class Supplier {

    private String supplierName;
    private String url;

    public Supplier(String supplierName, String url) {
        this.supplierName = supplierName;
        this.url = url;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
