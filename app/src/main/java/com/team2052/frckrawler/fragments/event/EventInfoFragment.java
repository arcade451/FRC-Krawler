package com.team2052.frckrawler.fragments.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team2052.frckrawler.R;
import com.team2052.frckrawler.databinding.FragmentEventInfoBinding;
import com.team2052.frckrawler.db.Event;
import com.team2052.frckrawler.fragments.BaseFragment;

/**
 * Created by adam on 6/15/15.
 */
public class EventInfoFragment extends BaseFragment {
    public static final String EVENT_ID = "EVENT_ID";
    private FragmentEventInfoBinding binding;
    private Event mEvent;

    public static EventInfoFragment newInstance(Event event) {
        Bundle args = new Bundle();
        args.putLong(EVENT_ID, event.getId());
        EventInfoFragment fragment = new EventInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvent = mDbManager.mEvents.load(getArguments().getLong(EVENT_ID));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_info, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentEventInfoBinding.bind(view);
    }
}
