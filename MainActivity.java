package sukesh.agnite.intentcam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    public static final int ACTIVITY_START_CAM_APP=0;
    //private ImageView imageView;
    PicHolder picHolderObj;
    static String mImageFileLocation="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //picHolderObj.CapImageView = (ImageView) findViewById(R.id.imageView);
    }

    public void takePhoto(View v)
    {
        Intent callCamAppIntent=new Intent();
        callCamAppIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try
        {
         photoFile = createImageFile();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        callCamAppIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        startActivityForResult(callCamAppIntent,ACTIVITY_START_CAM_APP);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==ACTIVITY_START_CAM_APP && resultCode==RESULT_OK)
        {
            Intent callPicHolderIntent = new Intent(this,PicHolder.class);
            startActivity(callPicHolderIntent);
           /* Bitmap photoCaptureBitmap = BitmapFactory.decodeFile(mImageFileLocation);
            picHolderObj.CapImageView.setImageBitmap(photoCaptureBitmap);*/
           /* Bundle extra= data.getExtras();
            Bitmap photoCaptureBitmap= (Bitmap) extra.get("data");
            imageView.setImageBitmap(photoCaptureBitmap);*/
//            Toast.makeText(this,"captured",Toast.LENGTH_SHORT).show();
        }
    }

    File createImageFile() throws IOException {
        String timeStamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName="Image_"+timeStamp;

        File storageDirectory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image=File.createTempFile(fileName,".jpg",storageDirectory);

        mImageFileLocation =image.getAbsolutePath();

        return image;
    }
}
