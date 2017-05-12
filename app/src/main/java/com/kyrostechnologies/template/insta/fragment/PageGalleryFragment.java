package com.kyrostechnologies.template.insta.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kyrostechnologies.template.insta.R;

public class PageGalleryFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.page_fragment_gallery, container, false);
        ((Button) view.findViewById(R.id.bt_photo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Take Photo Clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

        ((Button) view.findViewById(R.id.bt_video)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Take Video Clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
