package dev.android.jamesyoon.com.firebasesample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONObject;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Fire screen view
        mFirebaseAnalytics.setCurrentScreen(getActivity(), "first_screen", null);
        mFirebaseAnalytics.logEvent("first_screen_view", new Bundle());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Fire custom event 1
        view.findViewById(R.id.button_ce1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAnalytics.logEvent("custom_event_1", new Bundle());
            }
        });

        // Fire custom event 2
        view.findViewById(R.id.button_ce2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAnalytics.logEvent("custom_event_2", new Bundle());
            }
        });

        // Fire Enhanced Ecommerce Product View event
        view.findViewById(R.id.button_product_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList items = new ArrayList();

                Bundle product1 = new Bundle();
                product1.putString( FirebaseAnalytics.Param.ITEM_ID, "1234");
                product1.putString( FirebaseAnalytics.Param.ITEM_NAME, "Burger");
                product1.putString( FirebaseAnalytics.Param.ITEM_CATEGORY, "Ecommerce");
                product1.putString( FirebaseAnalytics.Param.ITEM_VARIANT, "Regular");
                product1.putDouble( FirebaseAnalytics.Param.PRICE, 10.5 );
                product1.putLong( FirebaseAnalytics.Param.INDEX, 1 );
                product1.putString(FirebaseAnalytics.Param.CURRENCY, "USD");

                Bundle product2 = new Bundle();
                product2.putString( FirebaseAnalytics.Param.ITEM_ID, "5678");
                product2.putString( FirebaseAnalytics.Param.ITEM_NAME, "Chicken");
                product2.putString( FirebaseAnalytics.Param.ITEM_CATEGORY, "Ecommerce");
                product2.putString( FirebaseAnalytics.Param.ITEM_VARIANT, "Upsell");
                product2.putDouble( FirebaseAnalytics.Param.PRICE, 22 );
                product2.putLong( FirebaseAnalytics.Param.INDEX, 2 );
                product2.putString(FirebaseAnalytics.Param.CURRENCY, "USD");

                items.add(product1);
                items.add(product2);

                Bundle ecommerceBundle = new Bundle();
                ecommerceBundle.putParcelableArrayList( FirebaseAnalytics.Param.ITEMS, items );

                ecommerceBundle.putString( FirebaseAnalytics.Param.ITEM_LIST_NAME, "A la carte" );

                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, ecommerceBundle);
            }
        });

        // Go to the next screen
        view.findViewById(R.id.button_goto_purchase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}