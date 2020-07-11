package net.shybaieva.audioplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> songTitle = new ArrayList<>();
    private ArrayList<String> songDescr = new ArrayList<>();
    RecyclerView recyclerView;

    public static MySqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqliteHelper = new MySqliteHelper(this, "BooksDB.sqlite",null,1);
        sqliteHelper.queryData("CREATE TABLE IF NOT EXISTS BOOKS (BookId INTEGER PRIMARY KEY AUTOINCREMENT, Title VARCHAR, Author VARCHAR, Description VARCHAR, Image VARCHAR, SoundFile BLOB)");
        sqliteHelper.queryData("CREATE TABLE IF NOT EXISTS CATEGORY (CategoryId INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR)");
        sqliteHelper.queryData("CREATE TABLE IF NOT EXISTS BOOKS_CATEGORIES BookID INTEGER, CategoryID INTEGER");

        songTitle.add("1 Title");
        songDescr.add("1 Author");

        songTitle.add(("2 Title"));
        songDescr.add(("2 Author"));

        Log.i("result", songTitle.get(1));

        initRecyclerView();

    }

    private void initRecyclerView() {
        Log.i("result", "initRecyclerView");
       // RecyclerView
                recyclerView = findViewById(R.id.songsRV);
        Log.i("result", "RV found");
        SongAdapter songAdapter = new SongAdapter(this, songTitle, songDescr);
        Log.i("result", "RV created");
        Log.i("result", songTitle.get(1));
        Log.i("result", songDescr.get(1));
        recyclerView.setAdapter(songAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }
}
class SongAdapter extends RecyclerView.Adapter<net.shybaieva.audioplayer.SongAdapter.ViewHolder> {

        Context mContext;
        List<String> songTitles ;
        List<String> songDescriptions;

        public SongAdapter(Context context, ArrayList<String> songTitle, ArrayList<String> songDescription) {
            mContext = context;
            this.songTitles = songTitle;
            this.songDescriptions = songDescription;
        }

        @NonNull
        @Override
        public net.shybaieva.audioplayer.SongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
            net.shybaieva.audioplayer.SongAdapter.ViewHolder viewHolder = new net.shybaieva.audioplayer.SongAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull net.shybaieva.audioplayer.SongAdapter.ViewHolder holder, final int position) {

            holder.titleView.setText(songTitles.get(position));
            holder.descriptionView.setText(songDescriptions.get(position));

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Is clicked" + songTitles.get(position), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(v.getContext(), DetailActivity.class);
                    i.putExtra("title",songTitles.get(position));
                    v.getContext().startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return songTitles.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView titleView, descriptionView;
            ConstraintLayout layout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                titleView =  itemView.findViewById(R.id.songTitle);
                descriptionView = itemView.findViewById(R.id.songAuthor);
                layout = itemView.findViewById(R.id.songLayout);
            }
        }
    }


