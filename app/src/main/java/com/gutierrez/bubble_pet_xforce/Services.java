package com.gutierrez.bubble_pet_xforce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.gutierrez.bubble_pet_xforce.Controllers.PagerController;
import com.gutierrez.bubble_pet_xforce.Controllers.Servicios;

public class Services extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerController pagerAdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        linking();


       pagerAdp = new PagerController(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdp);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                
                if (tab.getPosition() == 0){
                    pagerAdp.notifyDataSetChanged();
                }
                if (tab.getPosition() == 1){
                    pagerAdp.notifyDataSetChanged();
                }
                if(tab.getPosition() == 2){
                    pagerAdp.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void linking (){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewControl);
    }


}