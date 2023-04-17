package com.example.expretrofit_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    Button btn1,btn2,btn3;
    Retrofit retro;
    JsonPlaceHolderApi jph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retro = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jph= retro.create(JsonPlaceHolderApi.class);

        btn1=findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Post>> call=jph.getPosts();
                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        ListView LV1 =findViewById(R.id.LV1);
                        if (response.isSuccessful())
                        {
                            List<Post> lst =response.body();
                            ArrayAdapter<Post> AA = new ArrayAdapter<Post>(getApplicationContext(), android.R.layout.simple_list_item_1,lst);
                            LV1.setAdapter(AA);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                    }
                });

            }
        });
        btn2=findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Post> call=jph.getPostById(2);
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.isSuccessful())
                        {
                            TextView txt1=findViewById(R.id.txt1);
                            Post P =response.body();
                            txt1.setText(P.toString());

                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {

                    }
                });
            }
        });
        btn3=findViewById(R.id.btn3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Comment>> call=jph.getCommentsByPostId(2);
                call.enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        ListView LV3 =findViewById(R.id.LV3);
                        if (response.isSuccessful())
                        {
                            List<Comment> lst =response.body();
                            ArrayAdapter<Comment> AA = new ArrayAdapter<Comment>(getApplicationContext(), android.R.layout.simple_list_item_1,lst);
                            LV3.setAdapter(AA);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {

                    }
                });

            }
        });
       /*
        Call<List<Comment>> call2=jph.getCommentsById(1);
        call2.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful())
                {

                    return;
                }
                else
                {
                    List<Comment> lst =response.body();
                    String ch="\n_____________\n";
                    for (Comment P : lst)
                    {
                        ch+="id:"+P.getId();
                    }
                    txt.setText(ch);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

        */
    }
}