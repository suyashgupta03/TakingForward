package com.takingforward;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.os.Handler;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private WebView webview;
    private ProgressBar progressBar;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));
        }
        setContentView(R.layout.activity_home);
        Fabric.with(this, new Crashlytics());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        webview = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.toolbar_progress_bar);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        webview.setWebViewClient(new WebViewClient() {

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }

            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(HomeActivity.this, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

            }
        });
        if (Utils.isConnected(this)) {
            webview.loadUrl("http://www.takingforward.com/index.php");
        } else {
            Toast.makeText(HomeActivity.this, getString(R.string.err_no_internet), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                webview.clearCache(true);
                webview.clearHistory();
                webview.clearFormData();
                webview = null;
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if (Utils.isConnected(this)) {
                webview.loadUrl("http://www.takingforward.com/index.php");
            } else {
                Toast.makeText(HomeActivity.this, getString(R.string.err_no_internet), Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nav_register) {
            if (Utils.isConnected(this)) {
                webview.loadUrl("http://www.takingforward.com/seat_register");
            } else {
                Toast.makeText(HomeActivity.this, getString(R.string.err_no_internet), Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nav_live_market) {
            if (Utils.isConnected(this)) {
                webview.loadUrl("https://www.google.co.in/finance");
            } else {
                Toast.makeText(HomeActivity.this, getString(R.string.err_no_internet), Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nav_contact) {
            if (Utils.isConnected(this)) {
                webview.loadUrl("http://www.takingforward.com/contact");
            } else {
                Toast.makeText(HomeActivity.this, getString(R.string.err_no_internet), Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nav_about) {
            if (Utils.isConnected(this)) {
                webview.loadUrl("http://www.takingforward.com/about");
            } else {
                Toast.makeText(HomeActivity.this, getString(R.string.err_no_internet), Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nav_indian_market) {
            if (Utils.isConnected(this)) {
                webview.loadUrl("http://m.economictimes.com/");
            } else {
                Toast.makeText(HomeActivity.this, getString(R.string.err_no_internet), Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nav_world_market) {
            if (Utils.isConnected(this)) {
                webview.loadUrl("http://mobile.reuters.com/");
            } else {
                Toast.makeText(HomeActivity.this, getString(R.string.err_no_internet), Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nav_earning_calendar) {
            if (Utils.isConnected(this)) {
                webview.loadUrl("https://takingforward.com/marketstatview.php");
            } else {
                Toast.makeText(HomeActivity.this, getString(R.string.err_no_internet), Toast.LENGTH_LONG).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
