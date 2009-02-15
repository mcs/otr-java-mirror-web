package otr.mirror.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import otr.mirror.core.service.ShowRecordingsService;

/**
 *
 * @author Marcus Krassmann
 */
@UrlBinding("/action/download/{key}/{file}")
public class DownloadActionBean extends OTRMirrorActionBean {

    public static class Download {

        private String filename;
        private String key;

        public Download(String filename, String key) {
            this.filename = filename;
            this.key = key;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Download other = (Download) obj;
            if ((this.key == null) ? (other.key != null) : !this.key.equals(other.key)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 31 * hash + (this.key != null ? this.key.hashCode() : 0);
            return hash;
        }

        @Override
        public String toString() {
            return "File: '" + filename + "', Key: '" + key + "'";
        }


    }
    private static final Log logger = LogFactory.getLog(DownloadActionBean.class);
    @SpringBean
    private ShowRecordingsService showRecordingsService;
    private String file;
    @Validate(encrypted = true)
    private String key;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @ValidationMethod(on = "getRecording")
    public void checkKey(ValidationErrors errors) {
        SimpleError wrongKeyError = new SimpleError("Download not allowed! Use our website to download the file.");
        try {
            long time = file.hashCode() ^ Long.parseLong(key);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(time);
            Date now = new Date();
            if (now.before(cal.getTime())) {
                // link date is in future => hack attempt...
                errors.addGlobalError(wrongKeyError);
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
            if (now.after(cal.getTime())) {
                // 24 hours passed, link became invalid
                errors.addGlobalError(wrongKeyError);
            }
        } catch (Exception e) {
            // someone played around with the link => hack attempt...
            errors.addGlobalError(wrongKeyError);
        }
    }

    @DefaultHandler
    public Resolution getRecording() {
        if (!getContext().isDownloadEnabled()) {
            return new ErrorResolution(403, "Download zurzeit nicht erlaubt!");
        }
        // log request headers
        Enumeration headerNames = getContext().getRequest().getHeaderNames();
        StringBuilder sb = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String hName = (String) headerNames.nextElement();
            sb.append('[').append(hName).append(" => ");
            sb.append(getContext().getRequest().getHeader(hName)).append(']');
        }
        logger.debug("Headers:\n" + sb.toString());
        
        // count download if first hit
        //TODO shouldn't be in session, but in external database or global scope
        int dlAttempts = getContext().addCurrentDownload(file, key);
        if (dlAttempts > 4) {
            logger.debug("Too many connection attempts! Tries = " + dlAttempts);
            return new ErrorResolution(403, "Too many connection attempts!");
        }
        logger.debug("Key usage OK, download should start. Attempts = " + dlAttempts);
        final File recFile = showRecordingsService.getRecording(
                file,
                dlAttempts == 1,
                getContext().getRequest().getRemoteAddr());
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(recFile);
            StreamingResolution sr = new StreamingResolution("application/x-otrkey", fis) {

                @Override
                protected void stream(HttpServletResponse response) throws Exception {
                    response.setHeader("Content-Length", String.valueOf(recFile.length()));
                    response.setHeader("Accept-Ranges", "none");
                    super.stream(response);
                }
            };
            sr.setFilename(recFile.getName());
            return sr;
        } catch (FileNotFoundException ex) {
            logger.error("Did not find " + recFile.getName() + "!", ex);
        } catch (Exception ex) {
            logger.error("Download-Exception bei Datei " + recFile.getName() + "!", ex);
        }
        return new ErrorResolution(403, "Unknown temporal error while requesting the file.");
    }
}
