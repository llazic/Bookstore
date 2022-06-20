package com.example.pki_projekat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pki_projekat.R;

public class RecommendationFragment extends Fragment {

    private NavController navController;
    private ImageView imageViewBars;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommendation, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        getViews(view);
        setListeners();
        return view;
    }

    private void getViews(View view) {
        imageViewBars = view.findViewById(R.id.imageViewBars);
    }

    private void setListeners() {
        imageViewBars.setOnClickListener(view -> {
            navController.navigate(R.id.action_recommendationFragment_to_buyerMenuFragment);
        });
    }
}