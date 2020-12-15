package com.management.managementsystem.Filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component

public class MyFilter_2 implements Filter
{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter 2 is called...");

        //For conitnuing
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
