package com.khhh.kmovies;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {


    public static RecyclerView gridView;
    public static ProgressBar progressBar;
    public static RecyclerView recyclerView;
    String categoryName;
    public MovieFragment() {
        // Required empty public constructor
    }



    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_movie, container, false);

        progressBar=view.findViewById(R.id.progressBar);
        recyclerView=view.findViewById(R.id.new_movie_list);
        FireBaseConnect connect=new FireBaseConnect();
        FireBaseConnect.context=getContext();
        FireBaseConnect.inflater=getLayoutInflater();
        FireBaseConnect.activity=getActivity();
        connect.getMovies(categoryName);
        loadAds();
        return view;

    }

    public void loadAds()
    {
        MobileAds.initialize(getContext(),getResources().getString(R.string.app_id));
        AdLoader adLoader = new AdLoader.Builder(getContext(), getResources().getString(R.string.advanced_ads))
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        ColorDrawable background=new ColorDrawable(Color.WHITE);
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().withMainBackgroundColor(background).build();

                        TemplateView template = view.findViewById(R.id.my_template);
                        template.setStyles(styles);
                        template.setNativeAd(unifiedNativeAd);

                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());

        AdLoader adLoader1 = new AdLoader.Builder(getContext(), getResources().getString(R.string.advanced_ads))
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        ColorDrawable background=new ColorDrawable(Color.WHITE);
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().withMainBackgroundColor(background).build();

                        TemplateView template = view.findViewById(R.id.my_template2);
                        template.setStyles(styles);
                        template.setNativeAd(unifiedNativeAd);

                    }
                })
                .build();

        adLoader1.loadAd(new AdRequest.Builder().build());
    }


}
