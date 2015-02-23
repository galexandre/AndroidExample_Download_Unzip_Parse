package project.geoffrey.fr.appdownloaddata.Services.Localization;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by galexandre on 21/02/15.
 */
public class LocalizationImpl implements Localization, LocationListener {
    /**
     * Le tag pour les log
     */
    private static final String TAG = "Localization";

    // Le contexte
    Context mContext;

    /**
     * La position
     */
    Location lastLocation;

    /**
     * La manager de location
     */
    LocationManager locationManager;

    /**
     * Le constructeur
     * @param context le context
     */
    public LocalizationImpl(Context context){
        this.mContext = context;
        locationManager = (LocationManager)this.mContext.getSystemService(mContext.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);/* new Location(LocationManager.GPS_PROVIDER);*/
        Log.e(TAG, "Localization initialized");
        Log.e(TAG,"my location: "+this.lastLocation.getLatitude() + " "+ this.lastLocation.getLongitude());
    }

    /**
     * Retourne la position actuelle
     * A enlever
     */
    public Location getCurrentPosition() {
        return this.lastLocation;
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            this.lastLocation = location;
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            //Log.d(TAG, "GPS request " + String.valueOf(lat) + "," + String.valueOf(lng));
        }
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {}
    public void onProviderEnabled(String provider) {}
    public void onProviderDisabled(String provider){}
}
