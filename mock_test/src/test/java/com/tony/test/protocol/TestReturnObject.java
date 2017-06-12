package com.tony.test.protocol;

public class TestReturnObject {
    
    private String message;
    
    
    private Par par;


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public Par getPar() {
        return par;
    }


    public void setPar(Par par) {
        this.par = par;
    }


    @Override
    public String toString() {
        return "TestReturnObject [message=" + message + ", par=" + par + "]";
    }
    
}
