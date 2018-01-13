package com.choubao.www.sqlite_gameplayer.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.choubao.www.sqlite_gameplayer.R;
import com.choubao.www.sqlite_gameplayer.entity.GamePlayer;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamePlayerFragment extends Fragment {

    private GamePlayerFragmentListener gamePlayerFragmentListener;
    private GamePlayerAdapter gameplayerAdapter;

    public static interface GamePlayerFragmentListener{
        public void showGamePlayerFragment();
        public void showUpdateFragment(int id);

        public void delete(int id);
        public ArrayList<GamePlayer> findAll();
    }

    public static GamePlayerFragment newInstance(){
        GamePlayerFragment gamePlayerfragment=new GamePlayerFragment();
        return gamePlayerfragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            gamePlayerFragmentListener= (GamePlayerFragmentListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public GamePlayerFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<GamePlayer> gamePlayers=gamePlayerFragmentListener.findAll();
        gameplayerAdapter=new GamePlayerAdapter(getActivity(),gamePlayers);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_game_player,container,false);
        ListView listView= (ListView) view.findViewById(R.id.listView);
        registerForContextMenu(listView);//注册上下文菜单
        listView.setAdapter(gameplayerAdapter);
        return view;
    }

    //创建菜单项
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("修改/删除");
        menu.setHeaderIcon(android.R.drawable.ic_menu_edit);
        getActivity().getMenuInflater().inflate(R.menu.listview_context_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.delete_menu:
                AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                TextView textView_id= (TextView) info.targetView.findViewById(R.id.textView_id);//targetView拿到目标组件
                int id=Integer.parseInt(textView_id.getText().toString());
                gamePlayerFragmentListener.delete(id);
                changedData();
                break;
            case R.id.update_menu:
                info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                textView_id= (TextView) info.targetView.findViewById(R.id.textView_id);
                id=Integer.parseInt(textView_id.getText().toString());
                gamePlayerFragmentListener.showUpdateFragment(id);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        gamePlayerFragmentListener=null;
    }

    public void changedData() {
        gameplayerAdapter.setGamePlayers(gamePlayerFragmentListener.findAll());
        gameplayerAdapter.notifyDataSetChanged();
    }

    //适配器
    private static class GamePlayerAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<GamePlayer> gamePlayers;

        public void setGamePlayers(ArrayList<GamePlayer> gamePlayers) {
            this.gamePlayers = gamePlayers;
        }

        public GamePlayerAdapter(Context context,ArrayList<GamePlayer> gamePlayers) {
            this.context=context;
            this.gamePlayers=gamePlayers;
        }

        @Override
        public int getCount() {
            return gamePlayers.size();
        }

        @Override
        public Object getItem(int position) {
            return gamePlayers.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh=null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.game_player_list_item_layout, null);
                vh = new ViewHolder();
                vh.tv_id = (TextView) convertView.findViewById(R.id.textView_id);
                vh.tv_player = (TextView) convertView.findViewById(R.id.textView_player);
                vh.tv_score = (TextView) convertView.findViewById(R.id.textView_score);
                vh.tv_level = (TextView) convertView.findViewById(R.id.textView_level);
            } else {
                vh=(ViewHolder)convertView.getTag();
            }
            GamePlayer g=gamePlayers.get(position);
            vh.tv_id.setText(String.valueOf(g.getId()));
            vh.tv_player.setText(g.getPlayer());
            vh.tv_score.setText(String.valueOf(g.getScore()));
            vh.tv_level.setText(String.valueOf(g.getLevel()));
            return convertView;
        }

        private static class ViewHolder {
            TextView tv_id,tv_player,tv_score,tv_level;
        }
    }

}
