package com.reads.observer.enumerations;

public enum ResponseMessages {

    SaveNOTES("Notes was Successfully saved");
    private String message;
    ResponseMessages(String message) {
        this.message = message;
    }
}
