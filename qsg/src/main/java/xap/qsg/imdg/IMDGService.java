package xap.qsg.imdg;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitAlreadyDeployedException;
import org.openspaces.admin.space.SpaceDeployment;

public class IMDGService {

	String spaceName = "xapTutorialSpace";

	public void startDataGrid() {
		try {
			// create an admin instance to interact with the cluster
			Admin admin = new AdminFactory().createAdmin();

			// locate a grid service manager and deploy a partioned data grid
			// with 2 primaries and one backup for each primary
			GridServiceManager mgr = admin.getGridServiceManagers()
					.waitForAtLeastOne();

			@SuppressWarnings("unused")
			ProcessingUnit pu = mgr.deploy(new SpaceDeployment(spaceName)
					.partitioned(2, 1));

		} catch (ProcessingUnitAlreadyDeployedException e) {
			// already deployed, do nothing
			e.printStackTrace();
		}
	}
}
