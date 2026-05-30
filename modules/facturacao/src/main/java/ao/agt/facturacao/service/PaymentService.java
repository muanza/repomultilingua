package ao.agt.facturacao.service;

import ao.agt.common.exception.BusinessException;
import ao.agt.facturacao.model.Payment;
import java.math.BigDecimal;

public class PaymentService {

    public Payment register(BigDecimal amount, String method) {
        if (amount == null || amount.signum() <= 0) {
            throw new BusinessException("Payment amount must be positive");
        }
        return new Payment(amount, method);
    }
}
