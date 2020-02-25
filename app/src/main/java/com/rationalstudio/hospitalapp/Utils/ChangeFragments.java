package com.rationalstudio.hospitalapp.Utils;

import android.content.Context;
import android.os.Bundle;

import com.rationalstudio.hospitalapp.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class ChangeFragments {

    private Context context;

    public ChangeFragments(Context context) {
        this.context = context;
    }
    public void change(Fragment fragment){
        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,fragment,"fragment").setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();


    }
    public void changeWithParameters(Fragment fragment, String docId){
        Bundle bundle = new Bundle();
        bundle.putString("docId",docId);
        fragment.setArguments(bundle);
        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,fragment,"fragment").setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();


    }
}
