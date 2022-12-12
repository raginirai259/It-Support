package com.support.it.entity;

public enum Stage {

    InQueue ("Queued"),
    InProgress ("InProgress"),
    Resolved ("Resolved");

    private final String name;

    Stage(String name) {this.name = name;}

    public String getName() {return name;}

}
