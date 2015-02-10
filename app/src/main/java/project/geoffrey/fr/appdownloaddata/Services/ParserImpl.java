package project.geoffrey.fr.appdownloaddata.Services;

/**
 * Created by galexandre on 10/02/15.
 */
public class ParserImpl implements Parser {

    private String myFileName;

    public ParserImpl(String fileName){
        this.myFileName=fileName;
    }


    public void parse() {
        
    }


    public String getMyFileName() {
        return myFileName;
    }

    public void setMyFileName(String myFileName) {
        this.myFileName = myFileName;
    }

}
