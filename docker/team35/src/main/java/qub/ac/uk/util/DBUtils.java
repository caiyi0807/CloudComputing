package qub.ac.uk.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DBUtils {
    public static MongoCollection getCollection (String s){//根据传入的集合名称或许集合
        MongoClient mongoClient = new MongoClient("192.168.0.16",27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("webapp");
        log.info("获取数据库成功："+mongoDatabase);
        MongoCollection<Document> conn = mongoDatabase.getCollection(s);
        log.info("获取集合成功："+mongoDatabase);
        return conn;
    }
}




