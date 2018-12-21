package com.androidcodefinder.dashboarddesign.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidcodefinder.dashboarddesign.R;
import com.androidcodefinder.dashboarddesign.helper.RestManager;
import com.androidcodefinder.dashboarddesign.helper.apidata;
import com.androidcodefinder.dashboarddesign.oop.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dafidzeko on 5/11/2016.
 */
public class adapter_soal_petugas extends RecyclerView.Adapter<adapter_soal_petugas.Holder> {

    private static final String TAG = adapter_soal_petugas.class.getSimpleName();
    private List<item> mtr = new ArrayList<>();
    private final data_kirim mListen;
    apidata mApiService;
    private RestManager restManager;
    ProgressDialog loading;
    private Context context;

    public adapter_soal_petugas(data_kirim listener) {
        mtr = new ArrayList<>();
        mListen = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_soal_petugas, parent, false);
        context = parent.getContext();
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        item materi = mtr.get(position);
        holder.id_soal.setText(materi.getId_soal());
        holder.nama_soal.setText(materi.getNama_soal());

         }

    @Override
    public int getItemCount() {
        return mtr.size();
    }


    public void addBunga(item materi) {
        //Log.d(TAG,bunga.getFoto());
        //   Log.d(TAG,bunga.getFoto());
        mtr.add(materi);
        notifyDataSetChanged();
    }

    public void clear() {
        mtr.clear();
        notifyDataSetChanged();
    }

    public item getAmbildata(int position) {
        return mtr.get(position);
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id_soal,nama_soal;

        public Holder(View itemView) {
            super(itemView);
            id_soal = (TextView) itemView.findViewById(R.id.id_soal);
            nama_soal = (TextView) itemView.findViewById(R.id.nama_soal);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mListen.onClick(getLayoutPosition());
        }
    }

    public interface data_kirim {
        void onClick(int position);
    }

}
