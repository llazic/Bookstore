package com.example.pki_projekat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pki_projekat.R;

public class MenuFragment extends Fragment {

    private NavController navController;
    private TextView textViewRecommendedBooks, textViewProfile, textViewLogout;
    private ImageView imageViewLogo, imageViewBars;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_menu, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        getViews(view);
        setListeners();
        return view;
    }

    private void getViews(View view) {
        textViewRecommendedBooks = view.findViewById(R.id.textViewRecommendedBooks);
        textViewProfile = view.findViewById(R.id.textViewProfile);
        textViewLogout = view.findViewById(R.id.textViewLogout);
        imageViewLogo = view.findViewById(R.id.imageViewLogo);
        imageViewBars = view.findViewById(R.id.imageViewBars);
    }

    private void setListeners() {
        imageViewLogo.setOnClickListener(view -> {
            navController.navigate(R.id.action_buyerMenuFragment_to_buyerLandingFragment);
        });
        imageViewBars.setOnClickListener(view -> {
            navController.popBackStack();
        });
        textViewRecommendedBooks.setOnClickListener(view -> {
            navController.navigate(R.id.action_buyerMenuFragment_to_recommendationFragment);
        });
        textViewProfile.setOnClickListener(view -> {
            navController.navigate(R.id.action_buyerMenuFragment_to_profileFragment);
        });
        textViewLogout.setOnClickListener(view -> {
            navController.navigate(R.id.action_buyerMenuFragment_to_loginFragment);
        });
    }


}