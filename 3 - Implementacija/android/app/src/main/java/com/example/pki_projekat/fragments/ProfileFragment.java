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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pki_projekat.R;
import com.example.pki_projekat.model.Data;
import com.example.pki_projekat.model.User;
import com.example.pki_projekat.view_models.DataViewModel;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;


public class ProfileFragment extends Fragment {

    private NavController navController;
    private TextView textViewError;
    private ImageView imageViewBars, imageViewLogo;
    private EditText editTextName, editTextSurname, editTextPhoneNumber, editTextAddress, editTextNewPassword1, editTextNewPassword2;
    private CheckBox checkboxChangePassword;
    private Button buttonSave;
    private DataViewModel dataViewModel;
    private User currentUser;
    private ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        dataViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
        currentUser = dataViewModel.getDataLiveData().getValue().currentUser;

        getViews(view);
        populateData();
        setListeners();
        return view;
    }

    private void getViews(View view) {
        textViewError = view.findViewById(R.id.textViewError);
        editTextName = view.findViewById(R.id.editTextName);
        editTextSurname = view.findViewById(R.id.editTextSurname);
        editTextPhoneNumber = view.findViewById(R.id.editTextPhoneNumber);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextNewPassword1 = view.findViewById(R.id.editTextNewPassword1);
        editTextNewPassword2 = view.findViewById(R.id.editTextNewPassword2);
        checkboxChangePassword = view.findViewById(R.id.checkboxChangePassword);
        buttonSave = view.findViewById(R.id.buttonSave);
        imageViewLogo = view.findViewById(R.id.imageViewLogo);
        imageViewBars = view.findViewById(R.id.imageViewBars);
        scrollView = view.findViewById(R.id.scrollView);
    }

    private void populateData() {
        editTextName.setText(currentUser.name);
        editTextSurname.setText(currentUser.surname);
        editTextPhoneNumber.setText(currentUser.phoneNumber);
        editTextAddress.setText(currentUser.address);
    }

    private void setListeners() {
        imageViewLogo.setOnClickListener(view -> {
            navController.navigate(R.id.action_profileFragment_to_buyerLandingFragment);
        });

        imageViewBars.setOnClickListener(view -> {
            navController.navigate(R.id.action_profileFragment_to_buyerMenuFragment);
        });

        checkboxChangePassword.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                editTextNewPassword1.setFocusableInTouchMode(true);
                editTextNewPassword2.setFocusableInTouchMode(true);
            } else {
                editTextNewPassword1.setFocusable(false);
                editTextNewPassword2.setFocusable(false);
            }
        });

        buttonSave.setOnClickListener(view -> {
            String name = editTextName.getText().toString();
            String surname = editTextSurname.getText().toString();
            String address = editTextPhoneNumber.getText().toString();
            String phoneNumber = editTextAddress.getText().toString();

            boolean changePassword = checkboxChangePassword.isChecked();
            String newPassword1 = editTextNewPassword1.getText().toString();
            String newPassword2 = editTextNewPassword2.getText().toString();

            boolean errorsExist = false;
            errorsExist = isEmpty(name) || isEmpty(surname) || isEmpty(address) || isEmpty(phoneNumber);
            if (changePassword) errorsExist = errorsExist || isEmpty(newPassword1) || isEmpty(newPassword2);

            if (errorsExist) {
                textViewError.setText("Unesite sve podatke.");
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                return;
            }

            if (newPassword1.equals(newPassword2) == false) {
                textViewError.setText("Lozinke se ne poklapaju.");
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                return;
            }

            // Update current user.
            currentUser.name = name;
            currentUser.surname = surname;
            currentUser.address = address;
            currentUser.phoneNumber = phoneNumber;
            if (changePassword) currentUser.password = newPassword1;

            // Update all users.
            Data data = dataViewModel.getDataLiveData().getValue();
            int index = 0;
            for (User u : data.users) {
                if (u.id == currentUser.id) break;
                index++;
            }
            data.users.remove(index);
            data.users.add(currentUser);

            Toast.makeText(getContext(), "Uspešno ste sačuvali izmene", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.action_profileFragment_to_buyerLandingFragment);
        });
    }

    private boolean isEmpty(String str) {
        return "".equals(str);
    }
}