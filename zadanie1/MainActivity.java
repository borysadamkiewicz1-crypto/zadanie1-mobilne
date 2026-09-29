package com.example.myapplication;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText imie;
    private EditText nazwisko;
    private EditText email;
    private EditText haslo;
    private TextView komunikat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imie = findViewById(R.id.imie);
        nazwisko = findViewById(R.id.nazwisko);
        email = findViewById(R.id.email);
        haslo = findViewById(R.id.haslo);
        komunikat = findViewById(R.id.komunikat);

        Button przycisk = findViewById(R.id.rejestracja);

        przycisk.setOnClickListener(view -> sprawdzFormularz());
    }

    private void sprawdzFormularz() {

        String imieWartosc = imie.getText().toString().trim();
        String nazwiskoWartosc = nazwisko.getText().toString().trim();
        String emailWartosc = email.getText().toString().trim();
        String hasloWartosc = haslo.getText().toString();

        if (imieWartosc.isEmpty() || nazwiskoWartosc.isEmpty()
                || emailWartosc.isEmpty() || hasloWartosc.isEmpty()) {

            pokazKomunikat("Uzupełnij wszystkie pola");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailWartosc).matches()) {
            pokazKomunikat("Podaj poprawny adres email");
            return;
        }

        if (!poprawneHaslo(hasloWartosc)) {
            return;
        }

        pokazKomunikat("Dane są poprawne");
    }

    private boolean poprawneHaslo(String haslo) {

        boolean wielkaLitera = false;
        boolean malaLitera = false;
        boolean znakSpecjalny = false;

        for (char znak : haslo.toCharArray()) {

            if (Character.isUpperCase(znak)) {
                wielkaLitera = true;
            }

            if (Character.isLowerCase(znak)) {
                malaLitera = true;
            }

            if (!Character.isLetterOrDigit(znak)) {
                znakSpecjalny = true;
            }
        }

        if (haslo.length() < 8) {
            pokazKomunikat("Hasło musi mieć co najmniej 8 znaków");
            return false;
        }

        if (!wielkaLitera) {
            pokazKomunikat("Hasło musi zawierać dużą literę");
            return false;
        }

        if (!malaLitera) {
            pokazKomunikat("Hasło musi zawierać małą literę");
            return false;
        }

        if (!znakSpecjalny) {
            pokazKomunikat("Hasło musi zawierać znak specjalny");
            return false;
        }

        return true;
    }

    private void pokazKomunikat(String tekst) {
        komunikat.setText(tekst);
    }
}