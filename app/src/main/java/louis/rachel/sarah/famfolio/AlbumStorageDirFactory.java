package louis.rachel.sarah.famfolio;

import java.io.File;

//Album  class for version greater than froyo
//Created by Louis
abstract class AlbumStorageDirFactory {
    public abstract File getAlbumStorageDir(String albumName);
}
