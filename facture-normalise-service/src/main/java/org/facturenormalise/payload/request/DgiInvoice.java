package org.facturenormalise.payload.request;

import java.util.ArrayList;

public class DgiInvoice {
    public String ifu;
    public String aib;
    public String type;
    public ArrayList<Item> items;
    public Client client;
    public Operator operator;
    public ArrayList<Payment> payment;
}
