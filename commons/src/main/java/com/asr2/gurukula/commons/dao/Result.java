package com.asr2.gurukula.commons.dao;

import com.asr2.gurukula.commons.api.Message;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * Created by abhijith.nagarajan on 7/21/15.
 */
public class Result<T> {

    private boolean isTxSuccessful;

    private Collection<T> result;

    private List<Message> messages;

    public boolean isTxSuccessful() {
        return isTxSuccessful;
    }

    public void setIsTxSuccessful(boolean isTxSuccessful) {
        this.isTxSuccessful = isTxSuccessful;
    }

    public Collection<T> getResult() {
        return result;
    }

    public void setResult(Collection<T> result) {
        this.result = result;
    }

    public void addMessage(Message message) {
        if (null == messages)
            messages = Lists.newArrayList();
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
