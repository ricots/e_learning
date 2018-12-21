package com.androidcodefinder.dashboarddesign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.androidcodefinder.dashboarddesign.oop.item;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class detail_materi extends AppCompatActivity {
    TextView dtl_jdl,  dtl_refrensi;
    JustifyTextView dtl_isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_materi);
        Intent intent = getIntent();
        final item dtlnya = (item) intent.getSerializableExtra("kirim");
        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
        dtl_jdl = (TextView) findViewById(R.id.dtl_jdl);
        dtl_isi = (JustifyTextView) findViewById(R.id.dtl_isi);
        dtl_refrensi = (TextView) findViewById(R.id.dtl_refrensi);
        final String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"420\" height=\"315\" " +
                "src=" + dtlnya.getVideo()+  " frameborder=\"0\" allowfullscreen></iframe></body></html>";
        final WebView displayYoutubeVideo = (WebView) findViewById(R.id.web);
        displayYoutubeVideo.setWebViewClient(new SSLTolerentWebViewClient());
//        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        });
        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayYoutubeVideo.loadUrl("https://www.youtube.com/watch?v=8MDzs81zl8I");
//
//        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(displayYoutubeVideo, url);
//                Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(getApplicationContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
//            }
//        });
//        displayYoutubeVideo.loadUrl(dtlnya.getVideo());
//        displayYoutubeVideo.loadData(frameVideo,"text/html", "utf-8");
//        displayYoutubeVideo.loadData(frameVideo, dtlnya.getVideo(), "utf-8");
//        VideoView videoView = (VideoView) findViewById(R.id.video);
//        MediaController mediaController= new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+"https://drive.google.com/open?id=1uvoP_OeKs1ykAbiiH6PLzkJIa8-Y52Gw");

        dtl_jdl.setText(dtlnya.getJudul());
        dtl_isi.setText(dtlnya.getIsi());
        dtl_refrensi.setText(dtlnya.getRefrensi());
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//        videoView.start();
    }


    private class SSLTolerentWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
