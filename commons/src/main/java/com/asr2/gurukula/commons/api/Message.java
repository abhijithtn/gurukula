package com.asr2.gurukula.commons.api;

import javax.faces.application.FacesMessage;

/**
 * Created by abhijith.nagarajan on 7/21/15.
 */
public class Message extends FacesMessage {

    public Message(String summary) {
        super(summary);
    }

    public Message(String summary, String detail) {
        super(summary, detail);
    }

    public Message(Severity severity, String summary) {
        super(severity, summary, summary);
    }

    public Message(Severity severity, String summary, String detail) {
        super(severity, summary, detail);
    }
}
