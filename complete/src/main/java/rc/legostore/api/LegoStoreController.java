package rc.legostore.api;

import org.springframework.web.bind.annotation.*;
import rc.legostore.model.LegoSet;
import rc.legostore.model.LegoSetDifficulty;
import rc.legostore.persistence.LegoSetRepository;

import java.util.Collection;
import java.util.Optional;

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
        Collection<LegoSet> legosets = this.legoSetRepository.findAll();
        return legosets;
    }

    @GetMapping("/{id}")
    public LegoSet byId(@PathVariable String id){
        LegoSet legoSet = this.legoSetRepository.findById(id).orElse(null);
        return legoSet;
    }

    @GetMapping("/byTheme/{theme}")
    public Collection<LegoSet> byTheme(@PathVariable String theme){
        return this.legoSetRepository.findAllByThemeContains(theme);
    }

    @GetMapping("hardThatStartWithM")
    public Collection<LegoSet> hardThatStartWithM(){
        return this.legoSetRepository.findAllByDifficultyAndNameStartsWith(LegoSetDifficulty.HARD, "M");
    }

    @GetMapping("byDeliveryFeeLessThan/{price}")
    public Collection<LegoSet> byDeliveryFeeLessThan(@PathVariable int price){
        return this.legoSetRepository.findAllByDeliveryPriceLessThan(price);
    }
}
