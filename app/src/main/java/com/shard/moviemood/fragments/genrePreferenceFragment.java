package com.shard.moviemood.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shard.moviemood.R;
import com.shard.moviemood.interfaces.OptionCallbacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class genrePreferenceFragment extends Fragment implements View.OnClickListener
{
    private OptionCallbacks mDataPass;

    public static genrePreferenceFragment newInstance()
    {
        return new genrePreferenceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_genre, container, false);

        ((CardView) v.findViewById(R.id.action)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.thriller)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.documentary)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.horror)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.somethingelse)).setOnClickListener(this);

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        mDataPass = (OptionCallbacks) context;
    }

    @Override
    public void onClick(View view)
    {
        CardView cardView = (CardView) view;

        if (cardView.getId() == R.id.action)
        {
            mDataPass.onDataPass("genreID", "28");
            replaceFragment(ReleaseDateFragment.newInstance());
        } else if (cardView.getId() == R.id.thriller)
        {
            mDataPass.onDataPass("genreID", "53");
            replaceFragment(ReleaseDateFragment.newInstance());
        } else if (cardView.getId() == R.id.documentary)
        {
            mDataPass.onDataPass("genreID", "99");
            replaceFragment(ReleaseDateFragment.newInstance());
        } else if (cardView.getId() == R.id.horror)
        {
            mDataPass.onDataPass("genreID", "27");
            replaceFragment(ReleaseDateFragment.newInstance());
        }
        else if (cardView.getId() == R.id.somethingelse)
        {
            mDataPass.onDataPass("genreID", "");
            replaceFragment(ReleaseDateFragment.newInstance());
        }
    }

    void replaceFragment(Fragment fragment)
    {
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }
}