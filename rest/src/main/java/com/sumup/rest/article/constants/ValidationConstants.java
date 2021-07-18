package com.sumup.rest.article.constants;

public final class ValidationConstants {
    public static final String TITLE_BLANK_MSG = "'title' cannot be empty!";
    public static final String GROUP_BLANK_MSG = "'group' cannot be empty!";
    public static final String TEXT_BLANK_MSG = "'text' cannot be empty!";

    public static final int TITLE_MIN = 3;
    public static final int TITLE_MAX = 17;
    public static final int GROUP_MIN = 5;
    public static final int GROUP_MAX = 10;
    public static final int TEXT_MIN = 1;
    public static final int TEXT_MAX = 1000;

    public static final String TITLE_LENGTH_MSG = "'title' should be between " + TITLE_MIN + " and " + TITLE_MAX + " in length!";
    public static final String GROUP_LENGTH_MSG = "'group' should be between " + GROUP_MIN + " and " + GROUP_MAX + " in length!";
    public static final String TEXT_LENGTH_MSG = "'text' should be between " + TEXT_MIN + " and " + TEXT_MAX + " in length!";

    private ValidationConstants() {
        throw new IllegalStateException();
    }
}
