package project.geoffrey.fr.appdownloaddata.Controller;

import android.content.Context;

import java.io.IOException;

import project.geoffrey.fr.appdownloaddata.Services.DownloadData;
import project.geoffrey.fr.appdownloaddata.Services.DownloadDataImpl;
import project.geoffrey.fr.appdownloaddata.Services.Unzip;
import project.geoffrey.fr.appdownloaddata.Services.UnzipImpl;

/**
 * Created by Pygmy on 08/02/15.
 */
public class Controller {

    private Context myContext;
    private DownloadData dd;
    private Unzip uz;
    /**
     * Contructor of a controller
     * @param ctx a Context (android.content.Context)
     */
    public Controller(Context ctx){
        this.myContext=ctx;
    }

    /**
     * Donwload data from the url in parameter
     * @param url : String
     */
    public void DownloadData(String url){
        dd = new DownloadDataImpl(myContext,url);
        dd.download();
    }

    public void UnzipFile() throws IOException {
        uz = new UnzipImpl("/Download","data.zip");
        uz.unzipData();
    }

    public void deleteZipFile(){
        uz.deleteZipFile();
    }
}
