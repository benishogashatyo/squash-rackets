package database.dto;

import java.sql.Date;

public class CustomerDTO {
    private int userID;
    private String userName;
    private Date birthdate;
    private int gender;
    private int racket_weight;
    private int racket_balance;
    private int hardness;
    private int manufacturer;
    private String error;

    public int getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        try {
            this.userID = Integer.parseInt(userID);
        }catch (NumberFormatException e){
            this.error = "ユーザーIDを正しい形式で入力してください";
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        try {
            this.birthdate = java.sql.Date.valueOf(birthdate);
        }catch (IllegalArgumentException e){
            this.error = "誕生日を正しい形式で入力してください";
        }
    }

    public int getGender() {
        return gender;
    }

    public void setGender(String gender) {
        try {
            this.gender = Integer.parseInt(gender);
        }catch (NumberFormatException e){
            this.error = "性別を正しい形式で入力してください";
        }
    }

    public int getRacket_weight() {
        return racket_weight;
    }

    public void setRacket_weight(String racket_weight) {
        try {
            this.racket_weight = Integer.parseInt(racket_weight);
        }catch (NumberFormatException e){
            this.error = "ラケットの重さを正しい形式で入力してください";
        }
    }

    public int getRacket_balance() {
        return racket_balance;
    }

    public void setRacket_balance(String racket_balance) {
        try {
            this.racket_balance = Integer.parseInt(racket_balance);
        }catch (NumberFormatException e){
            this.error = "バランスポイントを正しい形式で入力してください";
        }
    }

    public int getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        try {
            this.hardness = Integer.parseInt(hardness);
        }catch (NumberFormatException e){
            this.error = "硬さを正しい形式で入力してください";
        }
    }

    public int getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        try {
            this.manufacturer = Integer.parseInt(manufacturer);
        }catch (NumberFormatException e){
            this.error = "好みのメーカーを正しい形式で入力してください";
        }
    }

    public String getError() {
        return error;
    }
}
