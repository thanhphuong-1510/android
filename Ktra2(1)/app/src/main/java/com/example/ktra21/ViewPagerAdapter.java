package com.example.ktra21;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ktra21.fragment.Fragment_ds;
import com.example.ktra21.fragment.Fragment_tk;
import com.example.ktra21.fragment.Fragment_tt;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_ds();
            case 1:
                return new Fragment_tt();

            case 2:
                return  new Fragment_tk();


            default:
                return new Fragment_ds();

        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
