package ru.sbt.mipt.oop.remotecontrol;

public enum ButtonCode {
    A_BUTTON("A"), B_BUTTON("B")//, C_BUTTON, D_BUTTON, ONE_BUTTON, TWO_BUTTON, THREE_BUTTON, FOUR_BATTON
    ;

    public String getCode() {
        return code;
    }

    private String code;
    ButtonCode(String code) {
        this.code = code;
    }
}
