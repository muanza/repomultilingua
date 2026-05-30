package ao.agt.facturacao.model;

import ao.agt.common.util.CurrencyUtils;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Payment {

    private final BigDecimal amount;
    private final String method;
    private final OffsetDateTime paidAt;

    public Payment(BigDecimal amount, String method) {
        this.amount = CurrencyUtils.normalize(amount);
        this.method = method;
        this.paidAt = OffsetDateTime.now();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public OffsetDateTime getPaidAt() {
        return paidAt;
    }
}
