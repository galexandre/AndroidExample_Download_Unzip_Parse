package project.geoffrey.fr.appdownloaddata.Services;

import java.io.IOException;

/**
 * Created by Pygmy on 08/02/15.
 */
public interface Unzip {
    public void unzipData() throws IOException;
    public void deleteZipFile();
}
