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

    private List<Station> pvd = new ArrayList<Station>();

    private String myFileName="";

    private float latitude;
    private float longitude;

    public ParserImpl(String fileName){
        this.myFileName=fileName;
    }

    private static final String ns = null;

    /**
     *
     * @throws IOException
     * @throws XmlPullParserException
     */
    public void parse() throws IOException, XmlPullParserException {
        InputStream is = new FileInputStream(Environment.getExternalStorageDirectory()+"/Download/"+this.myFileName);
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        xpp.setInput(is,null);
        xpp.nextTag();
        readFeed(xpp);
        is.close();
    }

    /**
     *
     * @link : developer.android.com/training/basics/network-ops/xml.html
     * @param parser
     */
    public void readFeed(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG,ns,"pdv_liste");
        while (parser.next()!=XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }

            String name=parser.getName();
            if(name.equals("pdv")){
                pvd.add(readStation(parser));
            }
        }
        Log.e("Parser","Taille de la liste:"+pvd.size());
        for(int i=0; i<pvd.size();i++){
            Log.e("Parser","id: "+pvd.get(i).getId());
            /*Log.e("Parser","adress: "+pvd.get(i).getAdress());
            Log.e("Parser","city: "+pvd.get(i).getCity());
            Log.e("Parser","longitude: "+pvd.get(i).getLongitude());
            Log.e("Parser","latitude: "+pvd.get(i).getLatitude());*/
            //Log.e("Parser","prix: "+pvd.get(i).getPrices().size());
            for(int e=0; e<pvd.get(i).getPrices().size();e++){
                //Log.e("Parser","See price in object: "+pvd.get(i).getPrices().get(pvd.get(i).get));
            }
            Log.e("Parser","Number of services: "+pvd.get(i).getServices().size());
        }
    }

    public Station readStation(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG,ns,"pdv");
        String id = parser.getAttributeValue(0);
        if(parser.getAttributeValue(1).equals("")){
            this.latitude=0;
        }else{
            this.latitude=Float.parseFloat(parser.getAttributeValue(1))/100000;//divide by 100 000
        }

        if (parser.getAttributeValue(2).equals("")){
            longitude=0;
        }else{
            this.longitude= Float.parseFloat(parser.getAttributeValue(2))/100000;//divide by 100 000
        }
        String cp = parser.getAttributeValue(3);
        String adress="";
        String city="";
        int i =0;
        Map<String,Float> carburants=new HashMap<String,Float>();
        List<String> services=new ArrayList();
        while(parser.next()!= XmlPullParser.END_TAG){
            if(parser.getEventType()!= XmlPullParser.START_TAG){
                continue;
            }

            String name = parser.getName();
            Log.e("Parse","Name: "+name);
            if(name.equals("adresse")){
                adress=readAdress(parser);
            }else if (name.equals("ville")){
                city = readCity(parser);
            }else if (name.equals("prix")) {
                if (parser.getAttributeCount()!=0){
                    String nameOfGas= parser.getAttributeValue(0);
                    float priceOfGas = Float.valueOf(parser.getAttributeValue(3))/1000;
                    carburants.put(nameOfGas, priceOfGas);//because format is "1234"
                }else{
                    carburants.put("null", (float) 0);
                }
                parser.next();
            }else if (name.equals("services")){
                services=readServices(parser);
                //parser.nextTag();
                parser.next();
            }else{
                skip(parser);
            }
        }

        Station st = new Station(id,longitude,latitude,city,adress, carburants,cp,services);
        //services.clear();
        return st;
    }



    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    private List<String> readServices(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<String> myList = new ArrayList<String>();
       // while(parser.getName().equals("service")){
            parser.require(XmlPullParser.START_TAG, ns, "service");
            String res = readText(parser);
            myList.add(res);
            parser.require(XmlPullParser.END_TAG, ns, "service");
            parser.next();
        //}
        return (List<String>) myList.clone();
    }

    private String readAdress(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "adresse");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "adresse");
        return title;
    }

    private String readCity(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "ville");
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "ville");
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

    public List<Station> getPvd() {
        return pvd;
    }
}
