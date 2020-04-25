package com.giang.Slytherin.constant;

public enum UserSource {
    ANDROID("ANDROID"),
    IOS("IOS"),
    WEB("WEB");

    private String source;

    UserSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return source;
    }


}
