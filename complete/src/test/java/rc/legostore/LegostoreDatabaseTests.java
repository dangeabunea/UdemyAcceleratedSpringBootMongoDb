package rc.legostore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import rc.legostore.model.*;
import rc.legostore.persistence.LegoSetRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class LegostoreDatabaseTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private LegoSetRepository legoSetRepository;

    @Before
    public void before(){
        this.legoSetRepository.deleteAll();

        LegoSet milleniumFalcon = new LegoSet(
                "Millennium Falcon",
                "Star Wars",
                LegoSetDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(1), 30, true),
                Arrays.asList(
                        new ProductReview("Dan", 7),
                        new ProductReview("Anna", 10),
                        new ProductReview("John", 8)
                ),
                new PaymentOptions(PaymentType.CreditCard, 0));

        LegoSet skyPolice = new LegoSet(
                "Sky Police Air Base",
                "City",
                LegoSetDifficulty.MEDIUM,
                new DeliveryInfo(LocalDate.now().plusDays(3), 50, true),
                Arrays.asList(
                        new ProductReview("Dan", 5),
                        new ProductReview("Andrew", 8)
                ),
                new PaymentOptions(PaymentType.CreditCard, 0));

        this.legoSetRepository.insert(milleniumFalcon);
        this.legoSetRepository.insert(skyPolice);
    }

    @Test
    public void findAllByGreatReviews_should_return_products_that_have_a_review_with_a_rating_of_ten() {
        List<LegoSet> results = (List<LegoSet>) this.legoSetRepository.findAllByGreatReviews();

        assertEquals(1, results.size());
        assertEquals("Millennium Falcon", results.get(0).getName());
    }

}
