package project.geoffrey.fr.appdownloaddata.services;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import project.geoffrey.fr.appdownloaddata.model.Station;

/**
 * Created by galexandre on 10/02/15.
 */
public interface Parser {

    public void parse() throws IOException, XmlPullParserException;
    public List<Station> getPvd();
    public List<Float> getDistances();
}
