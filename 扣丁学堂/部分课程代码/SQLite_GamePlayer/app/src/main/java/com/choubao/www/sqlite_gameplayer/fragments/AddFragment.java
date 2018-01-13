package com.choubao.www.sqlite_gameplayer.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.choubao.www.sqlite_gameplayer.R;
import com.choubao.www.sqlite_gameplayer.entity.GamePlayer;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends DialogFragment {

    private AddFragmentListener addFragmentListener;

    public static interface AddFragmentListener{
        public void add(GamePlayer gamePlayer);
    }

    public static AddFragment newInstance(){
        AddFragment fragment=new AddFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            addFragmentListener= (AddFragmentListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view=LayoutInflater.from(getActivity()).inflate(R.layout.create_gameplayer_dialog,null);
        return new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.ic_input_add)
                .setView(view)
                .setTitle("新增游戏玩家")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_player= (EditText) view.findViewById(R.id.editText_player);
                        EditText et_score= (EditText) view.findViewById(R.id.editText_score);
                        EditText et_level= (EditText) view.findViewById(R.id.editText_level);
                        GamePlayer gamePlayer=new GamePlayer();
                        gamePlayer.setPlayer(et_player.getText().toString());
                        gamePlayer.setScore(Integer.parseInt(et_score.getText().toString()));
                        gamePlayer.setLevel(Integer.parseInt(et_level.getText().toString()));
                        addFragmentListener.add(gamePlayer);
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
    }

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }
}
