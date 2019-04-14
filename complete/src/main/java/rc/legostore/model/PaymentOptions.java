package rc.legostore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PaymentOptions {
    @Id
    private String id;
    private PaymentType type;
    private int fee;

    public PaymentOptions(PaymentType type, int fee){
        this.type = type;
        this.fee = fee;
    }

    public PaymentType getType() {
        return type;
    }

    public int getFee() {
        return fee;
    }
}
