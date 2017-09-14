package com.example.smeet.productfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String TAG="MainActivity";
    RecyclerView rvItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvItem=(RecyclerView)findViewById(R.id.rvItem);
        rvItem.setHasFixedSize(true);

       // StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        LinearLayoutManager manager=new LinearLayoutManager(this);


        rvItem.setLayoutManager(manager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,manager.getOrientation());
        rvItem.addItemDecoration(dividerItemDecoration);

        String url= Constants.URL_SHOW_PRODUCT;
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                         Log.d(TAG, response);

                        ArrayList<Product> productList= new JsonConverter<Product>().
                                toArrayList(response,Product.class);

                        ProductAdapter productAdapter=new ProductAdapter(getApplicationContext(), productList);

                        rvItem.setAdapter(productAdapter);



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            Log.d(TAG,error.toString());
                            Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu );


        if(!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
            MenuItem menuItem = menu.findItem(R.id.logoutMenu);
            menuItem.setVisible(false);
            MenuItem menuItem1 = menu.findItem(R.id.cartOption);
            menuItem1.setVisible(false);
            MenuItem menuItem2 = menu.findItem(R.id.profileMenu);
            menuItem2.setVisible(false);
            MenuItem menuItem3 = menu.findItem(R.id.homeMenu);
            menuItem3.setVisible(false);
            MenuItem menuItem4 = menu.findItem(R.id.signInMenu);
            menuItem4.setVisible(true);
            MenuItem menuItem5 = menu.findItem(R.id.aboutUsOption);
            menuItem5.setVisible(true);
            MenuItem menuItem6 = menu.findItem(R.id.complaintOption);
            menuItem6.setVisible(false);
        }
        else{
            MenuItem menuItem= menu.findItem(R.id.logoutMenu);
            menuItem.setVisible(true);
            MenuItem menuItem1=menu.findItem(R.id.cartOption);
            menuItem1.setVisible(true);
            MenuItem menuItem2=menu.findItem(R.id.profileMenu);
            menuItem2.setVisible(true);
            MenuItem menuItem3 = menu.findItem(R.id.homeMenu);
            menuItem3.setVisible(false);
            MenuItem menuItem4=menu.findItem(R.id.aboutUsOption);
            menuItem4.setVisible(true);
            MenuItem menuItem5=menu.findItem(R.id.signInMenu);
            menuItem5.setVisible(false);
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

            case R.id.profileMenu:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
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




//       private ArrayList<Item> generatedDummy(){
//        ArrayList<Item> list =new ArrayList<>();
//        for(int i=0;i<50;i++) {
//            if (i % 3 == 0) {
//                Item item = new Item();
//                item.id = i;
//                item.text = "Desktop " + i;
//                item.img = "https://pisces.bbystatic.com/BestBuy_US/store/ee/2015/com/pm/nav_desktops_1115.jpg";
//                list.add(item);
//
//            } else if (i % 7 == 0) {
//                Item item = new Item();
//                item.id = i;
//                item.text = "Laptop" + i;
//                item.img = "http://core0.staticworld.net/images/article/2015/02/hp-spectre-x360_beauty-100570598-orig.jpg";
//                list.add(item);
//            } else if (i % 11 == 0) {
//                Item item = new Item();
//                item.id = i;
//                item.text = "Monitor " + i;
//                item.img = "http://img.bbystatic.com/BestBuy_US/images/products/5029/5029700_rd.jpg";
//                list.add(item);
//            } else {
//                Item item = new Item();
//                item.id = i;
//                item.text = "Mouse " + i;
//                item.img = "https://i.kinja-img.com/gawker-media/image/upload/s--Jo7sinAv--/c_scale,f_auto,fl_progressive,q_80,w_800/aoj4ajmkg11pq7jdwkm5.jpg";
//                list.add(item);
//            }
//        }
//        return list;
//    }
}
