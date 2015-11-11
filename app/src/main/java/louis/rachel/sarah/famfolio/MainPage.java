package louis.rachel.sarah.famfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//First screen allows users start either the camera, prompts, or send
//Created by Rachel
public class MainPage extends AppCompatActivity {
    private String mCurrentPhotoPath;
    private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
    private File mGalleryFolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        createImageGallery();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Intends to go to prompts, camera, and gallery
    public void goPrompt(View view){
        Intent intent = new Intent(this, PromptOptions.class);
        startActivity(intent);
    }
    public void goPhotoIntent(View view){
        Intent intent = new Intent(this, PhotoIntentActivity.class);
        startActivity(intent);
    }
    public void goSendIntent(View view){
        Intent intent = new Intent(this, SendFolio.class);
        startActivity(intent);
    }

    //set up camera and saving images to gallery
    public void photoTime(View view){
        File f = null;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
        } else {
            mAlbumStorageDirFactory = new BaseAlbumDirFactory();
        }
        try {
            f = createImageFile();
            mCurrentPhotoPath = f.getAbsolutePath();
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        } catch (IOException e) {
            e.printStackTrace();
            f = null;
            mCurrentPhotoPath = null;
        }
        startActivity(takePictureIntent);
        Intent mediaScanIntent = new Intent( Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.fromFile(f));
        sendBroadcast(mediaScanIntent);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "FamFolio" + timeStamp + "_";
        //File albumF = getAlbumDir();
        File imageF = File.createTempFile(imageFileName, ".jpg", mGalleryFolder);
        Globals g = (Globals)getApplication();
        return imageF;
    }

    private void createImageGallery() {
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        mGalleryFolder = new File(storageDirectory, "FamFolio");
        if(!mGalleryFolder.exists()) {
            mGalleryFolder.mkdirs();
        }
        Globals g = (Globals)getApplication();
        g.addFile(mGalleryFolder);
    }
}
