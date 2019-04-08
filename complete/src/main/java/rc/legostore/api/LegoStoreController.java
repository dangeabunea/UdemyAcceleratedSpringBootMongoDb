package rc.legostore.api;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import rc.legostore.model.LegoSet;

import java.util.Collection;

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

    @GetMapping("/all")
    public Collection<LegoSet> all(){
        Collection<LegoSet> legosets = this.mongoTemplate.findAll(LegoSet.class);
        return legosets;
    }
}
