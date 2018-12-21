package com.androidcodefinder.dashboarddesign.oop;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class item implements Serializable {

    @Expose
    String nis;
    @Expose
    String nama;
    @Expose
    String password;
    @Expose
    String id_materi;
    @Expose
    String judul;
    @Expose
    String refrensi;
    @Expose
    String video;
    @Expose
    String nip;
    @Expose
    String kamar,id_soal, nama_soal, jawaban, jawaban_benar,tanggal_ujian,nilai;

    public String getTanggal_ujian() {
        return tanggal_ujian;
    }

    public void setTanggal_ujian(String tanggal_ujian) {
        this.tanggal_ujian = tanggal_ujian;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getId_soal() {
        return id_soal;
    }

    public void setId_soal(String id_soal) {
        this.id_soal = id_soal;
    }

    public String getNama_soal() {
        return nama_soal;
    }

    public void setNama_soal(String nama_soal) {
        this.nama_soal = nama_soal;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getJawaban_benar() {
        return jawaban_benar;
    }

    public void setJawaban_benar(String jawaban_benar) {
        this.jawaban_benar = jawaban_benar;
    }

    public String getKamar() {
        return kamar;
    }

    public void setKamar(String kamar) {
        this.kamar = kamar;
    }

    public String getId_materi() {
        return id_materi;
    }

    public void setId_materi(String id_materi) {
        this.id_materi = id_materi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getRefrensi() {
        return refrensi;
    }

    public void setRefrensi(String refrensi) {
        this.refrensi = refrensi;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    @Expose
    String isi;

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
