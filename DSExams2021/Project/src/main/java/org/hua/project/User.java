package org.hua.project;

public class User {
    String RowNum;
    String Gender;
    String num1;
    String num2;
    String num3;

    public User(String rowNum, String gender, String num1, String num2, String num3) {
        RowNum = rowNum; //Αυτό είναι το UserID
        Gender = gender;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public String getRowNum() {
        return RowNum;
    }

    public void setRowNum(String rowNum) {
        RowNum = rowNum;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getNum3() {
        return num3;
    }

    public void setNum3(String num3) {
        this.num3 = num3;
    }
}
