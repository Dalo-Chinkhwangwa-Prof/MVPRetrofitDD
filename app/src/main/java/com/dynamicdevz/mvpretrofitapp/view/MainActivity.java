package com.dynamicdevz.mvpretrofitapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.dynamicdevz.mvpretrofitapp.R;
import com.dynamicdevz.mvpretrofitapp.databinding.ActivityMainBinding;
import com.dynamicdevz.mvpretrofitapp.model.data.JikanResponse;
import com.dynamicdevz.mvpretrofitapp.model.network.JikanRetrofit;
import com.dynamicdevz.mvpretrofitapp.view.adapter.JikanAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private JikanAdapter adapter = new JikanAdapter();

    private JikanRetrofit jikanRetrofit = new JikanRetrofit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.jikanRecyclerview.setAdapter(adapter);

        jikanRetrofit.getSearchResults("Naruto")
                .enqueue(new Callback<JikanResponse>() {
                    @Override
                    public void onResponse(Call<JikanResponse> call, Response<JikanResponse> response) {
                        Log.d("TAG_X", " "+call.request().url());
                        adapter.setResults(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<JikanResponse> call, Throwable t) {
                        Log.d("TAG_X", " OOPS :< "+call.request().url());
                    }
                });
    }
}