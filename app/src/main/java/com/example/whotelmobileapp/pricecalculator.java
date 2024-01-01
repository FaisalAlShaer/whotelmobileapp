package com.example.whotelmobileapp;

public class pricecalculator {
    public pricecalculator() {
    }

    public static double calculateTotalPrice(String roomType, int numberOfNights){
            double roomPrice = getRoomPrice(roomType);
            return  numberOfNights * roomPrice;
        }
        private  static double getRoomPrice(String roomType){
            switch (roomType){
                case "King" :
                    return 209.0;
                case "regular":
                    return 159.0;
                default:
                    throw new IllegalArgumentException("Invalid room type"+roomType);
            }
        }
}
