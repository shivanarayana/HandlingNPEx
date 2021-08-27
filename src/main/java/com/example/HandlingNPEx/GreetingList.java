package com.example.HandlingNPEx;

import java.util.List;

public class GreetingList {

    public List<Greeting> greetingObject;

    public GreetingList(List<Greeting> greetingObject) {
        this.greetingObject = greetingObject;
    }

    public GreetingList() {
//        Greeting g1 = new Greeting("Hello, Spring! This is flux");
//        Greeting g2 = new Greeting("Hello, Spring! This is flux");
//        greetingObject.add(g1);
//        greetingObject.add(g2);
    }

    public List<Greeting> getGreetingObject() {
        return greetingObject;
    }

    public void setGreetingObject(List<Greeting> greetingObject) {
        this.greetingObject = greetingObject;
    }
}
