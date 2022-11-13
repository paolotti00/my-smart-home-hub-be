package com.paolotti.my.smart.home.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig{
//        @Bean
//        public MongoTemplate mongoTemplate() throws Exception {
//            return new MongoTemplate(mongo(), "test");
//        }
//
////    @Override
////    protected String getDatabaseName() {
////        return "test"; //todo pt
////    }
////
////    @Override
////    public MongoClient mongoClient() { //todo pt
////        ConnectionString connectionString = new ConnectionString("mongodb+srv://mysmarthome_userdev:RkPThwoh0mUuqhbD@cluster0.gjqdgkx.mongodb.net/?retryWrites=true&w=majority");
////        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
////                .applyConnectionString(connectionString)
////                .build();
////
////        return MongoClients.create(mongoClientSettings);
////    }
}
