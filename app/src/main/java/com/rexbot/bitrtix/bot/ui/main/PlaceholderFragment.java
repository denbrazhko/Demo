package com.rexbot.bitrtix.bot.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rexbot.bitrtix.bot.R;
import com.rexbot.bitrtix.bot.entities.Buffer;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

//    private static final String ARG_SECTION_NUMBER = "section_number";
//
//    private com.rexbot.bot.ui.main.PageViewModel pageViewModel;
//    private TextView it;
//
//
//    public static PlaceholderFragment newInstance(int index) {
//        PlaceholderFragment fragment = new PlaceholderFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt(ARG_SECTION_NUMBER, index);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        pageViewModel = new ViewModelProvider(this).get(com.rexbot.bot.ui.main.PageViewModel.class);
//        int index = 1;
//        if (getArguments() != null) {
//            index = getArguments().getInt(ARG_SECTION_NUMBER);
//        }
//        pageViewModel.setIndex(index);
//    }
//
//    @Override
//    public View onCreateView(
//            @NonNull LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState) {
//        Log.i("HER","s");
//        View root = inflater.inflate(R.layout.fragment_balance, container, false);
//        it = root.findViewById(R.id.it);
//
//        for(String string : Buffer.buffer().getArrayList()){
//            it.setText(it.getText()+string+"\n");
//        }
//        return root;
//    }

}