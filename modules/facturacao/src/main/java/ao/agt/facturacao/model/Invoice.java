package ao.agt.facturacao.model;

import ao.agt.common.model.Entity;
import ao.agt.common.util.CurrencyUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Invoice extends Entity {

    private final String number;
    private final Customer customer;
    private final List<InvoiceLine> lines = new ArrayList<>();
    private final List<Payment> payments = new ArrayList<>();
    private BigDecimal total = BigDecimal.ZERO;

    public Invoice(String number, Customer customer) {
        this.number = number;
        this.customer = customer;
    }

    public void addLine(InvoiceLine line) {
        lines.add(line);
        recalculateTotal();
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public BigDecimal getPaidTotal() {
        return payments.stream().map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isFullyPaid() {
        return getPaidTotal().compareTo(total) >= 0;
    }

    public void recalculateTotal() {
        total = CurrencyUtils.normalize(lines.stream().map(InvoiceLine::getLineTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public String getNumber() {
        return number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<InvoiceLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public List<Payment> getPayments() {
        return Collections.unmodifiableList(payments);
    }

    public BigDecimal getTotal() {
        return total;
    }
}
