package ao.agt.facturacao.service;

import ao.agt.common.exception.BusinessException;
import ao.agt.facturacao.model.Customer;
import ao.agt.facturacao.model.Invoice;
import ao.agt.facturacao.model.InvoiceLine;
import ao.agt.facturacao.model.Payment;
import ao.agt.common.util.StringUtils;
import java.util.List;

public class InvoiceService {

    public Invoice createInvoice(String invoiceNumber, Customer customer, List<InvoiceLine> lines) {
        if (lines == null || lines.isEmpty()) {
            throw new BusinessException("Invoice requires at least one line");
        }
        Invoice invoice = new Invoice(StringUtils.requireNonBlank(invoiceNumber, "Invoice number is required"), customer);
        lines.forEach(invoice::addLine);
        return invoice;
    }

    public void registerPayment(Invoice invoice, Payment payment) {
        if (invoice.isFullyPaid()) {
            throw new BusinessException("Invoice already paid");
        }
        invoice.addPayment(payment);
    }
}
