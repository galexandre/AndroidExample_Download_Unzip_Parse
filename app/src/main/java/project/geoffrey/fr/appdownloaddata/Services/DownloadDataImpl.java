package project.geoffrey.fr.appdownloaddata.services;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;

/**
 * Created by Pygmy on 08/02/15.
 */
public class DownloadDataImpl implements DownloadData {

    private Context myContext;
    private String url;
    private String fileLocation;

    /**
     *
     * @param ctx a Context
     * @param url an Url where is stored the data.
     */
    public DownloadDataImpl(Context ctx, String url){
        this.myContext=ctx;
        this.url=url;
        this.fileLocation="/DataDownload";
    }

    /**
     * Method which give a context to the class
     * @param myContext
     */
    public void setMyContext(Context myContext) {
        this.myContext = myContext;
    }

    /**
     * Download the data from the website
     */
    public void download(){
        DownloadManager.Request req = new DownloadManager.Request(Uri.parse(this.url));
        req.setTitle("Downloading Data...");
        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            req.allowScanningByMediaScanner();
            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        req.setDestinationInExternalPublicDir(fileLocation, "data.zip");
        DownloadManager manager = (DownloadManager) this.myContext.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(req);
    }

    /**
     * Return the location of the zip file
     * @return the fileLocation : String
     */
    public String getFileLocation() {
        return fileLocation;
    }


    /**
     * Give a location of the zip file
     * @param fileLocation : String
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

}
