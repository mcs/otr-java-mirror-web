package otr.mirror.web.action;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * Straightforward logout action that logs the user out and then sends to an exit page.
 * @author Tim Fennell
 */
public class LogoutActionBean extends OTRMirrorActionBean {
    public Resolution logout() throws Exception {
        getContext().logout();
        return new RedirectResolution("/exit.jsp");
    }
}
