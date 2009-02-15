package otr.mirror.web.action.spider;

import java.util.List;
import net.sourceforge.stripes.action.UrlBinding;
import otr.mirror.core.model.Recording;

/**
 * Generates a list view especially for www.otr-search.com
 * 
 * @author Marcus Krassmann
 */
@UrlBinding("/action/otrkeyfinder.txt")
public class OTRKeyFinderSpider extends AbstractSpiderActionBean {

    @Override
    protected String createSpiderFile(List<Recording> recordings) {
        StringBuilder result = new StringBuilder();
        for (Recording each : recordings) {
            result.append(each.getFilename()).append('\n');
        }
        return result.toString();
    }
}
