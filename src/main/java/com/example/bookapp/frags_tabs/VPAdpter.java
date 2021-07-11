package com.example.bookapp.frags_tabs;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VPAdpter extends FragmentStatePagerAdapter {


    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private final ArrayList<String> ArrayList_title = new ArrayList<>();


    public VPAdpter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Log.d("frag_now", "getItem: " + fragmentArrayList.get(position));

        return  fragmentArrayList.get(position);

    }

    public void setCurrentItem(int x){
        Log.d("setfarg", "setCurrentItem: " + fragmentArrayList.get(x));
    }

    public void addFragment(Fragment fragment,String title){
        fragmentArrayList.add(fragment);
        ArrayList_title.add(title);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ArrayList_title.get(position);
    }
}
