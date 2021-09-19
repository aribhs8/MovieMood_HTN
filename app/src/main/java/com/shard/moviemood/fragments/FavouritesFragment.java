package com.shard.moviemood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.shard.moviemood.R;

public class FavouritesFragment extends Fragment implements View.OnClickListener
{

    public static FavouritesFragment newInstance()
    {
        return new FavouritesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.favourites, container, false);
        
        return v;
    }

    @Override
    public void onClick(View view)
    {

    }

}
