package app.entities;

public class Message {
    private String from;
    private String to;
    private String messageBody;

    public Message(String from, String to, String messageBody) {
        this.from = from;
        this.to = to;
        this.messageBody = messageBody;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return this.from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageBody() {
        return this.messageBody;
    }
}
// Step-2