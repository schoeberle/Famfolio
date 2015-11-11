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

//Activity which displayed randomized prompts and intent for a camera
//created by Rachel
public class PromptPage extends AppCompatActivity {
    private String mCurrentPhotoPath;
    private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
    private ImageView mImageView;
    private Bitmap mImageBitmap;
    private VideoView mVideoView;
    private Uri mVideoUri;
    private static int THING=1;
    Random RANDOM = new Random();
    public final String[] prompts={
            "Reach for the stars! Stretch your arms up high and touch the sky!",
            "Strike a pose! Make like a diva and give your best model face.",
            "How old are you? Hold up how many fingers old you are!  ",
            "Kissy face! Show some love with your best kissy face.",
            "Say cheese! Give your biggest smile!",
            "Jump for joy! Reach out and take a leap, bumpkins!",
            "Sleepy head! Can you pretend its naptime?",
            "Get groovy! Let’s see your best dance moves!",
            "Peek-a-boo! Where’d you go?",
            "Run, kiddo, run! Get speedy and take a running start.",
            "Cozy up to blankie! Get snug as a bug in a rug!",
            "Act like a critter! Can you pretend to be your favorite animal?",
            "Cuddle up! Snuggle with someone you love!",
            "Feetsies in the air! Are your piggy toes ready for their close-up?",
            "Bookworm pride! Show off your favoritest book!",
            "Snuggle your lovey! Can you give your favorite “lovey” toy a great big hug?",
            "Kick up high! Show me what you got, karate kid!",
            "Get shady! Pop on some sunglasses and shine on!",
            "My, how big you are! Let’s bring out the tape measure!",
            "Get posey! Throw up a peace sign!",
            "Silly face! Make your craziest, nuttiest funny face!",
            "Show off your masterpiece! Capture your little artist hard at work!",
            "Choose your own outfit! What styling combo will you rock?",
            "Rawr! Pretend you are the scariest monster in the world!",
            "No rules! Stand up on the couch, wild thing!",
            "Venture out! Explore the great outdoors.",
            "Totally silly face! Stick that silly tongue out!",
            "Umbrella huddle up! Does it feel drizzly to you or is it just me?",
            "Down on the ground! Take a fun tumble on the floor.",
            "Nose to nose! Give someone you love an Eskimo kiss.",
            "Break out the bubbles! Revel in the magic of bubble blowing!",
            "Take aim! Let’s see your pitcher’s arm in action!",
            "Splish splash! Capture the soapy fun of bath time.",
            "Mess up the bed! Life is sweeter under the sheets! ",
            "Breakfast chow down! Nosh on some morning time grub!   ",
            "Great big belly laugh! Grab a pic of giggly, good times.",
            "Shall we dance? Partner up for a boogie down!",
            "Look out the window! There’s a great big world out there!",
            "Play a game of airplane! Grab a fun-loving adult to hoist you up, up and away!",
            "Tea party! Make pretend we’re at the ritziest tea party of all!",
            "Nudie Judie!  Naked kid on the loose? Grab a shot of the goofiness!",
            "Sport a hat! Gear up with your cutest cap.  ",
            "Rep your favorite team! Be the ultimate fan for game day!",
            "Playground fun! Have a go at the slide and hop on the swings! ",
            "Work up a sweat! Play hard and have fun! ",
            "Hands on! Get close to capture your little cub’s paws! ",
            "Bedtime story! Climb up on the lap of someone you love for a goodnight story! ",
            "Crazy socks! Show off your nuttiest pair of socks!",
            "Pick a flower! Search for a lovely flower to share.  ",
            "Pat-a-cake, Pat-a-cake! Play a happy game of Patty Cake!"
    };

    TextView tx;
    TextView ty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt_page);

        //Custom Fonts
        Typeface head = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Medium.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        final TextView textOne = (TextView) findViewById(R.id.textView1);
        textOne.setTypeface(head);
        final TextView textTwo = (TextView) findViewById(R.id.textView2);
        textTwo.setTypeface(tf);
        ImageButton pushMe = (ImageButton) findViewById(R.id.randomizer);
        pushMe.bringToFront();
        pushMe.requestLayout();
        pushMe.invalidate();



        //Split prompts into two parts by punctuation
        int length = prompts.length;
        String prompt = prompts[RANDOM.nextInt(length)];
        if (prompt.matches(".*! .+") && !prompt.matches("!$")) {
            textOne.setText(prompt.substring(0, prompt.indexOf('!') + 2));
            textTwo.setText(prompt.substring(prompt.indexOf('!') + 2));
        } else if (prompt.matches(".*? .+") && !prompt.matches("\\?$")) {
            textOne.setText(prompt.substring(0, prompt.indexOf('?') + 2));
            textTwo.setText(prompt.substring(prompt.indexOf('?') + 2));
        }

        //Bring up a new prompt if button is clicked
        pushMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = prompts.length;
                String prompt = prompts[RANDOM.nextInt(length)];
                if (prompt.matches(".*! .+") && !prompt.matches("!$")) {
                    textOne.setText(prompt.substring(0, prompt.indexOf('!') + 2));
                    textTwo.setText(prompt.substring(prompt.indexOf('!') + 2));
                } else if (prompt.matches(".*? .+") && !prompt.matches("\\?$")) {
                    textOne.setText(prompt.substring(0, prompt.indexOf('?') + 2));
                    textTwo.setText(prompt.substring(prompt.indexOf('?') + 2));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prompt_page, menu);
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
