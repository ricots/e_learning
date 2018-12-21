package com.androidcodefinder.dashboarddesign.helper;

//import android.telecom.Call;

import com.androidcodefinder.dashboarddesign.oop.item;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dafidzeko on 5/11/2016.
 */
public interface apidata {


    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("nis") String nis,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("login_petugas.php")
    Call<ResponseBody> login_petugas(@Field("nip") String nip,
                                     @Field("password") String password);

    @GET("list_materi_all.php")
//    Call<List<item>> getmateri(@Query("nip") String nip);
    Call<List<item>> getmateri();

    @GET("list_soal_all.php")
    Call<List<item>> get_soal_all();

    @GET("list_materi_all.php")
    Call<List<item>> get_materi_all();

    @GET("akun_siswa.php")
    Call<List<item>> getakun(@Query("nis") String nis);

    @FormUrlEncoded
    @POST("update_akun.php")
    Call<ResponseBody> update_santri(@Field("nis") String nis,
                                     @Field("nama") String nama,
                                     @Field("kamar") String kamar,
                                     @Field("password") String password);

    @GET("cari_detail_kendala.php")
    Call<List<item>> getcari_detail_kendala(@Query("nip") String nip, @Query("data") String data);

    @GET("list_detail_kendala_all.php")
    Call<List<item>> getSemua_detail();

    @GET("cari_detail_kendala_all.php")
    Call<List<item>> getcari_detail_kendala_all(@Query("nip") String nip, @Query("data") String data);

    @FormUrlEncoded
    @POST("regis.php")
    Call<ResponseBody> regis(@Field("nis") String nis,
                             @Field("nama_siswa") String nama_siswa,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("regis_petugas.php")
    Call<ResponseBody> regis_petugas(@Field("nip") String nip,
                                     @Field("nama") String nama,
                                     @Field("password") String password);


    @FormUrlEncoded
    @POST("penilaian.php")
    Call<ResponseBody> penilaian(@Field("nip") String nip,
                                 @Field("nis") String nis,
                                 @Field("nilai") String nilai);

    @FormUrlEncoded
    @POST("add_soal.php")
    Call<ResponseBody> add_soal(@Field("nama_soal") String nama_soal,
                                @Field("tgl") String tgl,
                                @Field("jawaban") String jawaban,
                                @Field("jawaban_benar") String jawaban_benar,
                                @Field("nip") String nip);

    @FormUrlEncoded
    @POST("add_materi.php")
    Call<ResponseBody> add_materi(@Field("judul") String judul,
                                  @Field("isi") String isi,
                                  @Field("refrensi") String refrensi,
                                  @Field("video") String video,
                                  @Field("nip") String nip);

    @GET("list_soal_petugas.php")
    Call<List<item>> getdata_soal(@Query("nip") String nip);

    @GET("list_materi_petugas.php")
    Call<List<item>> getdata_materi(@Query("nip") String nip);

    @GET("list_nilai.php")
    Call<List<item>> getdata_nilai(@Query("nip") String nip);

    @FormUrlEncoded
    @POST("update_lokasi.php")
    Call<ResponseBody> update_lokasi(@Field("nip") String nip,
                                     @Field("lat") String lat,
                                     @Field("lng") String lng);

    @FormUrlEncoded
    @POST("delete_soal.php/{id_soal}")
    Call<ResponseBody> delete_soal(@Field("id_soal") String id_soal);

    @FormUrlEncoded
    @POST("delete_materi.php/{id_materi}")
    Call<ResponseBody> delete_materi(@Field("id_materi") String id_materi);


}
