package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class detalleContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);


           Bundle extras = getIntent().getExtras();
           String name= extras.getString(getResources().getString(R.string.nameParam));
           String DOB= extras.getString(getResources().getString(R.string.dobParam));
           String phone = extras.getString(getResources().getString(R.string.phoneParam));
           String email= extras.getString(getResources().getString(R.string.emailParam));
           String desc= extras.getString(getResources().getString(R.string.descripcionParam));


        TextView txtName= (TextView) findViewById(R.id.txvNombre);
        TextView txtPhone= (TextView) findViewById(R.id.txvTelefono);
        TextView txtDob= (TextView) findViewById(R.id.txvDob);
        TextView txtEmail= (TextView) findViewById(R.id.txvEmail);
        TextView txtDescription= (TextView) findViewById(R.id.txvDescripcion);


        txtName.setText(name);
        txtDob.setText(DOB);
        txtPhone.setText(phone);
        txtEmail.setText(email);
        txtDescription.setText(desc);


        Button btnEditar= (Button) findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
