package com.muanza.repomultilingua.facturacao.api.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.muanza.repomultilingua.common.i18n.InvoiceLabelService;
import com.muanza.repomultilingua.facturacao.core.domain.Cliente;
import com.muanza.repomultilingua.facturacao.core.domain.Fatura;
import com.muanza.repomultilingua.facturacao.core.service.ClienteService;
import com.muanza.repomultilingua.facturacao.core.service.FaturaService;
import com.muanza.repomultilingua.facturacao.core.service.ImpostoService;
import com.muanza.repomultilingua.facturacao.core.service.ProdutoService;
import com.muanza.repomultilingua.facturacao.core.service.RelatorioService;

@Configuration
public class FacturacaoBeansConfig {

    @Bean
    InvoiceLabelService invoiceLabelService() {
        return new InvoiceLabelService();
    }

    @Bean
    FaturaService faturaService(InvoiceLabelService invoiceLabelService) {
        return new FaturaService(invoiceLabelService);
    }

    @Bean
    ClienteService clienteService() {
        return new ClienteService();
    }

    @Bean
    ProdutoService produtoService() {
        return new ProdutoService();
    }

    @Bean
    ImpostoService impostoService() {
        return new ImpostoService();
    }

    @Bean
    RelatorioService relatorioService() {
        return new RelatorioService();
    }

    @Bean
    Map<Long, Fatura> facturaStore() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    Map<Long, Cliente> clienteStore() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    Map<Long, String> backupStore() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    AtomicLong sequenceGenerator() {
        return new AtomicLong(1L);
    }
}

