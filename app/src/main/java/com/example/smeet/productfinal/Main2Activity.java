package com.example.smeet.productfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextProduct, editTextCompany, editTextComplaint;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextProduct = (EditText) findViewById(R.id.editTextProduct);
        editTextCompany = (EditText) findViewById(R.id.editTextCompany);
        editTextComplaint = (EditText) findViewById(R.id.editTextComplaint);

        buttonSubmit = (Button) findViewById(R.id.buttonComplaint);

        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSubmit) {
            String product = "Product Name: " + editTextProduct.getText().toString();
            String company = "Company Name: " + editTextCompany.getText().toString();
            String complaint = "Complaint: " + editTextComplaint.getText().toString();
            String name = "Name: " + SharedPrefManager.getInstance(this).getUser_Name();
            String address = "Address" + SharedPrefManager.getInstance(this).getUser_Address();
            String phone = "Phone: " + SharedPrefManager.getInstance(this).getUser_Phone();


            String finall = product + "\n" + company + "\n" + complaint;


            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, "airsmeet@gmail.com");
            email.putExtra(Intent.EXTRA_TEXT, finall);

            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Choose an Email client :"));


        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.logoutMenu);
        menuItem.setVisible(true);
        MenuItem menuItem1 = menu.findItem(R.id.cartOption);
        menuItem1.setVisible(true);
        MenuItem menuItem2 = menu.findItem(R.id.profileMenu);
        menuItem2.setVisible(true);
        MenuItem menuItem3 = menu.findItem(R.id.homeMenu);
        menuItem3.setVisible(true);
        MenuItem menuItem4 = menu.findItem(R.id.signInMenu);
        menuItem4.setVisible(false);
        MenuItem menuItem5 = menu.findItem(R.id.aboutUsOption);
        menuItem5.setVisible(true);


        MenuItem menuItem6 = menu.findItem(R.id.complaintOption);
        menuItem6.setVisible(false);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signInMenu:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;


            case R.id.homeMenu:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;

            case R.id.aboutUsOption:
                startActivity(new Intent(getApplicationContext(),AboutUsActivity.class));
                break;

            case R.id.profileMenu:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                finish();
                break;


            case R.id.complaintOption:
                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                finish();
                break;

            case R.id.cartOption:
                //startActivity(new Intent(getApplicationContext(),AboutUsActivity.class));
                Toast.makeText(getApplicationContext(),"You Selected cart",Toast.LENGTH_LONG).show();
                break;
            case R.id.logoutMenu:

                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
        }
        return true;
    }
}