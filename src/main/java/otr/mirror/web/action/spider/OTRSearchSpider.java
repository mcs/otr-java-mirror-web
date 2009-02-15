package otr.mirror.web.action.spider;

import java.util.List;
import net.sourceforge.stripes.action.UrlBinding;
import otr.mirror.core.model.Recording;
import otr.mirror.core.util.Util;

/**
 * Generates a list view especially for www.otr-search.com
 * 
 * @author Marcus Krassmann
 */
@UrlBinding("/action/otrsearch.txt")
public class OTRSearchSpider extends AbstractSpiderActionBean {

    @Override
    protected String createSpiderFile(List<Recording> recordings) {
        StringBuilder result = new StringBuilder();
        String sLoad = "";
        double[] load = Util.getLoadAverages();
        if (load.length == 3) {
            if (load[1] > 3 || load[2] > 3) {
                // too heavy load, don't send any downloads
                return "";
            }
            else if (load[1] > 2 || load[2] > 2) {
                // heavy load, switch to red
                sLoad = " 2";
            }
            else if (load[1] > 1 || load[2] > 1) {
                // some load, switch to yellow
                sLoad = " 1";
            }
            else {
                // all OK, switch to green
                sLoad = " 0";
            }
        }
        for (Recording each : recordings) {
            result.append(each.getFilename());
            result.append(sLoad);
            result.append(' ');
        }
        return result.toString();
    }
}
