package otr.mirror.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import otr.mirror.core.service.ShowRecordingsService;
import otr.mirror.core.model.Recording;

/**
 * Shows all stored OTRKEY files.
 * 
 * @author Marcus Krassmann
 */
@UrlBinding("/action/recordings")
public class ShowRecordingsActionBean extends OTRMirrorActionBean {

    @SpringBean
    private ShowRecordingsService showRecordingsService;
    private List<Recording> recordings;
    private List<String> keys;

    public List<Recording> getRecordings() {
        return recordings;
    }

    public void setRecordings(List<Recording> recordings) {
        this.recordings = recordings;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    

    @Before
    public void setProperties() {
        recordings = showRecordingsService.getRecordings();
        keys = new ArrayList<String>(recordings.size());
        for (Recording each : recordings) {
            keys.add(String.valueOf(new Date().getTime() ^ each.getFilename().hashCode()));
        }
    }
    
    @DefaultHandler
    public Resolution showAll() {
        return new ForwardResolution("/recordings.jsp");
    }
}
