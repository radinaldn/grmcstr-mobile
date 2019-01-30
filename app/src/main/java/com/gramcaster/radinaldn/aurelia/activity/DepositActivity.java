package com.gramcaster.radinaldn.aurelia.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gramcaster.radinaldn.aurelia.R;
import com.gramcaster.radinaldn.aurelia.adapter.DepositViewPagerAdapter;
import com.gramcaster.radinaldn.aurelia.fragment.DepositHistoryFragment;
import com.gramcaster.radinaldn.aurelia.fragment.DepositIndexFragment;
import com.gramcaster.radinaldn.aurelia.viewpager.CustomViewPager;

public class DepositActivity extends AppCompatActivity {

    CustomViewPager viewPager;
    TabLayout tabLayout;

    private static final String TAG = DepositActivity.class.getSimpleName();
    //  ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        DepositViewPagerAdapter adapter = new DepositViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DepositIndexFragment(), "Index");
        adapter.addFragment(new DepositHistoryFragment(), "History");

        viewPager.setAdapter(adapter);
    }

    private void goToMainActivity(){
        Intent intent = new Intent(DepositActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
