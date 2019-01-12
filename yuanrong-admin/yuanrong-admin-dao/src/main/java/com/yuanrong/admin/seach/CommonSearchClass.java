package com.yuanrong.admin.seach;

import java.io.Serializable;

public class CommonSearchClass implements Serializable{
    private String collectionServer;
    private String accountServer;
    private String authorServer;
    private String invoiceCOLLECTION;
    private String invoiceAuthor;
    private String invoiceAccount;
    public CommonSearchClass(){}

    public CommonSearchClass(String collectionServer, String accountServer, String authorServer, String invoiceCOLLECTION, String invoiceAuthor, String invoiceAccount) {
        this.collectionServer = collectionServer;
        this.accountServer = accountServer;
        this.authorServer = authorServer;
        this.invoiceCOLLECTION = invoiceCOLLECTION;
        this.invoiceAuthor = invoiceAuthor;
        this.invoiceAccount = invoiceAccount;
    }

    public String getAccountServer() {
        return accountServer;
    }

    public void setAccountServer(String accountServer) {
        this.accountServer = accountServer;
    }

    public String getAuthorServer() {
        return authorServer;
    }

    public void setAuthorServer(String authorServer) {
        this.authorServer = authorServer;
    }

    public String getInvoiceCOLLECTION() {
        return invoiceCOLLECTION;
    }

    public void setInvoiceCOLLECTION(String invoiceCOLLECTION) {
        this.invoiceCOLLECTION = invoiceCOLLECTION;
    }

    public String getInvoiceAuthor() {
        return invoiceAuthor;
    }

    public void setInvoiceAuthor(String invoiceAuthor) {
        this.invoiceAuthor = invoiceAuthor;
    }

    public String getInvoiceAccount() {
        return invoiceAccount;
    }

    public void setInvoiceAccount(String invoiceAccount) {
        this.invoiceAccount = invoiceAccount;
    }

    public String getCollectionServer() {
        return collectionServer;
    }

    public void setCollectionServer(String collectionServer) {
        this.collectionServer = collectionServer;
    }
}
