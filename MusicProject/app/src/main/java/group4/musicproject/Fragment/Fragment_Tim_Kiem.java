package group4.musicproject.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import group4.musicproject.Adapter.SearchAdapter;
import group4.musicproject.R;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolBarSearch;
    RecyclerView recyclerViewSearch;
    TextView textViewSearch;
    SearchAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        anhxa( );
        return view;
    }

    private void anhxa() {
        toolBarSearch = view.findViewById(R.id.toolBarSearch);
        recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);
        textViewSearch = view.findViewById(R.id.textViewSearch);

        ((AppCompatActivity) getActivity( )).setSupportActionBar(toolBarSearch);
        toolBarSearch.setTitle("Tìm kiếm");
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.memuSeach);
        SearchView searchView = (SearchView) menuItem.getActionView( );
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener( ) {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("??????????????", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
