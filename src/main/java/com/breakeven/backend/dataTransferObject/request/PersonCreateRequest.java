package com.breakeven.backend.dataTransferObject.request;

import java.util.UUID;

public class PersonCreateRequest {
    private UUID initiativeId;
    private String name;

    public UUID getInitiativeId() {
        return initiativeId;
    }

    public void setInitiativeId(UUID initiativeId) {
        this.initiativeId = initiativeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
