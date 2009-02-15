package otr.mirror.web.action.admin;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import otr.mirror.web.action.OTRMirrorActionBean;

/**
 *
 * @author Marcus Krassmann
 */
public class SettingsActionBean extends OTRMirrorActionBean {

    private static final Log logger = LogFactory.getLog(SettingsActionBean.class);
    private Boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @DefaultHandler
    public Resolution show() {
        enabled = getContext().isDownloadEnabled();
        return new ForwardResolution("/admin/settings.jsp");
    }

    public Resolution save() {
        getContext().setDownloadEnabled(enabled);
        logger.debug(getContext().getServletContext().getAttribute("otr_enabled"));
        getContext().getMessages().add(new SimpleMessage("Success!"));
        return new ForwardResolution("/admin/settings.jsp");
    }
}
