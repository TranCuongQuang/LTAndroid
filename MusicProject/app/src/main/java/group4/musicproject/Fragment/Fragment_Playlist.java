package group4.musicproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import group4.musicproject.Activity.ListSongActivity;
import group4.musicproject.Adapter.PlaylistAdapter;
import group4.musicproject.Model.Playlist;
import group4.musicproject.R;
import group4.musicproject.Service.APIService;
import group4.musicproject.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView listviewPlaylist;
    TextView textviewTitlePlaylist;
    TextView textviewViewMorePlaylist;
    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> playlists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        anhxa( );
        GetData( );
        return view;
    }

    private void anhxa() {
        listviewPlaylist = view.findViewById(R.id.listviewPlaylist);
        textviewTitlePlaylist = view.findViewById(R.id.textviewTitlePlaylist);
        textviewViewMorePlaylist = view.findViewById(R.id.textviewViewMorePlaylist);
    }

    private void GetData() {
        DataService dataService = APIService.getService( );
        Call<List<Playlist>> callback = dataService.GetPlaylistCurrentDay( );
        callback.enqueue(new Callback<List<Playlist>>( ) {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlists = (ArrayList<Playlist>) response.body( );
                playlistAdapter = new PlaylistAdapter(getActivity( ), android.R.layout.simple_list_item_1, playlists);
                listviewPlaylist.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(listviewPlaylist);

                listviewPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), ListSongActivity.class);
                        intent.putExtra("itemplaylist", playlists.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
            }
        });
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter( );
        if (listAdapter == null) {
            return;
        }

        int totalHeight = listView.getPaddingTop( ) + listView.getPaddingBottom( );
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth( ), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount( ); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if (listItem != null) {
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight( );
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams( );
        params.height = totalHeight + (listView.getDividerHeight( ) * (listAdapter.getCount( ) - 1));
        listView.setLayoutParams(params);
        listView.requestLayout( );
    }
}
