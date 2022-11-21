package qub.ac.uk.service.impl;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qub.ac.uk.model.User;
import qub.ac.uk.service.UserService;
import qub.ac.uk.util.DBUtils;



@Service
public class UserServiceImpl implements UserService {
    MongoCollection conn = DBUtils.getCollection("user");
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public User findUserById(String _id) {
        FindIterable<Document> iterable = conn.find(new Document("_id", _id));
        MongoCursor<Document> cursor = iterable.iterator();
        if (cursor.hasNext()) {
            Document document = cursor.next();
            return JSON.parseObject(document.toJson(), User.class);
        } else {
            return null;
        }
    }
}


////
////    @Test
////    public void testMongo(){
////        boolean login = mongoTemplate.collectionExists("login");
////        assertEquals(login,true);
////    }


