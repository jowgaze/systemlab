package com.systemlab.domain.session;

public enum ShiftSession {
    MANHA("Manhã"),
    TARDE("Tarde"),
    NOITE("Noite");

    private String shift;

    ShiftSession(String shift){
        this.shift = shift;
    }

    public String getShift(){
        return shift;
    }
}
