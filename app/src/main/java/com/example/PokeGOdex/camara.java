package com.example.PokeGOdex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class camara extends AppCompatActivity {

    ImageView img;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.camara);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();
            }
        });

    }

    public void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        InputStream stream = null;
        if(requestCode==0 && resultCode==RESULT_OK){
            try{
                if(bitmap!=null){
                    bitmap.recycle();
                }
                stream=getContentResolver().openInputStream(data.getData());
                bitmap= BitmapFactory.decodeStream(stream);
                img.setImageBitmap(resizeImage(this,bitmap,700,600));
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } finally {
                if(stream!=null){
                    try{
                        stream.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private static Bitmap resizeImage(Context context, Bitmap bmpOriginal, float newWidth, float newHeight){
        Bitmap novoBmp= null;
        int w = bmpOriginal.getWidth();
        int h = bmpOriginal.getHeight();

        float densityFactor = context.getResources().getDisplayMetrics().density;
        float novoW = newWidth * densityFactor;
        float novoh = newHeight * densityFactor;

        float scalaw = novoW/w;
        float scalah = novoh/h;

        Matrix matrix = new Matrix();

        matrix.postScale(scalaw,scalah);

        novoBmp = Bitmap.createBitmap(bmpOriginal,0,0,w,h,matrix,true);

        return novoBmp;
    }

    public void girarFoto(View v){
        img.setRotation(90);
    }

    public void volFoto(View v){
        img.setRotation(90);
    }

}
