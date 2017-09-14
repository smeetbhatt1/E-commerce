package com.example.smeet.productfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextName,editTextSurname,editTextEmail,editTextPassword,editTextConfirmPassword,
            editTextPhone,editTextAddress,editTextCity,editTextState;
    Button buttonClick;
    ProgressDialog progressDialog;
    TextView textViewLoginClick;
    TextInputLayout textInputLayoutName,textInputLayoutSurname,textInputLayoutEmail,textInputLayoutPassword,textInputLayoutConfirmPassword,
            textInputLayoutPhone,textInputLayoutAddress,textInputLayoutCity,textInputLayoutState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
           finish();
           startActivity(new Intent(this,ProfileActivity.class));
            return;
        }
        editTextName=(EditText)findViewById(R.id.editName);
        editTextSurname=(EditText)findViewById(R.id.editSurname);
        editTextEmail=(EditText)findViewById(R.id.editEmail);
        editTextPassword=(EditText)findViewById(R.id.editPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.editConfirmPassword);
        editTextPhone=(EditText)findViewById(R.id.editPhone);
        editTextAddress=(EditText)findViewById(R.id.editAddress);
        editTextCity=(EditText)findViewById(R.id.editCity);
        editTextState=(EditText)findViewById(R.id.editState);

        progressDialog=new ProgressDialog(this);

        textViewLoginClick=(TextView)findViewById(R.id.clickHereLoginn);
        textViewLoginClick.setOnClickListener(this);

        buttonClick=(Button)findViewById(R.id.btn_register);
        buttonClick.setOnClickListener(this);

        textInputLayoutName=(TextInputLayout)findViewById(R.id.layout_name);
        textInputLayoutSurname=(TextInputLayout)findViewById(R.id.layout_surname);
        textInputLayoutEmail=(TextInputLayout)findViewById(R.id.layout_email);
        textInputLayoutPassword=(TextInputLayout)findViewById(R.id.layout_password);
        textInputLayoutConfirmPassword=(TextInputLayout)findViewById(R.id.layout_confirmPassword);
        textInputLayoutPhone=(TextInputLayout)findViewById(R.id.layout_phone);
        textInputLayoutAddress=(TextInputLayout)findViewById(R.id.layout_address);
        textInputLayoutCity=(TextInputLayout)findViewById(R.id.layout_city);
        textInputLayoutState=(TextInputLayout)findViewById(R.id.layout_State);
    }

    @Override
    public void onClick(View v) {
        if(v==buttonClick)
        Validate();
        if (v==textViewLoginClick)
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }

    private void Validate() {
        if(!validateName()){
            return;
        }
        if(!validateSurname()){
            return;
        }
        if(!validateEmail()){
            return;
        }
        if(!validatePassword()){
            return;
        }
        if(!validateConfirmPassword()){
            return;
        }
        if(!validatePhone()){
            return;
        }
        if(!validateAddress()){
            return;
        }
        if(!validateCity()){
            return;
        }
        if(!validateState()){
            return;
        }
        registerUser();
    }

    private void registerUser() {
        final String user_name=editTextName.getText().toString().trim().toLowerCase();
        final String user_surname=editTextSurname.getText().toString().trim().toLowerCase();
        final String user_email=editTextEmail.getText().toString().trim();
        final String user_password=editTextPassword.getText().toString().trim();
        final String user_phone=editTextPhone.getText().toString().trim();
        final String user_address=editTextAddress.getText().toString().trim();
        final String user_city=editTextCity.getText().toString().trim().toLowerCase();
        final String user_state=editTextState.getText().toString().trim().toLowerCase();

        progressDialog.setMessage("Registering User");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                Constants.URL_REGISTRATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject object=new JSONObject(response);
                            Toast.makeText(getApplicationContext(),object.getString("message"),Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("user_name",user_name);
                params.put("user_surname",user_surname);
                params.put("user_email",user_email);
                params.put("user_password",user_password);
                params.put("user_phone",user_phone);
                params.put("user_address",user_address);
                params.put("user_city",user_city);
                params.put("user_state",user_state);
                return params;

            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    private boolean validateName() {
        String name=editTextName.getText().toString().trim();
        if (name.isEmpty()) {
            textInputLayoutName.setError(getString(R.string.missing));
            requestFocus(editTextName);
            return false;}
        else if (!name.matches("[a-zA-Z]+$")){
            textInputLayoutName.setError(getString(R.string.invalid_input));
            requestFocus(editTextName);
            return false;
        }
        else {
            textInputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateSurname() {
        String name=editTextSurname.getText().toString().trim();
        if (name.isEmpty()) {
            textInputLayoutSurname.setError(getString(R.string.missing));
             requestFocus(editTextSurname);
            return false;}
        else if (!name.matches("[a-zA-Z]+$")){
            textInputLayoutSurname.setError(getString(R.string.invalid_input));
            requestFocus(editTextSurname);
            return false;
        }
        else {
            textInputLayoutSurname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail(){
        String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {
            textInputLayoutEmail.setError(getString(R.string.missing));
            requestFocus(editTextEmail);
            return false;
        }
        else if(!isValidEmail(email)) {
            textInputLayoutEmail.setError(getString(R.string.invalid_input));
            requestFocus(editTextEmail);
            return false;
        }
     else   {
            textInputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword(){
        String pass=editTextPassword.getText().toString().trim();
        if (pass.isEmpty()) {
            textInputLayoutPassword.setError(getString(R.string.missing));
            requestFocus(editTextPassword);
            return false;
        }
        else if(pass.length()<8){
            textInputLayoutPassword.setError(getString(R.string.invalid_password));
            requestFocus(editTextPassword);
            return false;
        }
        else {
            textInputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateConfirmPassword() {
        String confirmPass=editTextConfirmPassword.getText().toString().trim();
        String pass=editTextPassword.getText().toString().trim();
       if(confirmPass.isEmpty()){
            textInputLayoutConfirmPassword.setError(getString(R.string.missing));
            requestFocus(editTextConfirmPassword);
            return false;
        }
        else  if(!pass.equals(confirmPass)){
           textInputLayoutConfirmPassword.setError(getString(R.string.pass_do_not_match));
           requestFocus(editTextConfirmPassword);
           return false;
       }

       else {
            textInputLayoutConfirmPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePhone() {
        String phone=editTextPhone.getText().toString().trim();
        if(phone.isEmpty()){
            textInputLayoutPhone.setError(getString(R.string.missing));
            requestFocus(editTextPhone);
            return false;
        }
        else if(phone.length()!=10){
            textInputLayoutPhone.setError(getString(R.string.invalid_input));
            requestFocus(editTextPhone);
            return false;
        }
        else{
            textInputLayoutPhone.setErrorEnabled(false);
        }
        return true;

    }

    private boolean validateAddress() {
        String address=editTextAddress.getText().toString().trim();

        if(address.isEmpty()){
            textInputLayoutAddress.setError(getString(R.string.missing));
            requestFocus(editTextAddress);
            return false;
        }
        else  if(!(address.length()>10))
        {
            textInputLayoutAddress.setError(getString(R.string.invalid_input));
            requestFocus(editTextAddress);
            return false;
        }
        else{
            textInputLayoutAddress.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCity() {
        String city=editTextCity.getText().toString().trim();
        if (city.isEmpty()) {
            textInputLayoutCity.setError(getString(R.string.missing));
             requestFocus(editTextCity);
            return false;}
        else if(!city.matches("[a-zA-Z]+$"))
        {
            textInputLayoutCity.setError(getString(R.string.invalid_input));
            requestFocus(editTextCity);
            return false;
        }
        else {
            textInputLayoutCity.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateState() {
            String state=editTextState.getText().toString().trim();
        if (state.isEmpty()) {
            textInputLayoutState.setError(getString(R.string.missing));
             requestFocus(editTextState);
            return false;}
        else if (!state.matches("[a-zA-Z]+$")){
            textInputLayoutState.setError(getString(R.string.invalid_input));
            requestFocus(editTextState);
            return false;
        }
        else {
            textInputLayoutState.setErrorEnabled(false);
        }

        return true;
    }

   private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}




















