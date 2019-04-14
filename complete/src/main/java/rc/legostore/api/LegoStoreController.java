package rc.legostore.api;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.web.bind.annotation.*;
import rc.legostore.model.AvgRatingModel;
import rc.legostore.model.LegoSet;
import rc.legostore.model.LegoSetDifficulty;
import rc.legostore.model.QLegoSet;
import rc.legostore.persistence.LegoSetRepository;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("legostore/api")
public class LegoStoreController {
    private LegoSetRepository legoSetRepository;

    public LegoStoreController(LegoSetRepository legoSetRepository) {
        this.legoSetRepository = legoSetRepository;
    }

    @PostMapping
    public void insert(@RequestBody LegoSet legoSet){
        this.legoSetRepository.insert(legoSet);
    }

    @PutMapping
    public void update(@RequestBody LegoSet legoSet){
        this.legoSetRepository.save(legoSet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        this.legoSetRepository.deleteById(id);
    }

    @GetMapping("/all")
    public Collection<LegoSet> all(){
        Sort sortByThemeAsc = Sort.by("theme").ascending();
        Collection<LegoSet> legosets = this.legoSetRepository.findAll(sortByThemeAsc);
        return legosets;
    }

    @GetMapping("/{id}")
    public LegoSet byId(@PathVariable String id){
        LegoSet legoSet = this.legoSetRepository.findById(id).orElse(null);
        return legoSet;
    }

    @GetMapping("/byTheme/{theme}")
    public Collection<LegoSet> byTheme(@PathVariable String theme){
        Sort sortByThemeAsc = Sort.by("theme").ascending();
        return this.legoSetRepository.findAllByThemeContains(theme, sortByThemeAsc);
    }

    @GetMapping("hardThatStartWithM")
    public Collection<LegoSet> hardThatStartWithM(){
        return this.legoSetRepository.findAllByDifficultyAndNameStartsWith(LegoSetDifficulty.HARD, "M");
    }

    @GetMapping("byDeliveryFeeLessThan/{price}")
    public Collection<LegoSet> byDeliveryFeeLessThan(@PathVariable int price){
        return this.legoSetRepository.findAllByDeliveryPriceLessThan(price);
    }

    @GetMapping("greatReviews")
    public Collection<LegoSet> byGreatReviews(){
        return this.legoSetRepository.findAllByGreatReviews();
    }

    @GetMapping("bestBuys")
    public Collection<LegoSet> bestBuys(){
        // build query
        QLegoSet query = new QLegoSet("query");
        BooleanExpression inStockFilter =  query.deliveryInfo.inStock.isTrue();
        Predicate smallDeliveryFeeFilter =  query.deliveryInfo.deliveryFee.lt(50);
        Predicate hasGreatReviews =  query.reviews.any().rating.eq(10);

        Predicate bestBuysFilter = inStockFilter
                .and(smallDeliveryFeeFilter)
                .and(hasGreatReviews);

        // pass the query to findAll()
        return (Collection<LegoSet>) this.legoSetRepository.findAll(bestBuysFilter);
    }

    @GetMapping("fullTextSearch/{text}")
    public Collection<LegoSet> fullTextSearch(@PathVariable String text){
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(text);
        return this.legoSetRepository.findAllBy(textCriteria);
    }

    @GetMapping("/byPaymentOption/{id}")
    public Collection<LegoSet> byPaymentOption(@PathVariable String id){
        return this.legoSetRepository.findByPaymentOptionId(id);
    }
}
