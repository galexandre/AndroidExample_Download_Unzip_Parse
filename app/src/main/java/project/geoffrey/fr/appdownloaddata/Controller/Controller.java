package project.geoffrey.fr.appdownloaddata.Controller;

import android.content.Context;

import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import project.geoffrey.fr.appdownloaddata.Services.DownloadData;
import project.geoffrey.fr.appdownloaddata.Services.DownloadDataImpl;
import project.geoffrey.fr.appdownloaddata.Services.Localization.Localization;
import project.geoffrey.fr.appdownloaddata.Services.Localization.LocalizationImpl;
import project.geoffrey.fr.appdownloaddata.Services.Parser;
import project.geoffrey.fr.appdownloaddata.Services.ParserImpl;
import project.geoffrey.fr.appdownloaddata.Services.Unzip;
import project.geoffrey.fr.appdownloaddata.Services.UnzipImpl;
import project.geoffrey.fr.appdownloaddata.model.Station;

/**
 * Created by Pygmy on 08/02/15.
 */
public class Controller {

    private Context myContext;
    private DownloadData dd;
    private Unzip uz;
    private Parser p;
    private Localization l;
    /**
     * Contructor of a controller
     * @param ctx a Context (android.content.Context)
     */
    public Controller(Context ctx){
        this.myContext=ctx;
        this.l = new LocalizationImpl(myContext);
    }

    /**
     * Donwload data from the url in parameter
     * @param url : String
     */
    public void DownloadData(String url){
        dd = new DownloadDataImpl(myContext,url);
        dd.download();
    }

    public void UnzipFile() throws IOException, ParseException {
        uz = new UnzipImpl("/Download","data.zip");
        uz.unzipData();
        Date k = uz.getDateOfUnzipFile();
    }

    public void deleteZipFile(){
        uz.deleteZipFile();
    }
    public void parseXmlFile() throws IOException, XmlPullParserException {
        p = new ParserImpl(uz.getNameOftheUnzipFile(),l);
        p.parse();
    }

    public List<Station> getAllStations(){
        return p.getPvd();
    }

    public List<Float> getDistance(){
        return p.getDistances();
    }
}
