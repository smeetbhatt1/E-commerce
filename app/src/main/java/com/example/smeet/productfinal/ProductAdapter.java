package com.example.smeet.productfinal;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by smeet on 4/30/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products){
        this.context=context;
        this.products=products;

    }
    View view;

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
         view = inflater.from(parent.getContext()).inflate(R.layout.product_carview_layout,parent,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);

        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Product selectedProduct= products.get(position);
        holder.tvName.setText(selectedProduct.p_title);
        holder.tvPrice.setText("Rs."+selectedProduct.p_price);
//        holder.tvQuantity.setText(selectedProduct.p_quantity);
//        holder.tvKeyword.setText(selectedProduct.p_quantity);
//        holder.tvDescription.setText(selectedProduct.p_quantity);
        //holder.tvQuantity.setText(selectedProduct.p_quantity);


        String fullUrl = Constants.URL+selectedProduct.p_image;

        Picasso.with(context)
                .load(fullUrl)
                .placeholder(R.drawable.sample)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.ivImageUrl);

        holder.ivImageUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context,DetailActivity.class);
                in.putExtra("product",selectedProduct);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);

            }
        });


    }

    @Override
    public int getItemCount() {
        if(products!=null)
        {
            return products.size();
        }
        return 0;
    }


    //ViewHolder Class
    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        public CardView cvProduct;
        public ImageView ivImageUrl;
        public TextView tvName;
        public TextView tvPrice;
        //
//        public TextView tvQuantity;
//        public TextView tvKeyword;
//        public TextView tvDescription;


        public ProductViewHolder(View itemView) {
            super(itemView);
            cvProduct = (CardView)itemView.findViewById(R.id.cvProduct);
            ivImageUrl = (ImageView) itemView.findViewById(R.id.ivImageUrl);
            tvName= (TextView) itemView.findViewById(R.id.tvName);
            tvPrice = (TextView)itemView.findViewById(R.id.tvPrice);

        }

    }
}

//Pracs-5