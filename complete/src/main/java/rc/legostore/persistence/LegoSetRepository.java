package rc.legostore.persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import rc.legostore.model.LegoSet;
import rc.legostore.model.LegoSetDifficulty;

import java.util.Collection;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String>, QuerydslPredicateExecutor<LegoSet> {
    Collection<LegoSet> findAllByThemeContains(String theme, Sort sort);
    Collection<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);
    Collection<LegoSet> findAllBy(TextCriteria textCriteria);

    @Query("{'delivery.deliveryFee' : {$lt : ?0}}")
    Collection<LegoSet> findAllByDeliveryPriceLessThan(int price);

    @Query("{'reviews.rating' : {$eq : 10}}")
    Collection<LegoSet> findAllByGreatReviews();

    @Query("{'paymentOptions.id' : ?0}")
    Collection<LegoSet> findByPaymentOptionId(String id);
}
