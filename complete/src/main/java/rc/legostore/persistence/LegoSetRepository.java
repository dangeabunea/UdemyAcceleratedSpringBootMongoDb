package rc.legostore.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rc.legostore.model.LegoSet;

import java.util.Collection;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String> {
    Collection<LegoSet> findAllByThemeContains(String theme);
}
