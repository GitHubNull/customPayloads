package top.oxff.models;


import java.util.Objects;

public class Payload {
    int id;
    String tag;
    String payloadStr;
    Boolean enable;
    String comment;


    public Payload(int id, String tag, String payloadStr, Boolean enable, String comment) {
        this.id = id;
        this.tag = tag;
        this.payloadStr = payloadStr;
        this.enable = enable;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPayloadStr() {
        return payloadStr;
    }

    public void setPayloadStr(String payloadStr) {
        this.payloadStr = payloadStr;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payload payload = (Payload) o;
        return id == payload.id && Objects.equals(tag, payload.tag) && Objects.equals(payloadStr, payload.payloadStr) && Objects.equals(comment, payload.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag, payloadStr, comment);
    }
}
