package rc.legostore.api;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    @PutMapping
    public void update(@RequestBody LegoSet legoSet){
        this.mongoTemplate.save(legoSet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        this.mongoTemplate.remove(new Query(Criteria.where("id").is(id)), LegoSet.class);
    }

    @GetMapping("/all")
    public Collection<LegoSet> all(){
        Collection<LegoSet> legosets = this.mongoTemplate.findAll(LegoSet.class);
        return legosets;
    }
}
