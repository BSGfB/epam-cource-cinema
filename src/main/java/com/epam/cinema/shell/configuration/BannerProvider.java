package com.epam.cinema.shell.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.stereotype.Component;


@Profile("spring-shell")
@Component
@Primary
@Order(Integer.MIN_VALUE)
public class BannerProvider extends DefaultBannerProvider {

    @Value("${shell.banner}")
    private String banner;

    @Value("${shell.welcomeMessage}")
    private String welcomeMessage;

    @Value("${shell.version}")
    private String version;

    @Value("${shell.providerName}")
    private String providerName;

    @Override
    public String getBanner() {
        return banner;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    @Override
    public String getProviderName() {
        return providerName;
    }
}
