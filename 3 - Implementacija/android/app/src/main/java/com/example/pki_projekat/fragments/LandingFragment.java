package com.example.pki_projekat.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pki_projekat.R;


public class LandingFragment extends Fragment {

    private ImageView imageViewBars, imageViewLogo;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_landing, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        getViews(view);
        setListeners();

        View book = inflater.inflate(R.layout.book, container, false);
        ImageView bookImage = book.findViewById(R.id.bookImage);
        String PACKAGE_NAME = getContext().getPackageName();
        int imageResource = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+"noz_ju_nesbe_v", null, null);
        bookImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), imageResource));

        ViewGroup imagesLayout = view.findViewById(R.id.imagesLayout);
        int imagesLayoutWidth = imagesLayout.getWidth();
        imagesLayout.addView(book);

        book = inflater.inflate(R.layout.book, container, false);
        bookImage = book.findViewById(R.id.bookImage);
        imageResource = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+"noz_ju_nesbe_v", null, null);
        bookImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), imageResource));

        imagesLayout.addView(book);

        return view;
    }

    private void getViews(View view) {
        imageViewBars = view.findViewById(R.id.imageViewBars);
        imageViewLogo = view.findViewById(R.id.imageViewLogo);
    }

    private void setListeners() {
        imageViewBars.setOnClickListener(view -> {
            navController.navigate(R.id.action_buyerLandingFragment_to_buyerMenuFragment);
        });
    }
}