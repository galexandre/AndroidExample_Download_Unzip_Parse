package project.geoffrey.fr.appdownloaddata;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import project.geoffrey.fr.appdownloaddata.Controller.Controller;


public class MainActivity extends ActionBarActivity {

    private Controller ctl;
    private String url = "http://donnees.roulez-eco.fr/opendata/jour";
    private ArrayAdapter arS;
    private ListView lv;
    //String[] countryArray = {"India", "Pakistan", "USA", "UK"};
    List<String> stations = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);


        //We call the controller
        ctl = new Controller(this.getApplicationContext());
        ctl.DownloadData(this.url);
        try {
            ctl.UnzipFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            ctl.parseXmlFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ctl.deleteZipFile();
        Log.e("ListVeiw","size: "+ctl.getAllStations().size());
       for (int i=0;i<ctl.getAllStations().size();i++){

           String adress=ctl.getAllStations().get(i).getAdress();
           String city=ctl.getAllStations().get(i).getCity();

           float distance = ctl.getDistance().get(i);
           String gasNamePrice = "Gazole "+ctl.getAllStations().get(i).getPrices().get("Gazole") +
                   " SP95 "+ctl.getAllStations().get(i).getPrices().get("SP95") +
                   " SP98 "+ctl.getAllStations().get(i).getPrices().get("SP98");


           if(distance >=1000){
               distance=distance/1000;
               stations.add(adress+" "+city + " "+ distance + " km " + gasNamePrice);
           }else {
               stations.add(adress+" "+city +" "+ distance + " m " + gasNamePrice);
           }


       }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_main,R.id.textView,stations );
        //Create Graphic User Interface
        lv.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
