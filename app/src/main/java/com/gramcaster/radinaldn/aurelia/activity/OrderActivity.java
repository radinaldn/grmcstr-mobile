package com.gramcaster.radinaldn.aurelia.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gramcaster.radinaldn.aurelia.R;
import com.gramcaster.radinaldn.aurelia.fragment.OrderHistoryFragment;
import com.gramcaster.radinaldn.aurelia.adapter.OrderViewPagerAdapter;
import com.gramcaster.radinaldn.aurelia.fragment.OrderIndexFragment;
import com.gramcaster.radinaldn.aurelia.viewpager.CustomViewPager;

public class OrderActivity extends AppCompatActivity {

    CustomViewPager viewPager;
    TabLayout tabLayout;

    private static final String TAG = OrderActivity.class.getSimpleName();
    //  ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

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
        OrderViewPagerAdapter adapter = new OrderViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OrderIndexFragment(), "Index");
        adapter.addFragment(new OrderHistoryFragment(), "History");

        viewPager.setAdapter(adapter);
    }

    private void goToMainActivity(){
        Intent intent = new Intent(OrderActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
