package com.example.pki_projekat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.pki_projekat.model.Book;
import com.example.pki_projekat.model.Data;
import com.example.pki_projekat.model.Recommendation;
import com.example.pki_projekat.model.Review;
import com.example.pki_projekat.model.User;
import com.example.pki_projekat.view_models.DataViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        dataViewModel.setDataLiveData(new Data());

        addUsers();
        addBooks();
        addRecommendations();
    }

    private void addRecommendations() {
        Data data = dataViewModel.getDataLiveData().getValue();

        Recommendation recommendation = new Recommendation(
                "mmikic",
                "llazic",
                1
        );

        data.recommendations.add(recommendation);

        dataViewModel.setDataLiveData(data);
    }

    private void addBooks() {
        Data data = dataViewModel.getDataLiveData().getValue();

        Book book1 = new Book(
                1,
                547,
                "Serotonin",
                "Misel Uelbek",
                "1998.",
                "serotonin",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                true,
                new ArrayList<>(
                        Arrays.asList(
                                new Review(
                                        "llazic",
                                        "Odlicna knjiga! Sve preporuke!",
                                        5
                                ),
                                new Review(
                                        "mmikic",
                                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                                        3
                                )
                        )
                )
        );

        data.books.add(book1);

        Book book2 = new Book(
                2,
                547,
                "Da Vincijev kod",
                "Den Braun",
                "1998.",
                "da_vincijev_kod",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                true,
                new ArrayList<>(
                        Arrays.asList(
                                new Review(
                                        "llazic",
                                        "Odlicna knjiga! Sve preporuke!",
                                        5
                                ),
                                new Review(
                                        "mmikic",
                                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                                        3
                                )
                        )
                )
        );

        data.books.add(book2);

        Book book3 = new Book(
                3,
                547,
                "Serotonin",
                "Misel Uelbek",
                "1998.",
                "serotonin",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                true,
                new ArrayList<>(
                        Arrays.asList(
                                new Review(
                                        "llazic",
                                        "Odlicna knjiga! Sve preporuke!",
                                        5
                                ),
                                new Review(
                                        "mmikic",
                                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                                        3
                                )
                        )
                )
        );

        data.books.add(book3);

        Book book4 = new Book(
                4,
                547,
                "Da Vincijev kod",
                "Den Braun",
                "1998.",
                "da_vincijev_kod",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                true,
                new ArrayList<>(
                        Arrays.asList(
                                new Review(
                                        "llazic",
                                        "Odlicna knjiga! Sve preporuke!",
                                        5
                                ),
                                new Review(
                                        "mmikic",
                                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                                        3
                                )
                        )
                )
        );

        data.books.add(book4);

        Book book5 = new Book(
                5,
                547,
                "Serotonin",
                "Misel Uelbek",
                "1998.",
                "serotonin",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                false,
                new ArrayList<>(
                        Arrays.asList(
                                new Review(
                                        "llazic",
                                        "Odlicna knjiga! Sve preporuke!",
                                        5
                                ),
                                new Review(
                                        "mmikic",
                                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                                        3
                                )
                        )
                )
        );

        data.books.add(book5);

        Book book6 = new Book(
                6,
                547,
                "Da Vincijev kod",
                "Den Braun",
                "1998.",
                "da_vincijev_kod",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                false,
                new ArrayList<>(
                        Arrays.asList(
                                new Review(
                                        "llazic",
                                        "Odlicna knjiga! Sve preporuke!",
                                        5
                                ),
                                new Review(
                                        "mmikic",
                                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                                        3
                                )
                        )
                )
        );

        data.books.add(book6);

        dataViewModel.setDataLiveData(data);
    }

    private void addUsers() {
        Data data = dataViewModel.getDataLiveData().getValue();

        User lazar = new User();
        lazar.id = 1;
        lazar.name = "Lazar";
        lazar.surname = "Lazic";
        lazar.address = "Trg Republike 1";
        lazar.phoneNumber = "0612345678";
        lazar.username = "llazic";
        lazar.password = "sifra123";
        lazar.userType = User.UserType.BUYER;

        data.users.add(lazar);

        User mika = new User();
        mika.id = 3;
        mika.name = "Mika";
        mika.surname = "Mikic";
        mika.address = "Trg Republike 1";
        mika.phoneNumber = "0612345678";
        mika.username = "mmikic";
        mika.password = "sifra123";
        mika.userType = User.UserType.BUYER;

        data.users.add(mika);

        dataViewModel.setDataLiveData(data);
    }
}