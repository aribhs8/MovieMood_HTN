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

public class AnimatedFragment extends Fragment implements View.OnClickListener
{
    private OptionCallbacks mDataPass;

    public static AnimatedFragment newInstance()
    {
        return new AnimatedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.animation_selection, container, false);

        ImageButton mBackButton = (ImageButton) v.findViewById(R.id.backButton);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(EmotionsFragment.newInstance());
            }
        });

        ((CardView) v.findViewById(R.id.animatedyes)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.animatedno)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.animatedopentoanything)).setOnClickListener(this);

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

        if (cardView.getId() == R.id.animatedyes)
        {
            mDataPass.onDataPass("animated", "yes");
            mDataPass.onAllOptionsSelected();
        } else if (cardView.getId() == R.id.animatedno)
        {
            mDataPass.onDataPass("animated", "no");
            mDataPass.onAllOptionsSelected();
        } else if (cardView.getId() == R.id.animatedopentoanything)
        {
            mDataPass.onDataPass("animated", "");
            mDataPass.onAllOptionsSelected();
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
