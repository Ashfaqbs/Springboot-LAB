/**
 * Code developed by Ashfaq (© [Year])
 * GitHub: https://github.com/DarkSharkAsh
 */



package com.test.ashfaq.config;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<RequestLimitFilter> requestLimitFilter() {
        FilterRegistrationBean<RequestLimitFilter> registrationBean = new FilterRegistrationBean<>();
        RequestLimitFilter requestLimitFilter = new RequestLimitFilter();
        registrationBean.setFilter(requestLimitFilter);
        registrationBean.addUrlPatterns("/restapi/*"); // specify the URL patterns to apply the filter
        registrationBean.addUrlPatterns("/otherapi/");
        return registrationBean;
    }
}
