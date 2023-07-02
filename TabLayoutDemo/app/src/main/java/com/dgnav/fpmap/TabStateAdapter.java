package com.dgnav.fpmap;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabStateAdapter extends FragmentStateAdapter{

    int tabCount;

    public TabStateAdapter(FragmentActivity fa, int numberOfTabs){
        super(fa);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment createFragment(int position){
        switch(position){
            case 0:
                return new HomeFragment();
            case 1:
                return new MapsFragment();
            //case 2:
            //    return new EventsFragment();
            case 2:
                //change to case 3 when adding events fragment
                return new AboutFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount(){
        return tabCount;
    }

}
