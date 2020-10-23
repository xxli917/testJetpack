package com.example.testjetpack.test;

public enum   SingleEnum{
    //public final static
    SINGLE_ENUM("1");
    private String type;
    private SingleEnum(String type){
       this.type = type;
    }
}

enum LanguageEnum {
    INSTANCE;

    private static class Language {
        public String realName(String name) {
            return "Language " + name;
        }
    }

    private Language instance = null;
    private LanguageEnum() {
        instance = new Language();
    }

    // 实际对外开放的两个方法
    public Language getLanguage() {
        return instance;
    }

    public void printlnLanguageName(String name) {
        String str = getLanguage().realName(name);
        System.out.println(str);
    }
}