package com.example.smeet.productfinal;

import android.content.Intent;
import android.view.MenuItem;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    TextView textViewName, textViewSurname, textViewEmail, textViewPhone, textViewAddress, textViewCity, textViewState;
    Button  buttonChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewSurname = (TextView) findViewById(R.id.textViewSurname);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewAddress = (TextView) findViewById(R.id.textViewAddress);
        textViewPhone = (TextView) findViewById(R.id.textViewPhone);
        textViewCity = (TextView) findViewById(R.id.textViewCity);
        textViewState = (TextView) findViewById(R.id.textViewState);

        buttonChange=(Button)findViewById(R.id.btChange);

        textViewName.setText(SharedPrefManager.getInstance(this).getUser_Name());
        textViewSurname.setText(SharedPrefManager.getInstance(this).getUser_Surname());
        textViewEmail.setText(SharedPrefManager.getInstance(this).getUser_Email());
        textViewPhone.setText(SharedPrefManager.getInstance(this).getUser_Phone());
        textViewAddress.setText(SharedPrefManager.getInstance(this).getUser_Address());
        textViewCity.setText(SharedPrefManager.getInstance(this).getUser_City());
        textViewState.setText(SharedPrefManager.getInstance(this).getUser_State());

    }





    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu );

        MenuItem menuItem = menu.findItem(R.id.signInMenu);
        menuItem.setVisible(false);
        MenuItem menuItem1 = menu.findItem(R.id.cartOption);
        menuItem1.setVisible(true);
        MenuItem menuItem2 = menu.findItem(R.id.logoutMenu);
        menuItem2.setVisible(true);
        MenuItem menuItem3 = menu.findItem(R.id.homeMenu);
        menuItem3.setVisible(true);
        MenuItem menuItem4 = menu.findItem(R.id.aboutUsOption);
        menuItem4.setVisible(true);
        MenuItem menuItem5 = menu.findItem(R.id.profileMenu);
        menuItem5.setVisible(false);

        MenuItem menuItem6 = menu.findItem(R.id.complaintOption);
        menuItem6.setVisible(true);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenu:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this,MainActivity.class));
                break;


            case R.id.complaintOption:

                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                break;


            case R.id.homeMenu:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;


            case R.id.signInMenu:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;

            case R.id.aboutUsOption:
                startActivity(new Intent(getApplicationContext(),AboutUsActivity.class));
                break;

            case R.id.profileMenu:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                break;

            case R.id.cartOption:
                //startActivity(new Intent(getApplicationContext(),AboutUsActivity.class));
                Toast.makeText(getApplicationContext(),"You Selected cart",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }


    public void onClick(View v) {
        if(v==buttonChange)
        Toast.makeText(getApplicationContext(),"Successfully Changed",Toast.LENGTH_SHORT).show();
    }
}
