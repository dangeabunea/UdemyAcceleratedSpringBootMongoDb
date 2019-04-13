package rc.legostore.persistence;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import rc.legostore.model.LegoSet;

@ChangeLog(order = "001")
public class DataMigrations {

    @ChangeSet(order = "001", author = "dan", id = "update nb parts")
    public void updateNbParts(MongoTemplate mongoTemplate) {
        Criteria priceZeroCriteria = new Criteria().orOperator(
                Criteria.where("nbParts").is(0),
                Criteria.where("nbParts").is(null));

        mongoTemplate.updateMulti(
                new Query(priceZeroCriteria),
                Update.update("nbParts", 122),
                LegoSet.class);

        System.out.println("Applied changeset 001");
    }

}
