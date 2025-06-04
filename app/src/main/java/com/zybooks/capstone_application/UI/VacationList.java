package com.zybooks.capstone_application.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zybooks.capstone_application.R;
import com.zybooks.capstone_application.database.Repository;
import com.zybooks.capstone_application.entities.Excursion;
import com.zybooks.capstone_application.entities.Vacation;

import java.util.List;

public class VacationList extends AppCompatActivity {

    private Repository repository;
    private SearchView searchView;
    private VacationAdapter adapter;
    private SearchAdapter searchAdapter;
    private ExcursionAdapter countAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vacation_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VacationList.this, VacationDetails.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        repository = new Repository(getApplication());
        adapter = new VacationAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView recyclerView1 = findViewById(R.id.searchrecycler);
        repository = new Repository(getApplication());
        searchAdapter = new SearchAdapter(this);
        recyclerView1.setAdapter(searchAdapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setVisibility(View.GONE);


        searchView = findViewById(R.id.menu_search);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Vacation> searchResults = repository.searchVacationList(newText);
                adapter.setVacations(searchResults);
                searchAdapter.setVacations(searchResults);

                if(!newText.isEmpty()) {
                    recyclerView1.setVisibility(View.VISIBLE);
                } else {
                    recyclerView1.setVisibility(View.GONE);
                }
                return false;
            }
        });

        //System.out.println(getIntent().getStringExtra("test"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vacation_list, menu);
        return true;
    }

    @Override
    protected void onResume() {

        super.onResume();
        List<Vacation> allVacations = repository.getmAllVacations();
        adapter.setVacations(allVacations);
        searchAdapter.setVacations(allVacations);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }

        if (item.getItemId() == R.id.mysample) {
            repository = new Repository(getApplication());
            Vacation vacation = new Vacation(0, "Honduras", "Mariott", "5/17/2025", "5/23/2025");
            repository.insert(vacation);
            vacation = new Vacation(0, "Salzburg", "AirB&B", "6/3/2025", "6/20/2025");
            repository.insert(vacation);
            Excursion excursion = new Excursion(0, "Snorkeling", "5/20/2025", 1);
            repository.insert(excursion);
            excursion = new Excursion(0, "River Tour", "6/10/2025", 1);
            repository.insert(excursion);

            adapter.setVacations(repository.getmAllVacations());
            Toast.makeText(this, "Sample data added", Toast.LENGTH_SHORT).show();

            return true;
        }

        return true;
    }
}