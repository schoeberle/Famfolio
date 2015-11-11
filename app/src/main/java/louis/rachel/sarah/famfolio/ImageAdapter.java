package louis.rachel.sarah.famfolio;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

//import com.example.android.photobyintent.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which sets up the recyclerView image album and OnclickListeners to delete and send images
 * Created by Sarah on 10/30/2015.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private File imagesFile;
    private ArrayList list;

    public ImageAdapter(File folderFile) {
        imagesFile = folderFile;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        File imageFile = imagesFile.listFiles()[position];
        Bitmap imageBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        Bitmap newImage = RotateBitmap(imageBitmap, 270);
        holder.getImageView().setImageBitmap(newImage);
    }

    @Override
    public int getItemCount() {
        return imagesFile.listFiles().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            //send image on click
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Log.d("on", "onClick " + getAdapterPosition());
                    String subject = "Special Delivery From Famfolio!";
                    File f = imagesFile.listFiles()[getAdapterPosition()];
                    Log.d("on", "onClick " + f);
                    Uri sigURI = Uri.parse("file://" + f);
                    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                    emailIntent.putExtra(Intent.EXTRA_STREAM, sigURI);
                    (v.getContext()).startActivity(Intent.createChooser(emailIntent, "Sending Email...."));

                }
            });
            //delete image on long click
            view.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    File f = imagesFile.listFiles()[getAdapterPosition()];
                    String[] path = new String[]{f.toString()};
                    if (f.exists()) {
                        f.delete();
                        ContentResolver resolver = v.getContext().getContentResolver();
                        resolver.delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.DATA + "=?", path);
                    }
                    notifyItemRemoved(getAdapterPosition());
                    return true;

                }
            });
            imageView = (ImageView) view.findViewById(R.id.imageGalleryView);
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
    /*rotate a bitmap */
    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        if (source != null) {
            return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
        }
        else return source;
    }
}

