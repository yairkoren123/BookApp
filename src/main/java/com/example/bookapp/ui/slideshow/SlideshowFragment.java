package com.example.bookapp.ui.slideshow;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.bookapp.R;
import com.example.bookapp.databinding.FragmentGalleryBinding;
import com.example.bookapp.databinding.FragmentSlideshowBinding;
import com.example.bookapp.fin.Splash_screen;
import com.example.bookapp.frags_tabs.Fragment_Quotes_tab;
import com.example.bookapp.frags_tabs.Fragment_Search_tab;
import com.example.bookapp.frags_tabs.Fragment_popular_tab;
import com.example.bookapp.frags_tabs.VPAdpter;
import com.example.bookapp.ui.gallery.GalleryViewModel;
import com.example.bookapp.ui.gallery.Search_book_Fragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class SlideshowFragment extends Fragment {


    // quotes

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;


    // layout

    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;

    Fragment myFragment;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        binding = FragmentSlideshowBinding.inflate(inflater,container,false);
        View root = binding.getRoot();


        myFragment = new Splash_screen();
        getActivity().getSupportFragmentManager()
                .beginTransaction().add(R.id.mail_countener9, myFragment)
                .addToBackStack(null).commit();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.d("fag", "run: 1");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("fag2", "run: 1");
                        getActivity().getSupportFragmentManager().beginTransaction().remove(myFragment).commit();
                    }
                },1000);

            }
        },1000);


        return root;



    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {


        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);


        viewPager = getView().findViewById(R.id.viewpager_tabs);
        setupViewPager(viewPager);


        tabLayout = getView().findViewById(R.id.tab_lay);
        tabLayout.setupWithViewPager(viewPager);


    }
    private void setupViewPager(ViewPager viewPager) {
        VPAdpter adapter = new VPAdpter(getActivity().getSupportFragmentManager());

        adapter.notifyDataSetChanged();

        adapter.addFragment(new Fragment_Quotes_tab(), "Quotes");
        adapter.addFragment(new Fragment_popular_tab(), "Popular");
        adapter.addFragment(new Fragment_Search_tab(), "Search");


        viewPager.setAdapter(adapter);


        adapter.notifyDataSetChanged();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}