package com.revature.bean;

import java.sql.Date;

public class Reimbursement{
    //members
    private int reimburseID;
    private double amount;
    private String description;
    private byte[] receipt;
    private Date submitted;
    private Date resolved;
    private int author;
    private int resolver;
    private int rType; //Reimbursement type
    private int rStatus; //Reimbursement Status

    //default Constructor
    public Reimbursement(){
        this.reimburseID = 0;
        this.amount = 0.0;
        this.description = "";
        this.receipt = null;
        this.submitted = null;
        this.resolved = null;
        this.author = 0;
        this.resolver = 0;
        this.rType = 0;
        this.rStatus = 0;
    }

    //copy Constructor
    public Reimbursement(Reimbursement r){
        this.reimburseID = r.reimburseID;
        this.amount = r.amount;
        this.description = r.description;
        this.receipt = r.receipt; //??
        this.submitted = r.submitted;
        this.resolved = r.resolved;
        this.author = r.author;
        this.resolver = r.resolver;
        this.rType = r.rType; 
        this.rStatus = r.rStatus;
    }

    //parm Constructor
    public Reimbursement(int newRID, double newAmount, String newDescription,
    					 Byte[] newReceipt, Date newSubmitted, Date newResolved, 
    					 int newAuthor, int newResolver, int newRType, 
    					 int newRStatus){
        this.reimburseID = newRID;
        this.amount = newAmount;
        this.description = newDescription;
        this.receipt = null;
        this.submitted = newSubmitted;
        this.resolved = newResolved;
        this.author = newAuthor;
        this.resolver = newResolver;
        this.rType = newRType; 
        this.rStatus = newRStatus;
    }

    //Getters
    public int getRID(){
        return this.reimburseID;
    }

    public double getAmount(){
        return this.amount;
    }

    public String getDescription(){
        return this.description;
    }

    public byte[] getReceipt(){
        return this.receipt;
    }

    public Date getSubmitted(){
        return this.submitted;
    }

    public Date getResolved(){
        return this.resolved;
    }

    public int getAuthor(){
        return this.author;
    }

    public int getResolver(){
        return this.resolver;
    }
    
    public int getRType(){
        return this.rType;
    }
    
    public int getRStatus(){
        return this.rStatus;
    }

    //Setters
    public void setRID(int newRID){
        this.reimburseID = newRID;
    }

    public void setAmount(double newAmount){
        this.amount = newAmount;
    }

    public void setDescription(String newDescription){
        this.description = newDescription;
    }

    public void setReceipt(byte[] newReceipt){
        this.receipt = newReceipt;
    }

    public void setSubmitted(Date newSubmitted){
        this.submitted = newSubmitted;
    }

    public void setResolved(Date newResolved){
        this.resolved = newResolved;
    }

    public void setAuthor(int newAuthor){
        this.author = newAuthor;
    }

    public void setResolver(int newResolver){
        this.resolver = newResolver;
    }
    
    public void setRType(int newRType){
        this.rType = newRType;
    }
    
    public void setRStatus(int newRStatus){
        this.rStatus = newRStatus;
    }

    //ToString Override
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder(
                          "Reimbursement ID: " + this.reimburseID + "\n" + 
                          "Amount: " + this.amount +  "\n" +
                          "Description: " + this.description + "\n");
        //blobs shouldn't be printed out as strings??	
        //if(this.receipt != null) {
        	//s.append("Receipt: " + this.receipt);
        //}
        
        s.append("Date Submitted: " + this.submitted + "\n" +
        		 "Date Resolved: " + this.resolved + "\n" + 
        		 "Author: " + this.author);
        if(this.resolver != 0){
            s.append("Resolver: " + this.resolver + "\n");
        }
        else{
            s.append("Resolver: N/A\n");
        }
        s.append("Reimbursement type: " + this.rType + "\n" +
                 "Reimbursement status: " + this.rStatus + "\n");
        return s.toString();
    }
}