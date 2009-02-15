package otr.mirror.web.action;

import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import otr.mirror.core.dao.RecordingDAO;
import otr.mirror.core.model.Recording;

/**
 *
 * @author Marcus Krassmann
 */
@UrlBinding("/action/file/{recording.filename}")
public class ShowFileActionBean extends OTRMirrorActionBean {

    private static final Log logger = LogFactory.getLog(ShowFileActionBean.class);
    @SpringBean
    private RecordingDAO recordingDAO;
    @ValidateNestedProperties({
        @Validate(field = "filename", required = true)
    })
    private Recording recording;
    private String key;

    public Recording getRecording() {
        return recording;
    }

    public void setRecording(Recording recording) {
        this.recording = recording;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Before
    public void populate() {
        recording = recordingDAO.findByFilename(recording.getFilename());
        if (recording != null) {
            key = String.valueOf(new Date().getTime() ^ recording.getFilename().hashCode());
        }
    }

    @DefaultHandler
    public Resolution showFile() {
        if (recording == null) {
            logger.warn("Requested file not found!");
            getContext().getMessages().add(new SimpleMessage("Bitte nur vorhandene Dateien anfragen!"));
            return new RedirectResolution("/");
        }
        return new ForwardResolution("/file.jsp");
    }
}
