package project.geoffrey.fr.appdownloaddata.model;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by galexandre on 07/12/15.
 */
public class StationTest {

    private Station station = new Station();

    @Test
    public void testSetAdress() throws Exception {
        station.setAdress("testAddress");
        assertTrue(StringUtils.equals(station.getAdress(),"testAddress"));
    }

    @Test
    public void testSetCity() throws Exception {
        station.setCity("aCity");
        assertTrue(StringUtils.equals("aCity",station.getCity()));
    }

    @Test
    public void testSetOpen() throws Exception {
        station.setOpen("7-19");
        assertTrue(StringUtils.equals("7-19",station.getOpen()));
    }

    @Test
    public void testSetServices() throws Exception {
        List<String> lstOfServices = new ArrayList<>();
        lstOfServices.add("a service");

        station.setServices(lstOfServices);
        assertTrue(station.getServices().size()==1);
        assertTrue(StringUtils.equals(station.getServices().get(0),"a service"));
    }

    @Test
    public void testSetPrice() throws Exception {

    }

    @Test
    public void testSetId() throws Exception {
        station.setId("0155");
        assertTrue(StringUtils.equals("0155",station.getId()));
    }

    @Test
    public void testSetLongitude() throws Exception {
        station.setLatitude((float) 1.4567);
        assertTrue(Float.compare(station.getLongitude(), (float) 1.4567)==1);
    }

    @Test
    public void testSetLatitude() throws Exception {
        station.setLatitude((float) 1.5456);
        assertTrue(Float.compare(station.getLatitude(), (float) 1.5456)==1);
    }

    @Test
    public void testSetZipcode() throws Exception {
        station.setZipcode("12345");
        assertTrue(StringUtils.equals("12345",station.getZipcode()));
    }

    @Test
    public void testSetPrices() throws Exception {

    }
}