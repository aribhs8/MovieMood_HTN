package com.shard.moviemood;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.shard.moviemood.fragments.FavouritesFragment;
import com.shard.moviemood.fragments.InformationFragment;
import com.shard.moviemood.fragments.PeopleFragment;

public abstract class SingleFragmentActivity extends AppCompatActivity
{
    protected abstract Fragment createFragment();

    private boolean h;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null)
        {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, PeopleFragment.newInstance())
                            .commit();
                } else if (tab.getPosition() == 1) {
//                    Intent myIntent = new Intent(SingleFragmentActivity.this, MovieListingsActivity.class);
//                    startActivity(myIntent);

                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, FavouritesFragment.newInstance())
                            .commit();
                } else if (tab.getPosition() == 2) {
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, InformationFragment.newInstance())
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, PeopleFragment.newInstance())
                            .commit();
                } else if (tab.getPosition() == 1) {
//                    Intent myIntent = new Intent(SingleFragmentActivity.this, MovieListingsActivity.class);
//                    startActivity(myIntent);

                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, FavouritesFragment.newInstance())
                            .commit();
                } else if (tab.getPosition() == 2) {
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, InformationFragment.newInstance())
                            .commit();
                }

            }
        });

    }
}