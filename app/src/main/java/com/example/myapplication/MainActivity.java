package com.example.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout til_name, til_phone, til_email, til_description;
    private TextInputEditText tie_name, tie_phone, tie_email, tie_description, tie_DOB;
    private Button boton;

    Calendar calendar;
    Button btnSubmit;
    ArrayList<Contacto> contactos;
    String selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactos= new ArrayList<Contacto>();

        til_name =(TextInputLayout) findViewById(R.id.tilNombre);
        til_phone=(TextInputLayout) findViewById(R.id.tilTelefono);
        til_email=(TextInputLayout) findViewById(R.id.tilEmail);
        til_description=(TextInputLayout) findViewById(R.id.tilDescripcion);

        tie_name=(TextInputEditText) findViewById(R.id.tieNombre);
        tie_phone=(TextInputEditText) findViewById(R.id.tieTelefono);
        tie_email=(TextInputEditText) findViewById(R.id.tieEmail);
        tie_description=(TextInputEditText) findViewById(R.id.tieDescripcion);

        tie_DOB = findViewById(R.id.tieDOB);

        tie_DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        tie_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((isValidFormat("email", String.valueOf(s)))) {
                    tie_email.setError(null);
                } else {
                    tie_email.setError(getResources().getString(R.string.validateEmail));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSubmit=(Button) findViewById(R.id.btnSiguiente);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_form();
            }
        });
    }


    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate =  String.valueOf(day)+"/"+ String.valueOf(month+1) + "/" +String.valueOf(year);
                tie_DOB.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validate(TextInputEditText nameInput, String messageError){
        if(nameInput.getText().toString().isEmpty()){
            nameInput.setError(messageError);
            requestFocus(nameInput);
            return false;
        }
        return true;
    }

    private boolean isValidFormat(String type, String data){

        boolean answer=false;
        switch (type){
            case "phone": answer=Patterns.PHONE.matcher(data).matches();break;
            case "email": answer=Patterns.EMAIL_ADDRESS.matcher(data).matches();break;
        }

        return answer;
    }

    private boolean validateInputs(String nameInput){
        boolean answer=false;
        switch (nameInput){
            case "nombre": answer=  validate(tie_name, getResources().getString(R.string.validateNombre));break;
            case "fecha": answer=  validate(tie_DOB, getResources().getString(R.string.validateFecha));break;
            case "telefono": answer= validate(tie_phone, getResources().getString(R.string.validateTelefono));break;
            case "email": answer= validate(tie_email, getResources().getString(R.string.validateEmail));break;
            case "descripcion": answer= validate(tie_description, getResources().getString( R.string.validateDescripcion));break;
            default:
                throw new IllegalStateException("Unexpected value: " + nameInput);
        }

        return answer;
    }

    public void submit_form(){
        if( (validateInputs("nombre"))
                && (validateInputs("fecha"))
                && (validateInputs("telefono"))
                && (validateInputs("email"))
                && (validateInputs("descripcion"))
                && isValidFormat("email", tie_email.getText().toString())
        ){
            contactos.add(new Contacto(tie_name.getText().toString(), tie_email.getText().toString(),
                    tie_phone.getText().toString(), tie_description.getText().toString()
            ,tie_DOB.getText().toString()));


            int index=   contactos.size()-1;
            Intent intent = new Intent(MainActivity.this, detalleContacto.class);
            intent.putExtra(getResources().getString(R.string.nameParam), contactos.get( index).getName());
            intent.putExtra(getResources().getString(R.string.dobParam),contactos.get(index).getDob());
            intent.putExtra(getResources().getString(R.string.phoneParam), contactos.get(index).getPhone());
            intent.putExtra(getResources().getString(R.string.emailParam), contactos.get(index).getEmail());
            intent.putExtra(getResources().getString(R.string.descripcionParam), contactos.get(index).getDescription());
            startActivity(intent);
        }
    }

}
