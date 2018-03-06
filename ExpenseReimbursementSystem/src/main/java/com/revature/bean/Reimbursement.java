package com.revature.bean;

import java.util.Date;

public class Reimbursement{
    //members
    private int reimburseID;
    private double amount;
    private String description;
    private String receipt; //it's an image, can probably store image url??
    private Date submitted;
    private Date resolved;
    private User author;
    private User resolver;
    private String rType; //Reimbursement type
    private String rStatus; //Reimbursement Status

    //default Constructor
    public Reimbursement(){
        this.reimburseID = 0;
        this.amount = 0.0;
        this.description = "";
        this.receipt = ""; //??
        this.submitted = new Date();
        this.resolved = new Date();
        this.author = new User();
        this.resolver = new User();
        this.rType = "";
        this.rStatus = "";
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
    public Reimbursement(int newRID, double newAmount, String newDescription, String newReceipt, Date newSubmitted, Date newResolved, User newAuthor, User newResolver, String newRType, String newRStatus){
        this.reimburseID = newRID;
        this.amount = newAmount;
        this.description = newDescription;
        this.receipt = newReceipt; //??
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

    public String getReceipt(){
        return this.receipt;
    }

    public Date getSubmitted(){
        return this.submitted;
    }

    public Date getResolved(){
        return this.resolved;
    }

    public User getAuthor(){
        return this.author;
    }

    public User getResolver(){
        return this.resolver;
    }
    
    public String getRType(){
        return this.rType;
    }
    
    public String getRStatus(){
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

    public void setReceipt(String newReceipt){
        this.receipt = newReceipt;
    }

    public void setSubmitted(Date newSubmitted){
        this.submitted = newSubmitted;
    }

    public void setResolved(Date newResolved){
        this.resolved = newResolved;
    }

    public void setAuthor(User newAuthor){
        this.author = newAuthor;
    }

    public void setResolver(User newResolver){
        this.resolver = newResolver;
    }
    
    public void setRType(String newRType){
        this.rType = newRType;
    }
    
    public void setRStatus(String newRStatus){
        this.rStatus = newRStatus;
    }

    //ToString Override
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder(
                          "Reimbursement ID: " + this.reimburseID + 
                          "Amount: " + this.amount + 
                          "Description: " + this.description + "\n" +
                          "Receipt: " + this.receipt +
                          "Date Submitted: " + this.submitted + 
                          "Date Resolved: " + this.resolved + 
                          "Author: " + this.author.toString());
        if(this.resolver != null){
            s.append("Resolver: " + this.resolver.toString());
        }
        else{
            s.append("Resolver: N/A");
        }
        s.append("Reimbursement type: " + this.rType +
                 "Reimbursement status: " + this.rStatus);
        return s.toString();
    }
}