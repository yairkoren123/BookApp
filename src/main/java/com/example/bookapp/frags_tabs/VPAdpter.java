package com.example.bookapp.frags_tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VPAdpter extends FragmentPagerAdapter {

    private int tab_size;

    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();


    public VPAdpter(@NonNull @NotNull FragmentManager fm, int tab_sizes) {
        super(fm);
        this.tab_size = tab_sizes;
    }

    @Override
    public Fragment getItem(int position) {

        return  fragmentArrayList.get(position);


    }

    public void addFragment(Fragment fragment){
        fragmentArrayList.add(fragment);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

}
