package com.giang.Slytherin.constant;

public enum Status {
    ACTIVATE(1),
    HIDE(0),
    DELETED(-1);

    private Integer active;

    Status(Integer active) {
        this.active = active;
    }

    public Integer getValue() {
        return active;
    }
}
