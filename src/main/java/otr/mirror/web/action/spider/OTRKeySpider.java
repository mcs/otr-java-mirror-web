package otr.mirror.web.action.spider;

import java.util.List;
import net.sourceforge.stripes.action.UrlBinding;
import otr.mirror.core.model.Recording;

/**
 * Generates a list view especially for www.otr-search.com
 * 
 * @author Marcus Krassmann
 */
@UrlBinding("/action/otrkey.html")
public class OTRKeySpider extends AbstractSpiderActionBean {

    @Override
    protected String createSpiderFile(List<Recording> recordings) {
        StringBuilder result = new StringBuilder();
        for (Recording each : recordings) {
            result.append("<a href=\"http://otr-mirror.syn-online.de/action/file/").append(each.getFilename()).append("\">");
            result.append(each.getFilename()).append("</a>").append('\n');
        }
        return result.toString();
    }

}
