package project.geoffrey.fr.appdownloaddata.Services;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by galexandre on 08/02/15.
 */
public interface Unzip {
    public void unzipData() throws IOException;
    public void deleteZipFile();
    public void deleteAllFiles();
    public String getNameOftheUnzipFile();
    public java.util.Date getDateOfUnzipFile() throws ParseException;
}
