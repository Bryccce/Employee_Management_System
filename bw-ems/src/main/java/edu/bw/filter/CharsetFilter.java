package edu.bw.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

@WebFilter(filterName = "charsetFilter", urlPatterns = {"/user/*","/dept/*","/role/*","/permission/*"}
        ,initParams = @WebInitParam(name = "charset",value = "UTF-8"))
public class CharsetFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        FilterRegistration charsetFilter = request.getServletContext().getFilterRegistration("charsetFilter");
        String charset = charsetFilter.getInitParameter("charset");

        response.setContentType("application/json;charset=" + charset);
        chain.doFilter(request, response);//放行的时候,传递过去的response已设置好编码
    }
}
