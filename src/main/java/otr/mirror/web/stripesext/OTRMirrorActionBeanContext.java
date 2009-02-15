package otr.mirror.web.stripesext;

import java.util.HashMap;
import java.util.Map;
import net.sourceforge.stripes.action.ActionBeanContext;
import otr.mirror.core.model.User;
import otr.mirror.web.action.DownloadActionBean.Download;

/**
 * ActionBeanContext subclass that manages where values
 * like the logged in user are stored.
 *
 * @author Tim Fennell
 * @author Marcus Krassmann
 */
public class OTRMirrorActionBeanContext extends ActionBeanContext {

    /**
     * Gets the user who is currently logged in.
     * @return the user
     */
    public User getUser() {
        return (User) getRequest().getSession().getAttribute("user");
    }

    /**
     * Logs in a user.
     * @param user the user
     */
    public void setUser(User user) {
        getRequest().getSession().setAttribute("user", user);
    }

    @SuppressWarnings("unchecked")
    public Map<Download, Integer> getCurrentKeys() {
        Map<Download, Integer> keys = (Map<Download, Integer>) getServletContext().getAttribute("otr_keys");
        if (keys == null) {
            keys = new HashMap<Download, Integer>();
        }
        return keys;
    }

    /**
     * Adds a specific download to the session. Returns how many download-tries
     * where attemped until now (including this attempt). Smallest return value
     * is 1.
     * @param filename the filename
     * @param key the key
     * @return the current download attempt, starting with 1
     */
    public int addCurrentDownload(String filename, String key) {
        Map<Download, Integer> keys = getCurrentKeys();
        Download dl = new Download(filename, key);
        Integer num = keys.get(dl);
        if (num == null) {
            num = 0;
        }
        keys.put(dl, ++num);
        getServletContext().setAttribute("otr_keys", keys);
        return num;
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        getRequest().getSession().invalidate();
    }

    public void setDownloadEnabled(boolean enabled) {
        getServletContext().setAttribute("otr_enabled", enabled);
    }

    public boolean isDownloadEnabled() {
        Boolean enabled = (Boolean) getServletContext().getAttribute("otr_enabled");
        return enabled == null ? true : enabled;
    }
}
