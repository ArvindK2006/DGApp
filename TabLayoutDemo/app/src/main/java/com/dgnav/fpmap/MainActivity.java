package com.dgnav.fpmap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.dgnav.fpmap.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager2 = findViewById(R.id.view_pager);

        viewPager2.setUserInputEnabled(false);

        setSupportActionBar(binding.toolbar);

        configureTabLayout();
    }


    protected void configureTabLayout(){

        for(int i = 0; i < 3; i++){
            //change to i < 4 when adding events fragment
            binding.tabLayout.addTab(binding.tabLayout.newTab());
        }
        final FragmentStateAdapter adapter = new TabStateAdapter(this, binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(adapter);


        //new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) ->
          //      tab.setText("Tab " + (position + 1) + " Item")).attach();

        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {

                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch(position) {
                            case 0:
                                tab.setText("Home");
                                break;
                            case 1:
                                tab.setText("Map");
                                break;
                            //case 2:
                             //   tab.setText("Events");
                             //   break;
                            case 2:
                                //change to case 3 when adding events fragment
                                tab.setText("About");
                                break;
                        }
                    }
                }).attach();
    }


}