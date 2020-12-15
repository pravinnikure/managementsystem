package com.management.managementsystem.Config;

import com.management.managementsystem.Filter.MyNewFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig
{
    public FilterRegistrationBean<MyNewFilter> registrationBean()
    {
        FilterRegistrationBean<MyNewFilter>  registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new MyNewFilter());

        //This filter is only applicable for employees
        registrationBean.addUrlPatterns("/employees/*");
        return registrationBean;
    }
}
