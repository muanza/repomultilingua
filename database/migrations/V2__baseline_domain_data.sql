INSERT INTO crm_customers (customer_code, name, email)
VALUES ('C001', 'Cliente Padrão', 'cliente@localhost')
ON CONFLICT (customer_code) DO NOTHING;

INSERT INTO products (sku, name, price)
VALUES ('SKU-001', 'Produto Exemplo', 10.00)
ON CONFLICT (sku) DO NOTHING;
