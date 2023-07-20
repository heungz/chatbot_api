package com.zhr.chatbot.api.test;

import com.alibaba.fastjson.JSON;
import com.zhr.chatbot.api.domain_.AI.IOpenAI;
import com.zhr.chatbot.api.domain_.zsxq.IZsxqApi;
import com.zhr.chatbot.api.domain_.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.zhr.chatbot.api.domain_.zsxq.model.vo.Question;
import com.zhr.chatbot.api.domain_.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {
    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);
    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Resource
    private IZsxqApi iZsxqApi;
    @Resource
    private IOpenAI iOpenAI;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = iZsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
//        for (Topics topic:topics) {
//            String topic_id = topic.getTopic_id();
//            Question question = topic.getQuestion();
//            logger.info("topicId:{},text:{}",topic_id,question);
//        }
    }

    @Test
    public void test_openAi() throws IOException {
        String response = iOpenAI.doChatGPT("鲈鱼是什么?");
        logger.info("测试结果：{}",response);
    }
}
