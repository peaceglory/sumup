package com.sumup.rest.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class Error {
    private final List<String> messages;

    Error(final List<String> messages) {
        this.messages = messages != null ? Collections.unmodifiableList(messages) : Collections.emptyList();
    }

    Error(final String message) {
        final List<String> msgs = new ArrayList<>(1);
        msgs.add(message);
        this.messages = Collections.unmodifiableList(msgs);
    }

    public List<String> getMessages() {
        return messages;
    }
}
