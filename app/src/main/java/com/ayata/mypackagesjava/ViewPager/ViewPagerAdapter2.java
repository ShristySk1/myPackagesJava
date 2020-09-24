package com.ayata.mypackagesjava.ViewPager;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/*
used with viewpager2 with fragments
mostly used with tab layout
*/
public class ViewPagerAdapter2 extends FragmentStateAdapter {
    public ViewPagerAdapter2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Tab1Fragment();
            case 1:
                return new Tab2Fragment();

        }
        return new Tab1Fragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
