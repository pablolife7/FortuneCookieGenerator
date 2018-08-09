package com.monsanto.interview.FortuneCookieGenerator;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
/**
 * Bug Fix
 * Wrong way -> "@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)"
 * The bean instance have to be per HTTP request
 */
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FortuneCookieBuilder {

    private static final String NOT_DEFINED = "N/A";

    private String client = NOT_DEFINED;
    private String company = NOT_DEFINED;
    private String quote = NOT_DEFINED;

    FortuneCookieBuilder withClient(String client) {
        if (this.client.equals(NOT_DEFINED))
            this.client = client;
        return this;
    }

    FortuneCookieBuilder withCompany(String company) {
        if (this.company.equals(NOT_DEFINED))
            this.company = company;
        return this;
    }

    FortuneCookieBuilder withQuote(String quote) {
        if (this.quote.equals(NOT_DEFINED))
            this.quote = quote;
        return this;
    }

    FortuneCookie build() {
        return new FortuneCookie("Hi "+client+"! Thanks for buying at "+company+" :) And remember: "+quote);
    }

}
