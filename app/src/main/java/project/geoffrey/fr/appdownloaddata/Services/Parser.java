package project.geoffrey.fr.appdownloaddata.Services;

import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by galexandre on 10/02/15.
 */
public interface Parser {

    public void parse() throws IOException, XmlPullParserException;

}
