package project.geoffrey.fr.appdownloaddata.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by galexandre on 08/02/15.
 */
public interface Unzip {
    public void unzipData() throws IOException;
    public void deleteZipFile();
    public void deleteAllFiles();
    public String getNameOftheUnzipFile();
    public Date getDateOfUnzipFile() throws ParseException;
}
