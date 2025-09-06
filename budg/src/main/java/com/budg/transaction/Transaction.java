package com.budg.transaction;

import java.time.LocalDate;

public class Transaction {
    public LocalDate Date;
    public String Description;
    public Float Amount;
    public Category Category;

    public Transaction(LocalDate date, String description, Float amount, Category category) {
        this.Date = date;
        this.Description = description;
        this.Amount = amount;
        this.Category = category;
    }
}