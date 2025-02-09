package be.devlooj.codaparser.statementsparsers;

import be.devlooj.codaparser.lines.MessageLine;
import java.util.List;

public class MessageParser {

    public String parse(List<MessageLine> lines) {
        StringBuilder messageString = new StringBuilder();

        for (MessageLine message : lines) {
            String trimmedContent = message.getContent().getValue().trim();
            if (!trimmedContent.isEmpty() && !messageString.isEmpty()) {
                messageString.append(" ");
            }
            messageString.append(trimmedContent);
        }

        return messageString.toString();
    }
}