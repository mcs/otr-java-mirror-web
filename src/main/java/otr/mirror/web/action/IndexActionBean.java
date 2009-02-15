package otr.mirror.web.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Marcus Krassmann
 */
public class IndexActionBean extends OTRMirrorActionBean {

    private static final Log logger = LogFactory.getLog(IndexActionBean.class);

    @Validate(required = false)
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @DefaultHandler
    @DontValidate
    public Resolution show() {
        logger.debug("Entering \"show\" event handler...");
        if (file != null && file.length() > 0) {
            logger.debug("Found file! Redirecting...");
            return new RedirectResolution(ShowFileActionBean.class, "showFile").addParameter("recording.filename", file);
        } else {
            logger.debug("Forward to standard /index.jsp");
            return new ForwardResolution("/index.jsp");
        }
    }
}
