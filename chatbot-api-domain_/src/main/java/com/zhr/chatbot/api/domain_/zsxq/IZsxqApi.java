package com.zhr.chatbot.api.domain_.zsxq;

import com.zhr.chatbot.api.domain_.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * 知识星球 API接口
 */
public interface IZsxqApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;
    boolean answer(String groupId,String cookie,String topicId,String text,boolean silenced) throws IOException;
}
