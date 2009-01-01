package otr.mirror.web.action;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.util.StringUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * A simplistic security filter that ensures that the user is logged in
 * before allowing access to any secured pages.
 *
 * @author Tim Fennell
 */
public class SecurityFilter implements Filter {
    private static Set<String> publicUrls = new HashSet<String>();

    static {
        publicUrls.add("/index.jsp");
        publicUrls.add("/login.jsp");
        publicUrls.add("/register.jsp");
        publicUrls.add("/exit.jsp");
        publicUrls.add("/Login.action");
        publicUrls.add("/Register.action");
        publicUrls.add("/ViewResource.action");
    }

    /** Does nothing. */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getSession().getAttribute("user") != null) {
            // User is logged in
            filterChain.doFilter(request, response);
        }
        else if ( isPublicResource(request) ) {
            // resource is public for everyone
            filterChain.doFilter(request, response);
        }
        else {
            // Redirect the user to the login page, noting where they were coming from
            String targetUrl = StringUtil.urlEncode(request.getServletPath());

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp?targetUrl=" + targetUrl);
        }
    }

    /**
     * Method that checks the request to see if it is for a publicly accessible resource
     */
    protected boolean isPublicResource(HttpServletRequest request) {
        String resource = request.getServletPath();

        return publicUrls.contains(request.getServletPath())
                || (!resource.endsWith(".jsp") && !resource.endsWith(".action"));
    }

    /** Does nothing. */
    @Override
    public void destroy() { }
}
