package com.example.ktra22.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ktra22.fragment.Fragment_ds;
import com.example.ktra22.fragment.Fragment_tk;
import com.example.ktra22.fragment.Fragment_tt;

public class ViewPagerAdapter extends FragmentPagerAdapter {

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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Danh sách";
            case 1:
                return "Thông tin";
            case 2:
                return  "Tìm kiếm";
            default:
                return "Danh sách";

        }
    }
}
