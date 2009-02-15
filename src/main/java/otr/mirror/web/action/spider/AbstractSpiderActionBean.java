package otr.mirror.web.action.spider;

import java.util.List;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import otr.mirror.core.model.Recording;
import otr.mirror.core.service.ShowRecordingsService;
import otr.mirror.web.action.OTRMirrorActionBean;

/**
 * A template class that helps creating a new file list for search providers.
 * Subclasses need to implement {@link #createSpiderFile(java.util.List) }
 * to provide a text file for the search provider's spider.
 * @author Marcus Krassmann
 */
public abstract class AbstractSpiderActionBean extends OTRMirrorActionBean {

    @SpringBean
    private ShowRecordingsService showRecordingsService;

    /**
     * The default handler for creating a text file used by search spiders.
     * @return the text file used by a concrete search spider
     */
    public final Resolution showRecordings() {
        if (!getContext().isDownloadEnabled()) {
            // send empty list to stop being listed at search providers
            return new StreamingResolution("text/plain", "");
        }
        List<Recording> recordings = showRecordingsService.getRecordings();
        return new StreamingResolution("text/plain", createSpiderFile(recordings));
    }

    /**
     * This method is intended for formatting the text file needed by the
     * OTRKEY search provider. The result has to be the correctly formatted
     * text file parsed by the search provider.
     * @param recordings all available recordings
     * @return text formatted for being used as text file for the search spider
     */
    protected abstract String createSpiderFile(List<Recording> recordings);
}
