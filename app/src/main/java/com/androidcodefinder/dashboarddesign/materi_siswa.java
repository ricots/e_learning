package com.androidcodefinder.dashboarddesign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.androidcodefinder.dashboarddesign.adapter.adapter_materi;
import com.androidcodefinder.dashboarddesign.helper.RestManager;
import com.androidcodefinder.dashboarddesign.oop.item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class materi_siswa extends AppCompatActivity implements adapter_materi.data_kirim{
    private RecyclerView recyclerView;
    private RestManager restManager;
    private adapter_materi adapter;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_siswa);
        recyclerView = (RecyclerView) findViewById(R.id.list_materi);
        recyclerView.setHasFixedSize(true);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new adapter_materi(this);
        recyclerView.setAdapter(adapter);
        getmaterinya();
    }

    public void getmaterinya(){
        restManager = new RestManager();
        Call<List<item>> listCall = restManager.ambil_data().getmateri();
        loading = ProgressDialog.show(materi_siswa.this, null, "Harap Tunggu...", true, false);
        listCall.enqueue(new Callback<List<item>>() {
            @Override
            public void onResponse(Call<List<item>> call, Response<List<item>> response) {

                if (response.isSuccessful()) {
                    List<item> materi = response.body();
                    for (int i = 0 ; i < materi.size() ;i++){
                        item  mtr  = materi.get(i);
                        adapter.addBunga(mtr);
                        loading.dismiss();
                    }

                }else {
                    loading.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<item>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "cek koneksi internet", Toast.LENGTH_SHORT).show();
                Log.d("hasilnya ", t.toString());
            }

        });
    }

    @Override
    public void onClick(int position) {
        item kirim_id_materi = adapter.getAmbildata(position);
        Intent intent = new Intent(materi_siswa.this, detail_materi.class);
        intent.putExtra("kirim", kirim_id_materi);
        startActivity(intent);
    }
}
