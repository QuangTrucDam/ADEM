package com.adem.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adem.ListPostMain.News;
import com.adem.ListPostMain.NewsAdapter;
import com.adem.R;
import com.adem.controller.ParseJsonLogin;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

public static final String KEY_SPLIT ="aacacac";

    private String STRING_URL = "http://vnexpress.net/rss/khoa-hoc.rss";

    ProgressDialog progressDialog;

    ListView lsvNews;
    ArrayList<News> model;
    NewsAdapter adapter;

    ConnectTask connectTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        addControls();
        addEvents();
        process();





        // chuyển activity
//        startActivity(new Intent(MainActivity.this, PostActivity.class));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        String t="";
        String[] d = t.split(KEY_SPLIT);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //keyHash

        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.adem",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/


        //facebook

        if (AccessToken.getCurrentAccessToken() == null) {
            gotoLoginScreem();
        } else {
            AccessToken accesstoken = AccessToken.getCurrentAccessToken();
            Profile profile = Profile.getCurrentProfile();
            Log.d("accesstoken",AccessToken.getCurrentAccessToken().getToken());
            ParseJsonLogin login = new ParseJsonLogin(AccessToken.getCurrentAccessToken(),this);
            login.setInfoUser();


        }

        //navigation

    }


    private void addControls() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang xử lý, vui lòng chờ....");
        progressDialog.setCanceledOnTouchOutside(false);

        lsvNews = (ListView) findViewById(R.id.lsvNews);

        model = new ArrayList<News>();
        adapter = new NewsAdapter(MainActivity.this, R.layout.new_item, model);
        lsvNews.setAdapter(adapter);
    }


    private void addEvents() {
        lsvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.parse(model.get(position).getLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private void process(){
        connectTask = new ConnectTask();
        connectTask.execute();
    }


    private void gotoLoginScreem() {
        Intent intent= new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_adv_post) {
            // Handle the camera action
        } else if (id == R.id.nav_bussines_manage) {

        } else if (id == R.id.nav_auto_post) {
            startActivity(new Intent(MainActivity.this, PostActivity.class));

        } else if (id == R.id.nav_management) {

        } else if (id == R.id.nav_invite_friends) {

        } else if (id == R.id.nav_logout) {
            LoginManager.getInstance().logOut();
            gotoLoginScreem();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    class ConnectTask extends AsyncTask<Void, Void, ArrayList<News>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<News> model) {
            super.onPostExecute(model);

            MainActivity.this.model = model;
            adapter.clear();
            adapter.addAll(model);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<News> doInBackground(Void... params) {
            ArrayList<News> arr = new ArrayList<News>();
            try {
                URL url = new URL(STRING_URL);

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(url.openConnection().getInputStream(), "UTF_8");


                boolean insideItem = false;

                int eventType = xpp.getEventType();
                News news = new News();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {

                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                            news = new News();
                        }
                        else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem)
                                news.setTitle(xpp.nextText());
                        }
                        else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem)
                                news.setLink(xpp.nextText());
                        }
                        else if (xpp.getName().equalsIgnoreCase("pubDate")){
                            if (insideItem)
                                news.setTime(xpp.nextText());
                        }
                        else if (xpp.getName().equalsIgnoreCase("description")){
                            if (insideItem)
                                news.processCData(xpp.nextText());
                        }
                    }
                    else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                        insideItem=false;
                        arr.add(news);
                    }

                    eventType = xpp.next(); //move to next element
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                Log.e("Loi Website", e.toString());
            }
            return arr;
        }
    }
}
