package com.asr2.gurukula.commons.web;

import com.asr2.gurukula.commons.api.Message;

import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import java.util.Collection;

/**
 * Created by abhijith.nagarajan on 7/21/15.
 */
public class FacesUtils {

    public static void addFacesMessage(Message message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void addFacesMessage(Collection<Message> message) {
        message.forEach(m -> addFacesMessage(m));
    }
}
