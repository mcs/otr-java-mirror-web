package otr.mirror.web.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import otr.mirror.core.model.User;
import otr.mirror.core.service.LoginService;

/**
 * ActionBean for loggin in a user.
 * 
 * @author Marcus Krassmann
 */
public class LoginActionBean extends OTRMirrorActionBean {

    private static final Log logger = LogFactory.getLog(LoginActionBean.class);
    @SpringBean
    private LoginService loginService;
    @ValidateNestedProperties({
        @Validate(field = "login", required = true),
        @Validate(field = "password", required = true)
    })
    private User user;
    private String targetUrl;

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @DontValidate
    @DefaultHandler
    public Resolution show() {
        return new ForwardResolution("/login.jsp");
    }

    /**
     * Try to login the user.
     */
    public Resolution login() {
        logger.debug("Raw user: " + user);
        user = loginService.login(user.getLogin(), user.getPassword());
        if (user != null) {
            getContext().setUser(user);
            if (targetUrl == null) {
                targetUrl = "/";
            }
            return new RedirectResolution(targetUrl).flash(this);
        } else {
            getContext().getValidationErrors().addGlobalError(new SimpleError("Logindaten ungültig!"));
            return getContext().getSourcePageResolution();
        }

    }
}
