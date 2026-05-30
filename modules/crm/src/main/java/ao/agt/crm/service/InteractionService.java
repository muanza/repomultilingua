package ao.agt.crm.service;

import ao.agt.crm.model.Interaction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InteractionService {

    private final List<Interaction> interactions = new ArrayList<>();

    public Interaction register(String customerCode, String type, String notes) {
        Interaction interaction = new Interaction(customerCode, type, notes);
        interactions.add(interaction);
        return interaction;
    }

    public List<Interaction> listAll() {
        return Collections.unmodifiableList(interactions);
    }
}
