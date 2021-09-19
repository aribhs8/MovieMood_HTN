package com.shard.moviemood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.shard.moviemood.R;

import java.util.ArrayList;
import java.util.List;

public class InformationFragment extends Fragment implements View.OnClickListener
{

    public static InformationFragment newInstance()
    {
        return new InformationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.settings, container, false);

         Button credits = (Button) v.findViewById(R.id.credits);
         Button termsOfService = (Button) v.findViewById(R.id.termsofuse);

        credits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(CreditsFragment.newInstance());
            }
        });

        termsOfService.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(TermsofServiceFragment.newInstance());
            }
        });

        return v;
    }

    @Override
    public void onClick(View view)
    {
    }

    void replaceFragment(Fragment fragment)
    {
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }
}
