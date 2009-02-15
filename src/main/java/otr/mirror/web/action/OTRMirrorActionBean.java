package otr.mirror.web.action;

import otr.mirror.web.stripesext.OTRMirrorActionBeanContext;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Simple ActionBean implementation that all ActionBeans
 * will extend.
 *
 * @author Marcus Krassmann
 */
public abstract class OTRMirrorActionBean implements ActionBean {

    private OTRMirrorActionBeanContext context;

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = (OTRMirrorActionBeanContext) context;
    }

    /** Gets the ActionBeanContext set by Stripes during initialization. */
    @Override
    public OTRMirrorActionBeanContext getContext() {
        return this.context;
    }
}
