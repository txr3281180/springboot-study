package com.txr.spbbasic.repository.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sky.zhang on 2018/10/26.
 */
public class RedisMessage<Content> {
    private String topic;

    private Map<String, Content> contentMap = new HashMap<>();

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Map<String, Content> getContent() {
        return contentMap;
    }

/*    public RedisMessage setContentMap(Map<String, Content> content) {
        this.contentMap = content;
        return this;
    }*/

    public RedisMessage addContent(String user, Content content) {
        this.contentMap.put(user, content);
        return this;
    }
}
