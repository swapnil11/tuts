package com.example.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srg11 on 6/25/2016.
 */
public class MainListFragment extends ListFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> items = new ArrayList<String>();
        items.add("Primary Fragment");
        items.add("Secondary Fragment");
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items));
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.container,new PrimaryFragment()).commit();
                        break;
                    case 1:
                        transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.container,new SecondaryFragment()).commit();
                        break;
                }
            }
        });
    }
}
