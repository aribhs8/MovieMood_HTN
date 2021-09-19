package com.shard.moviemood.activities;

import androidx.fragment.app.Fragment;

import com.shard.moviemood.SingleFragmentActivity;
import com.shard.moviemood.fragments.FavouritesFragment;
import com.shard.moviemood.fragments.InformationFragment;

public class FavouritesActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment()
    {
        return FavouritesFragment.newInstance();
    }
}