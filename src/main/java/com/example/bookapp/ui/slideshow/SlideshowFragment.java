package com.example.bookapp.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.bookapp.frags_tabs.Fragment_Quotes_tab;
import com.example.bookapp.frags_tabs.Fragment_Search_tab;
import com.example.bookapp.frags_tabs.Fragment_popular_tab;
import com.example.bookapp.frags_tabs.VPAdpter;
import com.example.bookapp.ui.gallery.GalleryViewModel;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;



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


        return root;



    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {

    TabLayout tabLayout = getView().findViewById(R.id.tab_lay);

//        TabItem tab_quotes = view.findViewById(R.id.quotes_tab);
//        TabItem tab_popular = view.findViewById(R.id.popular_tab3);
//        TabItem tab_search = view.findViewById(R.id.search_tab);

        ViewPager viewPager = getView().findViewById(R.id.viewpager_tabs);


        VPAdpter vpAdpter =
                new VPAdpter(getActivity().getSupportFragmentManager(),
                        FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        vpAdpter.addFragment(new Fragment_Quotes_tab());
        vpAdpter.addFragment(new Fragment_popular_tab());
        vpAdpter.addFragment(new Fragment_Search_tab());

        viewPager.setAdapter(vpAdpter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}