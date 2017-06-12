package com.tony.test.protocol;

import java.util.ArrayList;

public class TestReturnObjectList {

    private String    message;

    private ArrayList<Par> par;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Par> getPar() {
        return par;
    }

    public void setPar(ArrayList<Par> par) {
        this.par = par;
    }

    @Override
    public String toString() {
        return "TestReturnObjectList [message=" + message + "]";
    }


   
}
