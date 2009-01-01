package otr.mirror.web.action;

import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * ActionBeanContext subclass that manages where values
 * like the logged in user are stored.
 *
 * @author Tim Fennell
 */
public class OTRMirrorActionBeanContext extends ActionBeanContext {

    /** Logs the user out by invalidating the session. */
    public void logout() {
        getRequest().getSession().invalidate();
    }
}
