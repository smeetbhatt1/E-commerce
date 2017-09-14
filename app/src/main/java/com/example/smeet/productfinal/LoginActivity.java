package com.example.smeet.productfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail,editTextPassword;
    TextView textViewClickRegister;
    Button buttonLoggin;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

           if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
            return;
        }

        editTextEmail=(EditText)findViewById(R.id.editEmail);
        editTextPassword=(EditText)findViewById(R.id.editPassword);
        buttonLoggin=(Button)findViewById(R.id.btnLogin);
        buttonLoggin.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        textViewClickRegister=(TextView)findViewById(R.id.clickHereRegister);
        textViewClickRegister.setOnClickListener(this);
    }

    private void userLogin() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(response);
                            if (!object.getBoolean("error")) {
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                        object.getInt("user_id"),
                                        object.getString("user_name"),
                                        object.getString("user_surname"),
                                        object.getString("user_email"),
                                        object.getString("user_phone"),
                                        object.getString("user_address"),
                                        object.getString("user_city"),
                                        object.getString("user_state")
                                );
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));


                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_email", email);
                params.put("user_password", password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v==buttonLoggin)
            userLogin();
        if(v==textViewClickRegister)
            startActivity(new Intent(this,RegisterActivity.class));
    }
}
