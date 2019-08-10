package com.lojpark.mangoskku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private String htmlPageUrl = "https://www.skku.edu/skku/campus/support/welfare_11_1.do?mode=info&srDt=2019-08-12&srCategory=L&conspaceCd=20201104&srResId=3&srShowTime=D";
    private String htmlContentInStringFormat="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setMovementMethod(new ScrollingMovementMethod());

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(htmlPageUrl).get();

                // Elements titles = doc.select("div.news-con h2.tit-news");
                // Elements menus = doc.select("div.mini-diet-wrap div ul");
                Elements menus = doc.select("div.corner_info ul li div.menu_title");

                for (Element e: menus) {
                    System.out.println("menu: " + e.text());
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            textView1.setText(htmlContentInStringFormat);
        }
    }
}
