package com.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class I18nConfig {
    @Bean
    public LocaleResolver localeResolver(){
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        List<Locale> locales = Arrays.asList(Locale.ENGLISH, new Locale("vi"));
        resolver.setSupportedLocales(locales);
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;

    }
}
