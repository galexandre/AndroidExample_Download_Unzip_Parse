package project.geoffrey.fr.appdownloaddata.services.Localization;

import android.location.Location;

/**
 * Created by galexandre on 21/02/15.
 */
public interface Localization {
    /**
     * Return the current location
     * @return Location : the current location
     */
    Location getCurrentPosition();
}
