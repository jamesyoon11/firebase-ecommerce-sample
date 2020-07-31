package dev.android.jamesyoon.com.firebasesample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Fire screen view
        mFirebaseAnalytics.setCurrentScreen(getActivity(), "second_screen", null);
        mFirebaseAnalytics.logEvent("second_screen_view", new Bundle());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fire Enhanced Ecommerce Add to Cart event
        view.findViewById(R.id.button_add_to_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle product1 = new Bundle();
                product1.putString( FirebaseAnalytics.Param.ITEM_ID, "1234");
                product1.putString( FirebaseAnalytics.Param.ITEM_NAME, "Burger");
                product1.putString( FirebaseAnalytics.Param.ITEM_CATEGORY, "Ecommerce");
                product1.putString( FirebaseAnalytics.Param.ITEM_VARIANT, "Regular");
                product1.putDouble( FirebaseAnalytics.Param.PRICE, 10.5 );
                product1.putLong( FirebaseAnalytics.Param.INDEX, 1 );
                product1.putInt( FirebaseAnalytics.Param.QUANTITY, 1);
                product1.putString(FirebaseAnalytics.Param.CURRENCY, "USD");

                Bundle ecommerceBundle = new Bundle();
                ecommerceBundle.putDouble(FirebaseAnalytics.Param.VALUE, 10.5);
                ecommerceBundle.putBundle(FirebaseAnalytics.Param.ITEMS, product1);

                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, ecommerceBundle);
            }
        });

        // Fire Enhanced Ecommerce Purchase event
        view.findViewById(R.id.button_purchase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle product1 = new Bundle();
                product1.putString( FirebaseAnalytics.Param.ITEM_ID, "1234");
                product1.putString( FirebaseAnalytics.Param.ITEM_NAME, "Burger");
                product1.putString( FirebaseAnalytics.Param.ITEM_CATEGORY, "Ecommerce");
                product1.putString( FirebaseAnalytics.Param.ITEM_VARIANT, "Regular");
                product1.putDouble( FirebaseAnalytics.Param.PRICE, 10.5 );
                product1.putLong( FirebaseAnalytics.Param.INDEX, 1 );
                product1.putString(FirebaseAnalytics.Param.CURRENCY, "USD");

                ArrayList items = new ArrayList();
                items.add(product1);

                Bundle ecommerceBundle = new Bundle();
                ecommerceBundle.putParcelableArrayList( FirebaseAnalytics.Param.ITEMS, items );
                ecommerceBundle.putString( FirebaseAnalytics.Param.TRANSACTION_ID, "345621" );
                ecommerceBundle.putString( FirebaseAnalytics.Param.AFFILIATION, "STORE-A" );
                ecommerceBundle.putDouble( FirebaseAnalytics.Param.VALUE, 10.5 );
                ecommerceBundle.putDouble( FirebaseAnalytics.Param.TAX, 0 );
                ecommerceBundle.putDouble( FirebaseAnalytics.Param.SHIPPING, 0 );

                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.PURCHASE, ecommerceBundle);
            }
        });

        view.findViewById(R.id.button_go_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}