package ao.agt.common.model;

import java.time.OffsetDateTime;

public class Audit {

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    public Audit(String user) {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.createdBy = user;
        this.updatedBy = user;
    }

    public void touch(String user) {
        this.updatedAt = OffsetDateTime.now();
        this.updatedBy = user;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }
}
