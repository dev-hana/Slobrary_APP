package com.example.autobrary.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public TabAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.mNumOfTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                Frag1 tab1 = new Frag1();
                return tab1;
            case 1:
                Frag2 tab2 = new Frag2();
                return tab2;
            case 2:
                Frag3 tab3 = new Frag3();
                return tab3;
            default:
                return null;
        }
        //return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
