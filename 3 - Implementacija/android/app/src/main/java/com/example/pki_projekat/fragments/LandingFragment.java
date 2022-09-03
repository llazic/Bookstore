package com.example.pki_projekat.fragments;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pki_projekat.R;
import com.example.pki_projekat.model.Book;
import com.example.pki_projekat.model.Data;
import com.example.pki_projekat.view_models.DataViewModel;

import java.util.ArrayList;
import java.util.List;


public class LandingFragment extends Fragment {

    private LayoutInflater inflater;
    private ViewGroup container;

    private ImageView imageViewBars;
    private GridLayout gridLayoutPromoBooks, gridLayoutBooksNotOnPromo;
    private NavController navController;
    private DataViewModel dataViewModel;
    private String PACKAGE_NAME;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;

        View view = this.inflater.inflate(R.layout.fragment_buyer_landing, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        dataViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
        getViews(view);
        setMenuListener();

        inflateAllBooksAndSetListeners(inflater, container);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void inflateAllBooksAndSetListeners(LayoutInflater inflater, ViewGroup container) {
        Data data = dataViewModel.getDataLiveData().getValue();
        ArrayList<Book> allBooks = data.books;
        ArrayList<Book> booksOnPromo = new ArrayList<>();
        ArrayList<Book> booksNotOnPromo = new ArrayList<>();

        PACKAGE_NAME = getContext().getPackageName();

        allBooks.forEach(book -> {
            if (book.promo) booksOnPromo.add(book);
            else booksNotOnPromo.add(book);
        });

        inflateBooksGenericAndSetListeners(booksOnPromo, gridLayoutPromoBooks);
        inflateBooksGenericAndSetListeners(booksNotOnPromo, gridLayoutBooksNotOnPromo);
    }

    private void inflateBooksGenericAndSetListeners(List<Book> books, GridLayout gridLayoutForBooks) {
        final int NUM_COLUMNS = 2;
        int numRowsBooksOnPromo = books.size() / NUM_COLUMNS + 1;

        gridLayoutForBooks.removeAllViews();
        gridLayoutForBooks.setColumnCount(NUM_COLUMNS);
        gridLayoutForBooks.setRowCount(numRowsBooksOnPromo);

        int column = 0, row = 0;
        for (Book book: books) {
            View bookView = inflater.inflate(R.layout.book, container, false);
            ImageView imageViewBookImage = bookView.findViewById(R.id.imageViewBookImage);
            TextView textViewBookTitle = bookView.findViewById(R.id.textViewTitle);
            TextView textViewBookAuthor = bookView.findViewById(R.id.textViewAuthor);

            textViewBookTitle.setText(book.name);
            textViewBookAuthor.setText(book.author);

            int imageResource = getResources().getIdentifier(
                    PACKAGE_NAME+":drawable/"+book.img, null, null);
            imageViewBookImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), imageResource));

            GridLayout.Spec rowSpan = GridLayout.spec(row, 1);
            GridLayout.Spec colspan = GridLayout.spec(column, 1);
            GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams(rowSpan, colspan);
            gridLayoutForBooks.addView(bookView, gridParams);

            bookView.setOnClickListener(view -> {
                Data data = dataViewModel.getDataLiveData().getValue();
                data.currentBookId = book.id;
                dataViewModel.setDataLiveData(data);

                navController.navigate(R.id.action_buyerLandingFragment_to_bookDetailsFragment);
            });

            column = (column + 1) % 2;
            row = row + (column == 0 ? 1 : 0);
        }
    }

    private void getViews(View view) {
        imageViewBars = view.findViewById(R.id.imageViewBars);
        gridLayoutPromoBooks = view.findViewById(R.id.gridLayoutPromoBooks);
        gridLayoutBooksNotOnPromo = view.findViewById(R.id.gridLayoutBooksNotOnPromo);
    }

    private void setMenuListener() {
        imageViewBars.setOnClickListener(view -> {
            navController.navigate(R.id.action_buyerLandingFragment_to_buyerMenuFragment);
        });
    }
}