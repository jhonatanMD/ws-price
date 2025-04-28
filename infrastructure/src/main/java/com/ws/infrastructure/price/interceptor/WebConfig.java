package com.ws.infrastructure.price.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for registering interceptors in the Spring MVC context.
 *
 * <p>This class configures the {@link RequestResponseLoggingInterceptor} to log request parameters
 * and bodies for all incoming HTTP requests. The interceptor is applied to all paths
 * (i.e., {@code "/**"}) by using the {@link WebMvcConfigurer} interface.</p>
 *
 * @see WebMvcConfigurer
 * @see RequestResponseLoggingInterceptor
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RequestResponseLoggingInterceptor requestResponseLoggingInterceptor;


    /**
     * Constructor to initialize the {@link RequestResponseLoggingInterceptor}.
     *
     * @param requestResponseLoggingInterceptor the logging interceptor to be registered
     */
    public WebConfig(RequestResponseLoggingInterceptor requestResponseLoggingInterceptor) {
        this.requestResponseLoggingInterceptor = requestResponseLoggingInterceptor;
    }

    /**
     * Adds the {@link RequestResponseLoggingInterceptor} to the application's interceptor registry.
     * The interceptor is applied to all incoming HTTP requests.
     *
     * @param registry the interceptor registry to which the interceptor is added
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestResponseLoggingInterceptor)
                .addPathPatterns("/**");
    }
}