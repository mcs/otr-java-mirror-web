package otr.mirror.web.stripesext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Exception handler for the OTR Mirror.
 * @author Marcus Krassmann
 */
public class OTRMirrorExceptionHandler extends DefaultExceptionHandler {

    private static final Log logger = LogFactory.getLog(OTRMirrorExceptionHandler.class);

    public Resolution handleGeneric(Exception exc, HttpServletRequest request, HttpServletResponse response) {
        logger.error("Caught exception! Requested URL was '" + request.getPathInfo() + "' with query string '" + request.getQueryString() + "'!", exc);
        // if in doubt, just forward to start site...
        return new RedirectResolution("/");
    }
}
