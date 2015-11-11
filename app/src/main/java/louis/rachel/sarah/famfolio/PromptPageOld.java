package louis.rachel.sarah.famfolio;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//Activity which displays randomized prompts and intent for a camera
//Created by Rachel
public class PromptPageOld extends AppCompatActivity {
    private String mCurrentPhotoPath;
    private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
    private ImageView mImageView;
    private Bitmap mImageBitmap;
    private VideoView mVideoView;
    private Uri mVideoUri;
    private static int THING=1;
    Random RANDOM = new Random();
    public final String[] prompts={
            "Selfie time! I’m just not myselfie without you!",
            "Kiddo’s turn! You’re the photographer now, little one! ",
            "Snowy day? Venture out and capture the fun!",
            "Go, team, go! Rep your favorite team on game day! ",
            "Build a blanket fort! Grab supplies and make a super-secret hideaway!",
            "Library adventure! Crack open a good book or two! ",
            "Movie time! Plop down the couch, pop some popcorn and grab a snapshot!   ",
            "Whip up a delight! Capture the fun of making a yummy treat.",
            "Sassy face! Let’s see some attitude, dude! ",
            "Messy hair! Let’s grab a pic of that unkempt do! ",
            "Jammies! Make the best of lazy day and grab a pic of that jammy action! ",
            "Get crafty! Let’s capture those moments of you hard at work! ",
            "Chalk art! Pose with your sidewalk masterpiece! ",
            "Snip, Snip, Snip! Time for a haircut? Don’t forget to point and shoot! ",
            "Get mucky! Squish your toes in the mud and get grubby! ",
            "Lemonade stand! Grab a shot of mixing ice-cold drinks and opening shop!",
            "Boogie down! Strut your stuff and shake it off! ",
            "Curl up with a book! Snag a good read and get started! ",
            "Smooch! Give someone you love a big ol’ smackaroo!",
            "Jump on the bed! Take a leap, little monkey!  ",
            "Mirror, mirror! Check yourself out in the mirror, good looking!  ",
            "Find the perfect leaf! Be on the lookout, my explorer!",
            "Unleash the paintbrush! Swirl colors into a wonderful creation! ",
            "Video game fun! Capture that intense focus-tongue hanging out and all!",
            "Silhouette fun! Find a bright background for a dramatic snap!",
            "Get ready for the day! Sneak a shot of that morning routine.",
            "TP mummy! Get crafty and wind yourself up in a mummy getup! ",
            "Fake laugh! Snap a darn cute photo once the real giggles slip out! ",
            "Collage! Cut out different things you love and paste them together. ",
            "Get drenched! Turn the hose on ",
            "Cool kicks! Stage a shoot featuring your slickest shoes. ",
            "Cherish family! Pose with an old family photo.",
            "Chow time! Make some munchies and chomp down. ",
            "Board game madness! Pull out a board game and capture the fun! ",
            "Spread the love! Make a heart shape with your hands! ",
            "Sing a tune! Grab a hairbrush and belt it out!",
            "Staircase showcase! Snag a seat on the stairs and pose for a snap!",
            "News flash! Get the scoop by reading the paper!",
            "Meditate! Chill out in a cross-legged pose. ",
            "Make a mess! Just remember to clean up after yourself!",
            "Attitude of gratitude! Write a list of the 10 things you’re most thankful for.",
            "Squirt gun fun! Have a soaking good time! ",
            "Tell a family story! Draw a picture of your family doing something! ",
            "Flapjack bash! Take charge of breakfast and stir up a yummy pancake batter!",
            "Breezy close-up! Plop in front of a fan and let your hair blow in the wind. ",
            "Take a hike! Lace up your boots and follow your compass!",
            "Send a letter! Take a snap of your little cub writing a letter and sending it off! ",
            "Work it out! Push-ups and jumping jacks got nothing on you!",
            "Made in the shade! Your future’s so bright you gotta wear sunglasses! ",
            "Hoola hoop! Use those hips to get twirling!",
    };
    TextView tx;
    TextView ty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt_page_old);

        //Custom Font
        Typeface head = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Medium.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        final TextView textOne=(TextView) findViewById(R.id.textView1);
        textOne.setTypeface(head);
        final TextView textTwo=(TextView) findViewById(R.id.textView2);
        textTwo.setTypeface(tf);
        ImageButton pushMe = (ImageButton) findViewById(R.id.randomizer);

        //Split prompts into two lines
        int length=prompts.length;
        String prompt = prompts[RANDOM.nextInt(length)];
        if(prompt.matches(".*! .+") && !prompt.matches("!$")) {
            textOne.setText(prompt.substring(0, prompt.indexOf('!') + 2));
            textTwo.setText(prompt.substring(prompt.indexOf('!') + 2));
        }
        else if(prompt.matches(".*? .+") && !prompt.matches("\\?$")) {
            textOne.setText(prompt.substring(0, prompt.indexOf('?') + 2));
            textTwo.setText(prompt.substring(prompt.indexOf('?') + 2));
        }

        //Bring up new prompt if button is clicked
        pushMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length=prompts.length;
                String prompt = prompts[RANDOM.nextInt(length)];
                if(prompt.matches(".*! .+") && !prompt.matches("!$")) {
                    textOne.setText(prompt.substring(0, prompt.indexOf('!') + 2));
                    textTwo.setText(prompt.substring(prompt.indexOf('!') + 2));
                }
                else if(prompt.matches(".*? .+") && !prompt.matches("\\?$")) {
                    textOne.setText(prompt.substring(0, prompt.indexOf('?') + 2));
                    textTwo.setText(prompt.substring(prompt.indexOf('?') + 2));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prompt_page_old, menu);
        return true;
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

    //Camera set up and adding image to gallery

    public void goPhotoIntent(View view){
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

        startActivityForResult(takePictureIntent, THING);
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.fromFile(f));
        sendBroadcast(mediaScanIntent);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "FamFolio" + timeStamp + "_";
        File albumF = getAlbumDir();
        File imageF = File.createTempFile(imageFileName, ".jpg", albumF);
        return imageF;
    }
    private File getAlbumDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            storageDir = mAlbumStorageDirFactory.getAlbumStorageDir("FamFolio");

            if (storageDir != null) {
                if (! storageDir.mkdirs()) {
                    if (! storageDir.exists()){
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }

        } else {
            Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
        }

        return storageDir;
    }
}
