package com.txr.spbbasic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * spring boot（版本1.5.1.RELEASE）中 @ConfigurationProperties注解已将location属性移除
 *
 * 因为 @ConfigurationProperties注解已将location属性移除, 所以需要加@Component注册
 * 使用@PropertySource来指定自定义的资源目录（默认springboot 配置文件）
 *
 * 可以修饰方法
 */
@Component
@ConfigurationProperties(prefix = "author")
@PropertySource(value = "classpath:/author.properties",encoding = "UTF-8") //可选
public class AuthorMapping {

    private String name;

    private List<String> friends;

    private Map<String, String> keys;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public Map<String, String> getKeys() {
        return keys;
    }

    public void setKeys(Map<String, String> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "AuthorMapping{" +
                "name='" + name + '\'' +
                ", friends=" + friends +
                ", keys=" + keys +
                '}';
    }
}
