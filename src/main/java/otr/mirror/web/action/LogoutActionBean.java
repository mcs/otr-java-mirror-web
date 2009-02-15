package otr.mirror.web.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * Logs out the current user.
 * @author Marcus Krassmann
 */
public class LogoutActionBean extends OTRMirrorActionBean {

    @DefaultHandler
    public Resolution logout() {
        getContext().logout();
        return new RedirectResolution("/index.jsp").flash(this);
    }
}
