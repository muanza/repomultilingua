package ao.agt.crm.service;

import ao.agt.crm.model.Customer;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerService {

    private final Map<String, Customer> customers = new ConcurrentHashMap<>();

    public Customer create(String code, String name, String email) {
        Customer customer = new Customer(code, name, email);
        customers.put(code, customer);
        return customer;
    }

    public Optional<Customer> findByCode(String code) {
        return Optional.ofNullable(customers.get(code));
    }
}
