package west.brian.ceg4110_hw1;

import com.ssaurel.mypaint.*;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
        paintView.setVisibility(View.INVISIBLE);
        TextView tv = (TextView) findViewById(R.id.editText);
        tv.setTextColor(Color.rgb(0, 0, 0));
        tv.setText(0+" "+0+" "+0+" #"+Integer.toHexString(0)+Integer.toHexString(0)+Integer.toHexString(0));
        Button bu = (Button)  findViewById(R.id.button4);
        bu.setBackgroundColor(Color.rgb(255,0,0));
        SeekBar sb = findViewById(R.id.seekBar);
        sb.setBackgroundColor(Color.rgb(255,0,0));
        sb = findViewById(R.id.seekBar2);
        sb.setBackgroundColor(Color.rgb(0,255,0));
        sb = findViewById(R.id.seekBar3);
        sb.setBackgroundColor(Color.rgb(0,0,255));
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
    }

    public void Done(View v){
        SeekBar sb = findViewById(R.id.seekBar);
        int r = sb.getProgress();
        sb.setVisibility(View.INVISIBLE);
        sb = findViewById(R.id.seekBar2);
        int g = sb.getProgress();
        sb.setVisibility(View.INVISIBLE);
        sb = findViewById(R.id.seekBar3);
        int b = sb.getProgress();
        sb.setVisibility(View.INVISIBLE);
        Button bu = (Button)  findViewById(R.id.button4);
        bu.setVisibility(View.INVISIBLE);
        bu = (Button)  findViewById(R.id.button3);
        bu.setVisibility(View.INVISIBLE);
        paintView.setCurrentColor(Color.rgb(r, g, b));
    }

    public void updatePre(View v) {
        SeekBar sb = findViewById(R.id.seekBar);
        int r = sb.getProgress();
        sb = findViewById(R.id.seekBar2);
        int g = sb.getProgress();
        sb = findViewById(R.id.seekBar3);
        int b = sb.getProgress();
        Button bu = (Button)  findViewById(R.id.button4);
        bu.setBackgroundColor(Color.rgb(r,g,b));
    }

    //On "Change Button" click the TextView box's message changes to a random color
    public void changeColor(View v) {
        Switch sw = (Switch) findViewById(R.id.switch1);
        if (sw.isChecked()) {
            SeekBar sb = findViewById(R.id.seekBar);
            sb.setVisibility(View.VISIBLE);
            sb = findViewById(R.id.seekBar2);
            sb.setVisibility(View.VISIBLE);
            sb = findViewById(R.id.seekBar3);
            sb.setVisibility(View.VISIBLE);
            Button bu = (Button)  findViewById(R.id.button4);
            bu.setVisibility(View.VISIBLE);
            bu = (Button)  findViewById(R.id.button3);
            bu.setVisibility(View.VISIBLE);
        } else {
            TextView tv = (TextView) findViewById(R.id.editText);
            Random r = new Random();
            int x = r.nextInt(256);
            int y = r.nextInt(256);
            int z = r.nextInt(256);
            tv.setTextColor(Color.rgb(x, y, z));
            tv.setText(x+" "+y+" "+z+" #"+Integer.toHexString(x)+Integer.toHexString(y)+Integer.toHexString(z));
        }
    }

    public void clear(View v) {
        paintView.clear();
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public void save(View v) {
        Bitmap bmp = paintView.getmBitmap();
        TextView tv = (TextView) findViewById(R.id.editText);
        if (isExternalStorageAvailable() && !isExternalStorageReadOnly()) {
            DateFormat df = new SimpleDateFormat("MMddyyyy_HHmmss");
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);
            String FILE_NAME = reportDate+".jpg";
            String baseDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
            File file = new File(baseDir, FILE_NAME);
            tv.setText(FILE_NAME);

            try {
                FileOutputStream out = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.JPEG, 90, out);
                tv.setText("test");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uiChange(View v) {
        TextView tv = (TextView) findViewById(R.id.editText);
        ImageButton sa = (ImageButton) findViewById(R.id.saveButton);
        ImageButton cl = (ImageButton) findViewById(R.id.clearButton);
        //paintView = (PaintView) findViewById(R.id.paintView);
        Switch sw = (Switch) findViewById(R.id.switch1);
        Button bu = (Button) findViewById(R.id.button);
        Button bu2 = (Button) findViewById(R.id.button2);
        if (sw.isChecked()) {
            tv.setVisibility(View.INVISIBLE);
            cl.setVisibility(View.VISIBLE);
            sa.setVisibility(View.VISIBLE);
            bu.setVisibility(View.INVISIBLE);
            bu2.setVisibility(View.VISIBLE);
            paintView.setVisibility(View.VISIBLE);

        } else {
            paintView.setVisibility(View.INVISIBLE);
            cl.setVisibility(View.INVISIBLE);
            sa.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.VISIBLE);
            bu2.setVisibility(View.INVISIBLE);
            bu.setVisibility(View.VISIBLE);
        }
    }
}
