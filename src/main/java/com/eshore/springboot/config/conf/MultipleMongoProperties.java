package com.eshore.springboot.config.conf;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Blaze on 2018/6/27.
 */
//@Data    、、lobok
@ConfigurationProperties(prefix = "mongodb")
public class MultipleMongoProperties {
    private MongoProperties primary = new MongoProperties();
    private MongoProperties secondary = new MongoProperties();
}
