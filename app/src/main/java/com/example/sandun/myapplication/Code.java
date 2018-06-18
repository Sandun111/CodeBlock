package com.example.sandun.myapplication;

public class Code {

    // fields

    private int codeID;
    private String Code;
    private String Description;

    // constructors

    public Code() {}

    public Code(String code, String description) {


        this.Code = code;
        this.Description = description;

    }

    // properties

    public void setID(int id) {
        this.codeID = id;
    }

    public int getID() {
        return this.codeID;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public String getCode() {
        return this.Code;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getDescription() {
        return this.Description;
    }


}

