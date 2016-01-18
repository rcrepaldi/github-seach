package br.com.desafio.githubsearch.objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rodrigo on 18/01/16.
 */
public class LoadingImage extends Thread{

    private ImageView imageView;
    private String urlImage;

    public LoadingImage(String url, ImageView imageView){
        this.imageView = imageView;
        this.urlImage = url;
    }

    public void run(){
        Bitmap bitmap = null;
        try {

            URL url = new URL(urlImage);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            // Update UI information
            final Bitmap finalBitmap = bitmap;
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(finalBitmap);
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
