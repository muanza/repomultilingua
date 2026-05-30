package ao.agt.crm.model;

import ao.agt.common.util.CurrencyUtils;
import java.math.BigDecimal;

public class Opportunity {

    private final String customerCode;
    private final String title;
    private final BigDecimal amount;
    private String stage;

    public Opportunity(String customerCode, String title, BigDecimal amount) {
        this.customerCode = customerCode;
        this.title = title;
        this.amount = CurrencyUtils.normalize(amount);
        this.stage = "OPEN";
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
