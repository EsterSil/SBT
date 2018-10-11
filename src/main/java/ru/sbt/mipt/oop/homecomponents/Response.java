package ru.sbt.mipt.oop.homecomponents;

public class Response {
    private Status status;

    public Status getStatus() {
        return status;
    }

    private String message;

    public Response(Status status) {
        this.status = status;
    }

    public Response(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void updateMessage(String addiction) {
        this.message = message+addiction;
    }
}
