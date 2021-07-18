package com.sumup.rest.exception;

import java.util.Collections;
import java.util.List;

final class Error {
    private final List<String> messages;

    Error(final List<String> messages) {
        this.messages = messages != null ? Collections.unmodifiableList(messages) : Collections.emptyList();
    }

    public List<String> getMessages() {
        return messages;
    }
}
