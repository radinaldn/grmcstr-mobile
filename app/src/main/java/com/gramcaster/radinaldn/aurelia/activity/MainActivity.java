package com.gramcaster.radinaldn.aurelia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.gramcaster.radinaldn.aurelia.R;
import com.gramcaster.radinaldn.aurelia.config.ServerConfig;
import com.gramcaster.radinaldn.aurelia.util.SessionManager;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvUsername, tvEmail;
    private ImageView ivProfil;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        ivProfil = header.findViewById(R.id.ivProfil);

        tvUsername = header.findViewById(R.id.tvUsername);
        tvEmail = header.findViewById(R.id.tvEmail);

//        Picasso.with(getApplicationContext())
//                .load(ServerConfig.IMAGE_PATH + "/dosen/" + sessionManager.getDosenDetail().get(TAG_FOTO))
//                .resize(100, 100)
//                .placeholder(R.drawable.dummy_ava)
//                .error(R.drawable.dummy_ava)
//                .centerCrop()
//                .into(imageView);

        tvUsername.setText(sessionManager.getUserDetails().get(SessionManager.USERNAME));
        tvEmail.setText(sessionManager.getUserDetails().get(SessionManager.EMAIL));
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
        if (id == R.id.logout) {
            sessionManager.logoutUser();
            Intent intent =  new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            // Handle the camera action
        } else if (id == R.id.nav_order) {
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_deposit) {
            Intent intent = new Intent(MainActivity.this, DepositActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_affiliate) {
            Intent intent = new Intent(MainActivity.this, AffiliateActivity.class);
            startActivity(intent);

        } else if(id == R.id.nav_konfirmasi_pembayaran) {
            Intent intent = new Intent(MainActivity.this, KonfirmasiPembayaranActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {
            showLicesenceApp();

        } else if (id == R.id.nav_logout) {
            sessionManager.logoutUser();
            Intent intent =  new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showLicesenceApp() {
        AlertDialog alertDialog = new AlertDialog.Builder(
                MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle(getString(R.string.about));

        // Setting Dialog Message
        alertDialog.setMessage(getString(R.string.app_name_version));

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.logo_app_300_x_300);

        // Setting OK Button
//            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // Write your code here to execute after dialog closed
//                    Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
//                }
//            });

        // Showing Alert Message
        alertDialog.show();
    }
}
