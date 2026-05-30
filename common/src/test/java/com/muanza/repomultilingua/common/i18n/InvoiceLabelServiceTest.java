package com.muanza.repomultilingua.common.i18n;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InvoiceLabelServiceTest {

    private final InvoiceLabelService service = new InvoiceLabelService();

    @Test
    void shouldReturnBilingualEnglishLabels() {
        assertEquals("Fatura / Invoice", service.invoiceLabels(Language.EN).get("Fatura"));
        assertEquals("Cliente / Customer", service.invoiceLabels(Language.EN).get("Cliente"));
    }
}
