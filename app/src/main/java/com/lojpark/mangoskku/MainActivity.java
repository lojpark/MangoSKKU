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
                String htmlPageUrl = "https://www.skku.edu/skku/campus/support/welfare_11_1.do?mode=info&srDt=2019-08-16&srCategory=L&conspaceCd=20201104&srResId=3&srShowTime=D";
                Document doc = Jsoup.connect(htmlPageUrl).get();

                Elements menus = doc.select("div.corner_info ul li div.menu_title");

                htmlContentInStringFormat += "<행단골>" + "\n";
                for (Element e: menus) {
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }

                htmlPageUrl = "https://www.skku.edu/skku/campus/support/welfare_11_1.do?mode=info&srDt=2019-08-16&srCategory=L&conspaceCd=20201040&srResId=11&srShowTime=D";
                doc = Jsoup.connect(htmlPageUrl).get();

                menus = doc.select("div.corner_info ul li div.menu_title");

                htmlContentInStringFormat += "<구시재>" + "\n";
                for (Element e: menus) {
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }

                htmlPageUrl = "https://www.skku.edu/skku/campus/support/welfare_11_1.do?mode=info&srDt=2019-08-16&srCategory=L&conspaceCd=20201097&srResId=10&srShowTime=D";
                doc = Jsoup.connect(htmlPageUrl).get();

                menus = doc.select("div.corner_info ul li div.menu_title");

                htmlContentInStringFormat += "<해오름>" + "\n";
                for (Element e: menus) {
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }

                htmlPageUrl = "https://dorm.skku.edu/dorm_suwon";
                doc = Jsoup.connect(htmlPageUrl).get();

                menus = doc.select("div.mini-diet-wrap");

                htmlContentInStringFormat += "<기숙사>" + "\n";
                for (Element e: menus) {
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
