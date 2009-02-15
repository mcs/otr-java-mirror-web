package otr.mirror.web.filter;

import java.io.*;
import javax.servlet.*;

public class CharsetFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");

        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Respect the client-specified character encoding
        // (see HTTP specification section 3.4.1)
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
