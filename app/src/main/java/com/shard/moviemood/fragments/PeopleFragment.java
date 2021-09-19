package com.shard.moviemood.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.shard.moviemood.R;
import com.shard.moviemood.interfaces.OptionCallbacks;

import java.util.ArrayList;
import java.util.List;

public class PeopleFragment extends Fragment implements View.OnClickListener
{
    private OptionCallbacks mDataPass;

    public static PeopleFragment newInstance()
    {
        return new PeopleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_people, container, false);

        ((CardView) v.findViewById(R.id.option_myself)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.option_family)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.option_friends)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.option_other)).setOnClickListener(this);

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

        if (cardView.getId() == R.id.option_myself)
        {
            mDataPass.onDataPass("company", "");
            replaceFragment(genrePreferenceFragment.newInstance());
        } else if (cardView.getId() == R.id.option_family)
        {
            mDataPass.onDataPass("company", "18035");
            replaceFragment(genrePreferenceFragment.newInstance());
        } else if (cardView.getId() == R.id.option_friends)
        {
            mDataPass.onDataPass("company", "9713");
            replaceFragment(genrePreferenceFragment.newInstance());
        } else if (cardView.getId() == R.id.option_other)
        {
            mDataPass.onDataPass("company", "9840");
            replaceFragment(genrePreferenceFragment.newInstance());
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
