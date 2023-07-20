package com.zhr.chatbot.api.domain_.AI;

import java.io.IOException;

/**
 * ChatGpt open ai 接口
 * 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
 */
public interface IOpenAI {
    String doChatGPT(String question) throws IOException;
}
