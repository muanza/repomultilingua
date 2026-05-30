package ao.agt.crm.model;

import java.time.OffsetDateTime;

public class Interaction {

    private final String customerCode;
    private final String type;
    private final String notes;
    private final OffsetDateTime date;

    public Interaction(String customerCode, String type, String notes) {
        this.customerCode = customerCode;
        this.type = type;
        this.notes = notes;
        this.date = OffsetDateTime.now();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getType() {
        return type;
    }

    public String getNotes() {
        return notes;
    }

    public OffsetDateTime getDate() {
        return date;
    }
}
