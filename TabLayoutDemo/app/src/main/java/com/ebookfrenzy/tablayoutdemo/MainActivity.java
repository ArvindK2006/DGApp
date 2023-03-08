package com.ebookfrenzy.tablayoutdemo;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.ebookfrenzy.tablayoutdemo.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;

import android.net.Uri;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {



    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        configureTabLayout();
    }

    protected void configureTabLayout(){
        for(int i = 0; i < 4; i++){
            //change to i < 4 when 4th tab works
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
                            case 2:
                                tab.setText("Calendar");
                                break;
                            case 3:
                                tab.setText("About DG");
                                break;
                        }
                    }
                }).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}