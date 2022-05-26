package fr.radi3nt.updater;

import fr.radi3nt.updater.api.UpdateManager;

public class UpdateInstaller {

    private final UpdateManager updateManager;

    private boolean wasUpdated = false;

    public UpdateInstaller(UpdateManager updateManager) {
        this.updateManager = updateManager;
    }

    public void install(boolean check) {
        boolean needUpdate = true;

        updateManager.getChecker().fetch();

        if (check) {
            needUpdate = !updateManager.getChecker().isUpToDate();
        }

         if (needUpdate) {
             update();
         } else {
             System.out.println("No updates available");
        }
    }

    private void update() {
        System.out.println("Updating application...");
        updateManager.createUpdater().update();
        wasUpdated = true;
    }

    public boolean wasUpdated() {
        return wasUpdated;
    }
}
