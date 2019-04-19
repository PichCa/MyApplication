package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        getListFromServer();
    }

    private void getListFromServer() {
        RestApiBreweryService restApi = new Retrofit.Builder()
                .baseUrl("https://api.openbrewerydb.org/")
                //convertie le json automatiquement
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RestApiBreweryService.class);

        Call<List<Brewery>> call = restApi.getListBreweries();
        call.enqueue(new Callback<List<Brewery>>() {
            @Override
            //Si on obtient bien une réponse du serveur
            public void onResponse(Call<List<Brewery>> call, Response<List<Brewery>> response) {
                //Si la réponse contient des données (le serveur peut répondre autre chose)
               if(response.isSuccessful()){
                   List<Brewery> list = response.body();
                   showList(list);
               }
            }

            @Override
            //Si le serveur renvoie qu'il y a eut une erreur ex : pas d'accès a internet
            public void onFailure(Call<List<Brewery>> call, Throwable t) {
                Log.d("erreur", "erreur");
            }
        });
    }

    private void showList(List<Brewery> list) {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(list);
        recyclerView.setAdapter(mAdapter);
    }
}
