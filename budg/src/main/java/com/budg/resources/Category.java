package com.budg.resources;

public enum Category {
    Unassigned(0),
    Transfer(-1),

    Income(1),
    Savings(2),

    Donations(77),
    Tithing(7),
    FastOffering(8),

    Loan1(11),
    Loan2(12),

    Rent(100),
    Medical(103),

    Grocery(200),
    Dining(201),

    Household(300),
    Clothing(301),
    Amazon(302),

    Education(350),

    Travel(5000),
    VehicleInsurance(5001),
    VehicleGas(5002),
    VehicleOil(5003),

    Phone(6001),
    Adobe(6002),
    Spotify(6003),
    AmazonPrime(6005),
    OtherSubscriptions(6999),

    Emtertainment(7000);

    private final int value;

    private Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
