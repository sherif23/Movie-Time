package com.example.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Adapters.MovieAdapter;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.ViewModels.HomeViewModel;

import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private TextView txMonth1 ;
    private TextView txMonth2 ;
    final String[] months = new String[]{"January", "February", "March", "April",
            "May", "June", "July", "Augest", "September", "October", "November", "December"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        txMonth1 = (TextView) rootView.findViewById(R.id.tx_month1) ;
        txMonth2 = (TextView) rootView.findViewById(R.id.tx_month2) ;
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rc_movielist1) ;
        Calendar c = Calendar.getInstance();
        int index = c.get(Calendar.MONTH);
        int nextIndex = (index==11) ?  0 : index + 1 ;
        txMonth1.setText(months[index]);
        txMonth2.setText(months[nextIndex]);

        return rootView ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      /*  mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mViewModel.init();
        mViewModel.getMoviesList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null){
                    initRecyclerView();
                }
            }
        });*/
    }

    private void initRecyclerView(){
        mAdapter = new MovieAdapter(mViewModel.getMoviesList().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) linearLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }


}
