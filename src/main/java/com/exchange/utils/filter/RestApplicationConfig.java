package com.exchange.utils.filter;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApplicationConfig extends ResourceConfig {

    public RestApplicationConfig() {
        packages( "com.exchange.utils.filter" );
        register( AuthenticationFilter.class );
    }
}