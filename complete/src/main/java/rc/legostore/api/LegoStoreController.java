package rc.legostore.api;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rc.legostore.model.LegoSet;

@RestController
@RequestMapping("legostore/api")
public class LegoStoreController {
    private MongoTemplate mongoTemplate;

    public LegoStoreController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @PostMapping
    public void insert(@RequestBody LegoSet legoSet){
        this.mongoTemplate.insert(legoSet);
    }
}
