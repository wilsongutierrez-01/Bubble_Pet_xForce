package com.gutierrez.bubble_pet_xforce.Controllers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerController extends FragmentPagerAdapter {
    int tbnum;

    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.tbnum = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Expediente();

            case 1:
                return new Servicios();

            case 2:
                return new Registro();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tbnum;
    }
}
