package org.sigei.validacao;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageProvider {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public static String get(String key, String arg) {
        String template = bundle.getString(key);
        return MessageFormat.format(template, arg);
    }
}
