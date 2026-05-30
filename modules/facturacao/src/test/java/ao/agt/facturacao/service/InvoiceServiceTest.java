package ao.agt.facturacao.service;

import ao.agt.facturacao.model.Customer;
import ao.agt.facturacao.model.Invoice;
import ao.agt.facturacao.model.InvoiceLine;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvoiceServiceTest {

    @Test
    void shouldCreateInvoiceAndRegisterPayment() {
        InvoiceService invoiceService = new InvoiceService();
        PaymentService paymentService = new PaymentService();

        Invoice invoice = invoiceService.createInvoice(
                "FT-1",
                new Customer("C001", "Cliente A"),
                List.of(
                        new InvoiceLine("Produto 1", 2, new BigDecimal("10")),
                        new InvoiceLine("Produto 2", 1, new BigDecimal("5"))
                )
        );

        assertEquals(new BigDecimal("25.00"), invoice.getTotal());
        assertFalse(invoice.isFullyPaid());

        invoiceService.registerPayment(invoice, paymentService.register(new BigDecimal("25"), "CASH"));
        assertTrue(invoice.isFullyPaid());
    }
}
