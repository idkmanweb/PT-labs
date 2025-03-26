package org.example;

import java.io.Serializable;

public class Message implements Serializable {
    private int number;
    private String content;

    Message(int n, String content){
        number = n;
        this.content = content;
    }

    Message(Message mess){
        number = mess.getNumber();
        content = mess.getContent();
    }

    public int getNumber() {
        return number;
    }

    public String getContent() {
        return content;
    }
}
