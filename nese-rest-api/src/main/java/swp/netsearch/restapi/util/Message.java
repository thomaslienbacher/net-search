package swp.netsearch.restapi.util;

import com.google.gson.GsonBuilder;

/**
 * Created on 29.12.2019.
 *
 * @author Thomas Lienbacher
 */
public class Message {

    private String message;

    public Message() {
        this("undefined message");
    }

    public Message(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        var gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
