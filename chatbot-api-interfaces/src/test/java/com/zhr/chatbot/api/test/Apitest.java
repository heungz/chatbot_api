package com.zhr.chatbot.api.test;

import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.IOException;

public class Apitest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");
        httpGet.addHeader("cookie", "zsxq_access_token=95F8411F-3C0F-8216-936D-B56BC9E3C885_20B11388868A2906; zsxqsessionid=4ef1e8206bae5f939a3f1001e781e3ef; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22812542524481522%22%2C%22first_id%22%3A%22186f914add7907-09e26e8c0b84ab8-26031851-2073600-186f914add8474%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2ZjkxNGFkZDc5MDctMDllMjZlOGMwYjg0YWI4LTI2MDMxODUxLTIwNzM2MDAtMTg2ZjkxNGFkZDg0NzQiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI4MTI1NDI1MjQ0ODE1MjIifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22812542524481522%22%7D%2C%22%24device_id%22%3A%22186f914add7907-09e26e8c0b84ab8-26031851-2073600-186f914add8474%22%7D");
        httpGet.addHeader("Content-Type", "application/json; charset=UTF-8");
        CloseableHttpResponse response = httpClient.execute(httpGet);
//        判断请求的信息是否是正常的信息
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost();
    }
    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization","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");
        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity((HttpEntity) stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

}
