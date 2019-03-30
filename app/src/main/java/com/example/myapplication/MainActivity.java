package com.example.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigation;
    private Fragment homeFrag ;
    private Fragment searchFrag ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new HomeFragment());
        bottomNavigation = findViewById(R.id.navigation);
        bottomNavigation.inflateMenu(R.menu.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        homeFrag = new HomeFragment() ;
        searchFrag = new SearchFragment() ;
        replaceFragment (homeFrag) ;

    }

    public void replaceFragment(Fragment someFragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = homeFrag;
                break;
            case R.id.navigation_search:
                fragment = searchFrag;
                break;
            case R.id.navigation_timer:
                break;
            case R.id.navigation_list:
                break;
        }
        replaceFragment(fragment);
        return true ;
    }

}
