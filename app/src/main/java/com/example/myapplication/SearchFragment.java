package com.example.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapters.MovieAdapter;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.ViewModels.SearchViewModel;

import java.util.List;


public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private SearchViewModel mViewModel;
    private TextView mTextView ;
    private SearchView searchView ;
    private String searchString ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.search_fragment, container, false);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rc_movielist2) ;
        mTextView = (TextView) rootView.findViewById(R.id.tx_search) ;
        return  rootView ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        mViewModel.init();
        setTextView("search");

        mViewModel.getTextViewMass().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                setTextView(s);
            }
        });

        mViewModel.getMoviesList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                    if (movies != null ) mAdapter.updtateList(movies);
                    mAdapter.notifyDataSetChanged();
            }
        });



        initRecyclerView() ;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu_item,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search) ;
        searchView = (SearchView)menuItem.getActionView() ;
        if (searchString!= null )
            searchView.setQuery(searchString,true);
        searchView.setQueryHint("search....");
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mViewModel.search(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initRecyclerView(){
            mAdapter = new MovieAdapter(mViewModel.getMoviesList().getValue());
            RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            ((LinearLayoutManager) linearLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(mAdapter);
    }

    private void setTextView (String massage) {
        if (massage.equals("Done")) {mTextView.setVisibility(View.INVISIBLE); return;}
        mTextView.setText(massage);
        mTextView.setVisibility(View.VISIBLE);
    }

}
