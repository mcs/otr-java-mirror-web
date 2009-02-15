package otr.mirror.web.action.admin;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import otr.mirror.web.action.OTRMirrorActionBean;

/**
 *
 * @author Marcus Krassmann
 */
public class ContextActionBean extends OTRMirrorActionBean {

    private Map<String, Object> attributes;

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Resolution show() {
        attributes = new HashMap<String, Object>();
        @SuppressWarnings("unchecked")
        Enumeration<String> attributeNames = getContext().getServletContext().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            attributes.put(name, getContext().getServletContext().getAttribute(name));
        }
        return new ForwardResolution("/admin/context.jsp");
    }
}
