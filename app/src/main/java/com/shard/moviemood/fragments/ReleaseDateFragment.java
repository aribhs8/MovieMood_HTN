package com.shard.moviemood.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.shard.moviemood.R;
import com.shard.moviemood.interfaces.OptionCallbacks;

import java.util.ArrayList;
import java.util.List;

public class ReleaseDateFragment extends Fragment implements View.OnClickListener
{
    private OptionCallbacks mDataPass;

    public static ReleaseDateFragment newInstance()
    {
        return new ReleaseDateFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.release_date, container, false);

        ImageButton mBackButton = (ImageButton) v.findViewById(R.id.backButton);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(genrePreferenceFragment.newInstance());
            }
        });

        ((CardView) v.findViewById(R.id.recent)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.m2000)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.m80)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.m60)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.upforanydate)).setOnClickListener(this);

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

        if (cardView.getId() == R.id.recent)
        {
            mDataPass.onDataPass("release_date", "2020");
            replaceFragment(EmotionsFragment.newInstance());
        } else if (cardView.getId() == R.id.m2000)
        {
            mDataPass.onDataPass("release_date", "2000");
            replaceFragment(EmotionsFragment.newInstance());
        } else if (cardView.getId() == R.id.m80)
        {
            mDataPass.onDataPass("release_date", "1980");
            replaceFragment(EmotionsFragment.newInstance());
        } else if (cardView.getId() == R.id.m60)
        {
            mDataPass.onDataPass("release_date", "1960");
            replaceFragment(EmotionsFragment.newInstance());
        } else if (cardView.getId() == R.id.upforanydate)
        {
            mDataPass.onDataPass("release_date", "");
            replaceFragment(EmotionsFragment.newInstance());
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
