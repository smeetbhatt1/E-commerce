package com.example.smeet.productfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView ivImage;
    TextView tvName,tvPrice,tvQuantity,tvKeyword,tvDescription,textView;
    Button buttonBuyNow,buttonAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ivImage=(ImageView)findViewById(R.id.ivImage);
        tvName=(TextView) findViewById(R.id.tvName);
        tvPrice=(TextView) findViewById(R.id.tvPrice);
//        tvQuantity=(TextView) findViewById(R.id.tvQuantity);
//        tvKeyword=(TextView) findViewById(R.id.tvKeyword);
        tvDescription=(TextView) findViewById(R.id.tvDescription);

        textView=(TextView) findViewById(R.id.textView);

        buttonAddToCart=(Button)findViewById(R.id.btAddToCart);
        buttonBuyNow=(Button)findViewById(R.id.btBuyNow);
        buttonBuyNow.setOnClickListener(this);
        buttonAddToCart.setOnClickListener(this);




        if(getIntent().getSerializableExtra("product") !=null){
            Product product = (Product)getIntent().getSerializableExtra("product");
            String fullUrl = Constants.URL+product.p_image;
            Picasso.with(this)
                    .load(fullUrl)
                    .placeholder(R.drawable.sample)
                    .error(android.R.drawable.stat_notify_error)
                    .into(ivImage);

            tvName.setText(product.p_title);
            tvPrice.setText("Rs."+product.p_price);
//            tvQuantity.setText(product.p_quantity);
//            tvKeyword.setText("Rs."+product.p_keyword);
           tvDescription.setText(product.p_description);
//         //   tvPrice.setText("Rs."+product.p_price);
            textView.setText(product.p_warantry);
        }
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu );

        if(!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
            MenuItem menuItem = menu.findItem(R.id.logoutMenu);
            menuItem.setVisible(false);
            MenuItem menuItem1 = menu.findItem(R.id.cartOption);
            menuItem1.setVisible(false);
            MenuItem menuItem2 = menu.findItem(R.id.profileMenu);
            menuItem2.setVisible(false);
            MenuItem menuItem3 = menu.findItem(R.id.homeMenu);
            menuItem3.setVisible(true);
            MenuItem menuItem4 = menu.findItem(R.id.signInMenu);
            menuItem4.setVisible(true);
            MenuItem menuItem5 = menu.findItem(R.id.aboutUsOption);
            menuItem5.setVisible(true);
            MenuItem menuItem6 = menu.findItem(R.id.complaintOption);
            menuItem6.setVisible(false);
        }
        else{
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
            menuItem6.setVisible(true);

        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signInMenu:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;


            case R.id.complaintOption:
                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                break;


            case R.id.aboutUsOption:
                startActivity(new Intent(getApplicationContext(),AboutUsActivity.class));
                break;
            case R.id.homeMenu:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
            case R.id.profileMenu:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
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







    @Override
    public void onClick(View v) {
        if(v==buttonAddToCart)
            Toast.makeText(getApplicationContext(),"Add To Cart Selected",Toast.LENGTH_SHORT).show();
        if(v==buttonBuyNow)
            Toast.makeText(getApplicationContext(),"Buy Now Selected",Toast.LENGTH_SHORT).show();
    }
}
