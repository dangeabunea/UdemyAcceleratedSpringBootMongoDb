package rc.legostore.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rc.legostore.model.LegoSet;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String> {
}
