package project.geoffrey.fr.appdownloaddata.Services;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Pygmy on 08/02/15.
 */
public class UnzipImpl implements Unzip {

    private String locationZipFile;
    private String nameOfFile;
    private InputStream is;
    private ZipInputStream zi;
    private ZipEntry ze=null;
    private byte[] buff = new byte[1024];
    private int i=0;
    private String nameOftheUnzipFile="";

    public UnzipImpl(String dataLocation, String nameOfZipFile){
        this.locationZipFile=dataLocation;
        this.nameOfFile=Environment.getExternalStorageDirectory().toString()+locationZipFile+"/"+nameOfZipFile;
    }

    public void unzipData() throws IOException {
        Log.e("Zip","Starting unzip: "+nameOfFile);
        is=new FileInputStream(nameOfFile);
        zi = new ZipInputStream(is);


        ze=zi.getNextEntry();
        this.nameOftheUnzipFile=ze.getName();
        while(ze!=null){
            Log.e("Zip","Name of the file"+ze.getName());
            FileOutputStream foutput = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+this.locationZipFile+"/"+ze.getName());
            while ((i=zi.read(this.buff))!=-1){
                foutput.write(this.buff,0,i);
            }
            zi.closeEntry();
            foutput.close();
            Log.e("Zip",foutput.toString());
            ze=zi.getNextEntry();
        }
        zi.close();
    }

    public void deleteZipFile(){
        File dir = new File(this.nameOfFile);
        dir.delete();
        String path = Environment.getExternalStorageDirectory().toString()+this.locationZipFile;
        Log.e("Files","Path "+path);
        File f = new File(path);
        File files[] = f.listFiles();
        for(int k=0; k<files.length;k++){
            File e = files[k];
            Log.e("Files","File: "+e.getName() );
        }
    }

    public String getNameOftheUnzipFile() {
        return nameOftheUnzipFile;
    }


}
