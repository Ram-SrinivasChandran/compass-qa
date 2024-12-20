package net.breezeware.ManageDrivers;

import java.io.IOException;

public class CompassShareContext {

    private static CompassShareContext compassShareContext;
    private CompassDriverManager compassDriverManager;
    private CompassPageObjectManager compassPageObjectManager;


    public CompassDriverManager manageRenterDriver() {
        if (compassDriverManager == null) {
            compassDriverManager = new CompassDriverManager();
        }

        return compassDriverManager;
    }

    public CompassPageObjectManager compassPageObjectManager() throws IOException {

        if (compassPageObjectManager == null) {
            compassPageObjectManager = new CompassPageObjectManager(manageRenterDriver().getAdminDriver());
        }

        return compassPageObjectManager;
    }

}
