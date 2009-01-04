package otr.mirror.web.stripesext;

import java.util.Random;
import javax.servlet.http.Cookie;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * ActionBeanContext subclass that manages where values
 * like the logged in user are stored.
 *
 * @author Tim Fennell
 */
public class OTRMirrorActionBeanContext extends ActionBeanContext {

    private static final Random rnd = new Random();

    public String getDownloadId() {
        String downloadId = null;
        Cookie[] cookies = getRequest().getCookies();
        if (cookies == null) {
            // create a new download id
            downloadId = createDownloadId();
        } else {
            for (Cookie each : cookies) {
                if (each.getName().equals("downloadId")) {
                    downloadId = each.getValue();
                }
            }

            // found download id? If not, create one
            if (downloadId == null) {
                downloadId = createDownloadId();
            }
        }

        return downloadId;
    }

    private String createDownloadId() {
        String downloadId = Integer.toHexString(rnd.nextInt()).toLowerCase();
        Cookie cookie = new Cookie("downloadId", downloadId);
        getResponse().addCookie(cookie);
        return downloadId;
    }
}
