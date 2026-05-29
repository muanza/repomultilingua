package com.agt.common.util;

public final class TenantContext {
    private static final ThreadLocal<String> CURRENT = new ThreadLocal<>();

    private TenantContext() {
    }

    public static void setTenantCode(String tenantCode) {
        CURRENT.set(tenantCode);
    }

    public static String getTenantCode() {
        return CURRENT.get();
    }

    public static void clear() {
        CURRENT.remove();
    }
}
