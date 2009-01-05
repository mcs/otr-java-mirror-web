package otr.mirror.web.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import otr.mirror.core.service.ShowRecordingsService;

/**
 *
 * @author Marcus Krassmann
 */
@UrlBinding("/action/download/{key}/{file}")
public class DownloadActionBean extends OTRMirrorActionBean {

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
        SimpleError smplErr = new SimpleError("Download not allowed! Use our website to download the file.");
        try {
            long time = file.hashCode() ^ Long.parseLong(key);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(time);
            Date now = new Date();
            if (now.before(cal.getTime())) {
                // link date is in future => hack attempt...
                errors.addGlobalError(smplErr);
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
            if (now.after(cal.getTime())) {
                // 24 hours passed, link became invalid
                errors.addGlobalError(smplErr);
            }
        } catch (Exception e) {
            // someone played around with the link => hack attempt...
            errors.addGlobalError(smplErr);
        }
    }

    @DefaultHandler
    public Resolution getRecording() throws FileNotFoundException {
        return new StreamingResolution(
                "application/octet-stream",
                new FileInputStream(showRecordingsService.getRecording(file))).setFilename(file);
    }
}
