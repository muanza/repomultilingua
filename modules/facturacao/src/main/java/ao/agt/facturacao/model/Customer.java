package ao.agt.facturacao.model;

import ao.agt.common.util.StringUtils;

public class Customer {

    private final String code;
    private final String name;

    public Customer(String code, String name) {
        this.code = StringUtils.requireNonBlank(code, "Customer code is required");
        this.name = StringUtils.requireNonBlank(name, "Customer name is required");
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
