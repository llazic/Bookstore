package com.example.pki_projekat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pki_projekat.R;
import com.example.pki_projekat.model.Data;
import com.example.pki_projekat.model.User;
import com.example.pki_projekat.view_models.DataViewModel;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private NavController navController;
    private EditText editTextUsername, editTextPassword;
    private TextView textViewError;
    private Button buttonLogin;
    private DataViewModel dataViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        dataViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);

        getViews(view);
        setListeners();

        return view;
    }

    private void getViews(View view) {
        this.editTextUsername = view.findViewById(R.id.editTextUsername);
        this.editTextPassword = view.findViewById(R.id.editTextPassword);
        this.buttonLogin = view.findViewById(R.id.buttonLogin);
        this.textViewError = view.findViewById(R.id.textViewError);
    }

    private void setListeners() {
        buttonLogin.setOnClickListener(view -> {
            Data data = dataViewModel.getDataLiveData().getValue();

            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            for (User u : data.users) {
                if (u.username.equals(username) && u.password.equals(password)) {
                    dataViewModel.getDataLiveData().getValue().currentUser = u;
                    navController.navigate(R.id.action_loginFragment_to_buyerLandingFragment);
                    return;
                }
            }

            textViewError.setText("Neispravni kredencijali");
        });
    }

}