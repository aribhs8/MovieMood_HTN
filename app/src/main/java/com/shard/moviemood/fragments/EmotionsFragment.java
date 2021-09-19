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

public class EmotionsFragment extends Fragment implements View.OnClickListener
{
    private OptionCallbacks mDataPass;

    public static EmotionsFragment newInstance()
    {
        return new EmotionsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.emotion_selection, container, false);

        ImageButton mBackButton = (ImageButton) v.findViewById(R.id.backButton);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(ReleaseDateFragment.newInstance());
            }
        });

        ((CardView) v.findViewById(R.id.laugh)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.sad)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.surprised)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.inspired)).setOnClickListener(this);
        ((CardView) v.findViewById(R.id.idc)).setOnClickListener(this);

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

        if (cardView.getId() == R.id.laugh)
        {
            mDataPass.onDataPass("emotion", "282960");
            replaceFragment(AnimatedFragment.newInstance());
        } else if (cardView.getId() == R.id.sad)
        {
            mDataPass.onDataPass("emotion", "1647");
            replaceFragment(AnimatedFragment.newInstance());
        } else if (cardView.getId() == R.id.surprised)
        {
            mDataPass.onDataPass("emotion", "278160");
            replaceFragment(AnimatedFragment.newInstance());
        } else if (cardView.getId() == R.id.inspired)
        {
            mDataPass.onDataPass("emotion", "281585");
            replaceFragment(AnimatedFragment.newInstance());
        } else if (cardView.getId() == R.id.idc)
        {
            mDataPass.onDataPass("emotion", "");
            replaceFragment(AnimatedFragment.newInstance());
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
