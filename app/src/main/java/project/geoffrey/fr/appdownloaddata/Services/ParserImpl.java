package project.geoffrey.fr.appdownloaddata.Services;

import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.geoffrey.fr.appdownloaddata.model.Station;

/**
 * Created by galexandre on 10/02/15.
 */
public class ParserImpl implements Parser {

    private String myFileName="";

    public ParserImpl(String fileName){
        this.myFileName=fileName;
    }


    /**
     *
     * @throws IOException
     * @throws XmlPullParserException
     */
    public void parse() throws IOException, XmlPullParserException {
        Log.e("Parse", "Name of the file: " + Environment.getExternalStorageDirectory()+"/Download/"+this.myFileName);
        InputStream is = new FileInputStream(Environment.getExternalStorageDirectory()+"/Download/"+this.myFileName);
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        xpp.setInput(is,null);
        xpp.nextTag();
        readFeed(xpp);
    }

    /**
     *
     * @link : developer.android.com/training/basics/network-ops/xml.html
     * @param parser
     */
    public void readFeed(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<Station> pvd = new ArrayList<Station>();
        parser.require(XmlPullParser.START_TAG,null,"pdv_liste");
        while (parser.next()!=XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }

            String name=parser.getName();
            if(name.equals("pdv")){
                pvd.add(readStation(parser));
            }
        }

        for(int i=0; i<pvd.size();i++){
            Log.e("Parser","id: "+pvd.get(i).getId());
            Log.e("Parser","adress: "+pvd.get(i).getAdress());
            Log.e("Parser","city: "+pvd.get(i).getCity());
        }
    }

    public Station readStation(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG,null,"pdv");
        String id = parser.getAttributeValue(0);
        float latitude = Float.parseFloat(parser.getAttributeValue(1))/100000;//divide by 100 000
        float longitude= Float.parseFloat(parser.getAttributeValue(2))/100000;//divide by 100 000
        String adress="";
        String city="";
        Map<String,Float> carburants=new HashMap<String,Float>();
        List<String> services=new ArrayList();

        while(parser.next()!= XmlPullParser.END_TAG){
            if(parser.getEventType()!= XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("adresse")){
                adress=readAdress(parser);
            }else if (name.equals("ville")){
                city = readCity(parser);
            }else if (name.equals("prix")){
                carburants.put(parser.getAttributeValue(0), Float.valueOf(parser.getAttributeValue(3)));
            }
        }

        Station st = new Station(id,longitude,latitude,adress,city, carburants);
        return st;
    }

    public String readAdress(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "adresse");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "adresse");
        return title;
    }

    public String readCity(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "ville");
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "ville");
        return name;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    public String getMyFileName() {
        return myFileName;
    }

    public void setMyFileName(String myFileName) {
        this.myFileName = myFileName;
    }

}
