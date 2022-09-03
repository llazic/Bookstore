package com.example.pki_projekat.fragments;

import android.graphics.BitmapFactory;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pki_projekat.R;
import com.example.pki_projekat.model.Book;
import com.example.pki_projekat.model.Data;
import com.example.pki_projekat.model.Recommendation;
import com.example.pki_projekat.model.Review;
import com.example.pki_projekat.model.User;
import com.example.pki_projekat.view_models.DataViewModel;

public class BookDetailsFragment extends Fragment {
    private ImageView imageViewBars, imageViewLogo, imageViewBookImage;
    private ImageView imageViewInfo, imageViewReviews, imageViewShare;
    private TextView textViewTitle, textViewAuthor, textViewReleaseYear, textViewPageCount;
    private TextView textViewBookDescription;
    private ConstraintLayout constraintLayoutDynamicContent;

    private NavController navController;
    private LayoutInflater inflater;
    private ViewGroup container;

    private DataViewModel dataViewModel;
    private Book currentBook;
    private int currentBookId;

    private int rating;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_book_details, container, false);
        this.inflater = inflater;
        this.container = container;
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        dataViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);

        getViews(layout);
        setListeners();
        loadCurrentBookData();

        return layout;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadCurrentBookData() {
        Data data = dataViewModel.getDataLiveData().getValue();
        currentBookId = data.currentBookId;
        data.books.forEach(book -> { if (book.id == currentBookId) currentBook = book; });

        textViewTitle.setText(currentBook.name);
        textViewAuthor.setText(currentBook.author);
        textViewReleaseYear.setText("Godina izdanja: " + currentBook.releaseYear);
        textViewPageCount.setText("Broj strana: " + currentBook.pageCount);
        textViewBookDescription.setText(currentBook.description);

        String PACKAGE_NAME = getContext().getPackageName();
        int imageResource = getResources().getIdentifier(
                PACKAGE_NAME+":drawable/"+currentBook.img, null, null);
        imageViewBookImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), imageResource));
    }


    private void getViews(View layout) {
        imageViewBars = layout.findViewById(R.id.imageViewBars);
        imageViewLogo = layout.findViewById(R.id.imageViewLogo);
        imageViewBookImage = layout.findViewById(R.id.imageViewBookImage);
        imageViewInfo = layout.findViewById(R.id.imageViewInfo);
        imageViewReviews = layout.findViewById(R.id.imageViewReviews);
        imageViewShare = layout.findViewById(R.id.imageViewShare);
        textViewTitle = layout.findViewById(R.id.textViewTitle);
        textViewAuthor = layout.findViewById(R.id.textViewAuthor);
        textViewReleaseYear = layout.findViewById(R.id.textViewReleaseYear);
        textViewPageCount = layout.findViewById(R.id.textViewPageCount);
        textViewBookDescription = layout.findViewById(R.id.textViewBookDescription);
        constraintLayoutDynamicContent = layout.findViewById(R.id.constraintLayoutDynamicContent);
    }

    private void setListeners() {
        imageViewBars.setOnClickListener(view -> {
            navController.navigate(R.id.action_bookDetailsFragment_to_buyerMenuFragment);
        });
        imageViewLogo.setOnClickListener(view -> {
            navController.navigate(R.id.action_bookDetailsFragment_to_buyerLandingFragment);
        });
        imageViewInfo.setOnClickListener(view -> {
            removeDynamicContent();
            loadBookDescription();
        });
        imageViewReviews.setOnClickListener(view -> {
            removeDynamicContent();
            loadReviews();
        });
        imageViewShare.setOnClickListener(view -> {
            removeDynamicContent();
            loadShare();
        });
    }

    private void removeDynamicContent() {
        constraintLayoutDynamicContent.removeAllViews();
    }

    private void loadBookDescription() {
        View bookDescriptionView = inflater.inflate(R.layout.book_description, container, false);
        textViewBookDescription = bookDescriptionView.findViewById(R.id.textViewBookDescription);
        textViewBookDescription.setText(currentBook.description);
        constraintLayoutDynamicContent.addView(bookDescriptionView);
    }

    private void loadReviews() {
        rating = 0;
        // Load existing reviews
        LinearLayout linearLayoutReviews = (LinearLayout) inflater.inflate(R.layout.linear_layout, container, false);

        ImageView[] stars = new ImageView[5];
        for (Review review: currentBook.reviews) {
            View reviewView = inflater.inflate(R.layout.review, container, false);
            LinearLayout linearLayoutStars = reviewView.findViewById(R.id.linearLayoutStars);
            TextView textViewAuthorUsername = reviewView.findViewById(R.id.textViewAuthorUsername);
            TextView textViewComment = reviewView.findViewById(R.id.textViewComment);

            stars[0] = linearLayoutStars.findViewById(R.id.imageViewStar1);
            stars[1] = linearLayoutStars.findViewById(R.id.imageViewStar2);
            stars[2] = linearLayoutStars.findViewById(R.id.imageViewStar3);
            stars[3] = linearLayoutStars.findViewById(R.id.imageViewStar4);
            stars[4] = linearLayoutStars.findViewById(R.id.imageViewStar5);

            for (int i = 0; i < stars.length; i++) {
                if (i >= review.rating) stars[i].setAlpha(0f);
            }

            textViewAuthorUsername.setText(review.username);
            textViewComment.setText(review.comment);

            linearLayoutReviews.addView(reviewView);
        }

        // Load form for creating new review
        View commentFormView = inflater.inflate(R.layout.comment_form, container, false);
        LinearLayout linearLayoutStars = commentFormView.findViewById(R.id.linearLayoutStars);

        stars[0] = linearLayoutStars.findViewById(R.id.imageViewStar1);
        stars[1] = linearLayoutStars.findViewById(R.id.imageViewStar2);
        stars[2] = linearLayoutStars.findViewById(R.id.imageViewStar3);
        stars[3] = linearLayoutStars.findViewById(R.id.imageViewStar4);
        stars[4] = linearLayoutStars.findViewById(R.id.imageViewStar5);

        for (int i = 0; i < stars.length; i++) {
            final int index = i;
            stars[i].setOnClickListener(view -> {
                for (int j = 0; j < stars.length; j++) {
                    stars[j].setAlpha(1f - (j <= index ? 0f : 1f));
                }
                rating = index + 1;
            });
        }
        EditText editTextComment = commentFormView.findViewById(R.id.editTextComment);
        Button buttonSubmit = commentFormView.findViewById(R.id.buttonSubmit);
        TextView textViewError = commentFormView.findViewById(R.id.textViewError);

        // Create a new review
        buttonSubmit.setOnClickListener(view -> {
            if (rating == 0) {
                textViewError.setText("Niste ocenili knjigu.");
                return;
            }
            Data data = dataViewModel.getDataLiveData().getValue();
            String currentUserUsername = data.currentUser.username;

            Review newReview = new Review(currentUserUsername,
                    editTextComment.getText().toString(),
                    rating);
            int removeIndex = -1;

            // Remove old review if it exists
            for (int i = 0; i < currentBook.reviews.size(); i++) {
                if (currentBook.reviews.get(i).username.equals(currentUserUsername)) {
                    removeIndex = i;
                    break;
                }
            }
            if (removeIndex != -1) {
                currentBook.reviews.remove(removeIndex);
            }

            // Add new review and update data
            currentBook.reviews.add(newReview);
            dataViewModel.setDataLiveData(data);

            removeDynamicContent();
            loadReviews();
        });

        linearLayoutReviews.addView(commentFormView);

        constraintLayoutDynamicContent.addView(linearLayoutReviews);
    }

    private void loadShare() {
        Data data = dataViewModel.getDataLiveData().getValue();
        LinearLayout linearLayoutRecommendations = (LinearLayout) inflater.inflate(R.layout.linear_layout, container, false);

        for (User u: data.users) {
            if (u.id == data.currentUser.id) continue;
            View recommendationFormView = inflater.inflate(R.layout.recommendation_form, container, false);
            ConstraintLayout constraintLayoutRecommendationForm = recommendationFormView.findViewById(R.id.constraintLayoutRecommendationForm);
            TextView textViewUsername = recommendationFormView.findViewById(R.id.textViewUsername);
            textViewUsername.setText(u.username);

            boolean alreadyRecommended = false;
            for (Recommendation recommendation: data.recommendations) {
                if (recommendation.bookId == currentBookId &&
                        recommendation.from.equals(data.currentUser.username) &&
                        recommendation.to.equals(u.username)) {
                    alreadyRecommended = true;
                    break;
                }
            }

            if (!alreadyRecommended) {
                // Make success image invisible.
                ImageView imageViewSuccess = constraintLayoutRecommendationForm.findViewById(R.id.imageViewSuccess);
                imageViewSuccess.setAlpha(0f);

                // Load button.
                Button buttonRecommend = new Button(getContext());
                buttonRecommend.setBackgroundColor(getResources().getColor(R.color.blue));
                buttonRecommend.setText("PREPORUČI");
                buttonRecommend.setTextColor(getResources().getColor(R.color.white));

                buttonRecommend.setOnClickListener(view -> {
                    // Add new recommendation.
                    Recommendation newRecommendation = new Recommendation(
                            data.currentUser.username,
                            u.username,
                            currentBookId);
                    data.recommendations.add(newRecommendation);
                    dataViewModel.setDataLiveData(data);

                    // Refresh view.
                    removeDynamicContent();
                    loadShare();
                });

                constraintLayoutRecommendationForm.addView(buttonRecommend);
            }

            linearLayoutRecommendations.addView(recommendationFormView);
        }
        constraintLayoutDynamicContent.addView(linearLayoutRecommendations);
    }
}