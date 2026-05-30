package com.muanza.repomultilingua.common.i18n;

public enum Language {
    PT("pt"),
    EN("en"),
    FR("fr"),
    ZH("zh");

    private final String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Language fromCode(String code) {
        for (Language language : values()) {
            if (language.code.equalsIgnoreCase(code)) {
                return language;
            }
        }
        return PT;
    }
}

