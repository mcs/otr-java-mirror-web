package otr.mirror.web.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * A minimal forwarding bean to the impressum.
 * I'm just loving UrlBindings :-)
 * 
 * @author Marcus Krassmann
 */
@UrlBinding("/action/impressum")
public class ImpressumActionBean extends OTRMirrorActionBean {

    @DefaultHandler
    @DontValidate
    public Resolution showImpressum() {
        return new ForwardResolution("/impressum.jsp");
    }
}
