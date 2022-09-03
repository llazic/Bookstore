package com.example.pki_projekat.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pki_projekat.R;
import com.example.pki_projekat.model.Data;
import com.example.pki_projekat.model.Recommendation;
import com.example.pki_projekat.view_models.DataViewModel;

public class RecommendationFragment extends Fragment {

    private NavController navController;
    private ImageView imageViewBars, imageViewLogo;
    ConstraintLayout constraintLayoutDynamicContent;
    private DataViewModel dataViewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommendation, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        dataViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
        getViews(view);
        setListeners();

        Data data = dataViewModel.getDataLiveData().getValue();

        LinearLayout linearLayoutRecommendations = (LinearLayout) inflater.inflate(R.layout.linear_layout, container,false);

        for (Recommendation recommendation: data.recommendations) {
            if (recommendation.to.equals(data.currentUser.username)) {
                // Display recommendation.
                View recommendationView = inflater.inflate(R.layout.recommendation_view, container, false);
                TextView textViewTitle = recommendationView.findViewById(R.id.textViewBookTitle);
                TextView textViewUsername = recommendationView.findViewById(R.id.textViewUsername);

                data.books.forEach(book -> {
                    if (book.id == recommendation.bookId) {
                        textViewTitle.setText(book.name);
                    }
                });
                textViewTitle.setOnClickListener(v -> {
                    data.currentBookId = recommendation.bookId;
                    dataViewModel.setDataLiveData(data);
                    navController.navigate(R.id.action_recommendationFragment_to_bookDetailsFragment);
                });

                textViewUsername.setText(recommendation.from);

                linearLayoutRecommendations.addView(recommendationView);
            }
        }

        constraintLayoutDynamicContent.addView(linearLayoutRecommendations);

        return view;
    }

    private void getViews(View view) {
        constraintLayoutDynamicContent = view.findViewById(R.id.constraintLayoutDynamicContent);
        imageViewBars = view.findViewById(R.id.imageViewBars);
        imageViewLogo = view.findViewById(R.id.imageViewLogo);
    }

    private void setListeners() {
        imageViewLogo.setOnClickListener(view -> {
            navController.navigate(R.id.action_recommendationFragment_to_buyerLandingFragment);
        });
        imageViewBars.setOnClickListener(view -> {
            navController.navigate(R.id.action_recommendationFragment_to_buyerMenuFragment);
        });
    }
}