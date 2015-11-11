package louis.rachel.sarah.famfolio;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which creates a global variable with methods to get the variable and change it
 * Created by Sarah on 11/1/2015.
 */
public class Globals extends Application{
    private File storageAlbum;
    public File getStor() {return this.storageAlbum;}
    public void addFile(File x){
        storageAlbum = x;
    }
    public Globals(){}
}

