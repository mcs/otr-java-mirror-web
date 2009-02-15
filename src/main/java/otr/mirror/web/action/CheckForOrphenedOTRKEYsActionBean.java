package otr.mirror.web.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import otr.mirror.core.service.MaintenanceService;

/**
 * Checks for orphened OTRKEYs and handles them in an appropiate way.
 *
 * @author Marcus Krassmann
 */
public class CheckForOrphenedOTRKEYsActionBean extends OTRMirrorActionBean {

    @SpringBean
    private MaintenanceService maintenanceService;

    public Resolution check() {
        maintenanceService.checkForFileOrphans();
        maintenanceService.checkForDBOrphans();
        return new ForwardResolution(ShowRecordingsActionBean.class);
    }
}
