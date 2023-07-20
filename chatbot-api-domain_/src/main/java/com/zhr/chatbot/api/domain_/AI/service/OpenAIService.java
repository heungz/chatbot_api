package com.zhr.chatbot.api.domain_.AI.service;

import com.alibaba.fastjson.JSON;
import com.zhr.chatbot.api.domain_.AI.IOpenAI;
import com.zhr.chatbot.api.domain_.AI.model.aggregates.AIAnswer;
import com.zhr.chatbot.api.domain_.AI.model.vo.Choices;


import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OpenAIService implements IOpenAI {

    private Logger logger = LoggerFactory.getLogger(OpenAIService.class);

    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    @Override
    public String doChatGPT(String question) throws IOException {

        String pro = "127.0.0.1";//本机地址

        int pro1 = 7890; //代理端口号

        //创建一个 HttpHost 实例，这样就设置了代理服务器的主机和端口。
        HttpHost httpHost = new HttpHost(pro, pro1);
        //创建一个 RequestConfig 对象，然后使用 setProxy() 方法将代理 httpHost 设置进去。
        RequestConfig build = RequestConfig.custom().setProxy(httpHost).build();

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization","Bearer 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");
        //将 build 配置设置到 post 请求中包括先前指定的代理设置。
        post.setConfig(build);
        String paramJson = "{\"model\": \"gpt-3.5-turbo-0301\",\n" +
                "  \"messages\": [{\"role\": \"user\", \"content\": \""+question+"\"}],\n" +
                "  \"temperature\": 1,\n" +
                "  \"max_tokens\": 256,\n" +
                "  \"top_p\": 1,\n" +
                "  \"frequency_penalty\": 0,\n" +
                "  \"presence_penalty\": 0}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice:choices) {
                answers.append(choice);
                System.out.println("-------------------"+choice);
            }
            return answers.toString();
        } else {
            throw new RuntimeException("api.openai.com Err Code is " + response.getStatusLine().getStatusCode());
        }


    }
}
