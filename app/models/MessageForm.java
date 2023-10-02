package models;

public class MessageForm {

    private String receiver;
    private String content;

    public MessageForm() {

    }

    public MessageForm(String content, String receiver) {
        this.receiver = receiver;
        this.content = content;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNameOfreceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }
}
