package otr.mirror.web.action;

import otr.mirror.web.stripesext.OTRMirrorActionBeanContext;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Simple ActionBean implementation that all ActionBeans
 * will extend.
 *
 * @author Marcus Krassmann
 */
public abstract class OTRMirrorActionBean implements ActionBean {

    private OTRMirrorActionBeanContext context;

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = (OTRMirrorActionBeanContext) context;
    }

    /** Gets the ActionBeanContext set by Stripes during initialization. */
    @Override
    public OTRMirrorActionBeanContext getContext() {
        return this.context;
    }

    /**
     * Determines whether the bug provided is a new bug, or an existing bug. If it is an
     * existing bug, the bug is retrieved from the bug manager and changes are merged in.
     * If it is a new bug, fields that need defaults are filled in (such as creation date).
     * The newly formed bug is then returned - the bug passed in as an argument is
     * unaltered.
     *
     * @param bug a bug from which to create a fully populated bug
     * @return a fully populated bug
     */
/*    protected Bug populateBug(Bug bug) {
        BugManager bm = new BugManager();
        ComponentManager cm = new ComponentManager();
        PersonManager pm = new PersonManager();
        Bug newBug;

        if (bug.getId() == null) {
            newBug = new Bug();
            newBug.setOpenDate(new Date());
        }
        else {
            newBug = bm.getBug((bug.getId()));
        }

        // Populate the fields from the bug on the form
        newBug.setLongDescription( bug.getLongDescription() );
        newBug.setPriority( bug.getPriority());
        newBug.setShortDescription( bug.getShortDescription() );
        newBug.setDueDate( bug.getDueDate() );
        newBug.setPercentComplete( bug.getPercentComplete() );

        // If it's a new bug, status isn't mandatory, so default it
        if (bug.getStatus() == null) {
            newBug.setStatus(Status.New);
        }
        else {
            newBug.setStatus( bug.getStatus() );
        }

        // Link in the full component and person based on their IDs
        newBug.setComponent( cm.getComponent(bug.getComponent().getId()) );
        newBug.setOwner( pm.getPerson(bug.getOwner().getId()) );
        return newBug;
    } */
}
