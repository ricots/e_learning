package com.androidcodefinder.dashboarddesign;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcodefinder.dashboarddesign.helper.RestManager;
import com.androidcodefinder.dashboarddesign.helper.SharedPrefManager;
import com.androidcodefinder.dashboarddesign.helper.apidata;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_siswa extends AppCompatActivity {
    TextView signin,signup,signin_signup_txt;
    CircleImageView circleImageView;
    Button signin_signup_btn;
    EditText user, password,nama;
    apidata mApiService;
    SharedPrefManager sharedPrefManager;
    private RestManager restManager;
    Context mContext;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_siswa);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);

        user = findViewById(R.id.user);
        nama = findViewById(R.id.nama);
        password = findViewById(R.id.password);

        signin_signup_txt = findViewById(R.id.signin_signup_txt);
//        circleImageView = findViewById(R.id.circleImageView);
        signin_signup_btn = findViewById(R.id.signin_signup_btn);

        mContext = this;
        restManager = new RestManager();
        mApiService = restManager.ambil_data();
        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin()){
            Intent in = new Intent(login_siswa.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);
            finish();
        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin.setTextColor(Color.parseColor("#FFFFFF"));
                signin.setBackgroundColor(Color.parseColor("#FF2729C3"));
                signup.setTextColor(Color.parseColor("#FF2729C3"));
                signup.setBackgroundResource(R.drawable.bordershape);
//                circleImageView.setImageResource(R.drawable.sigin_boy_img);
                signin_signup_txt.setText("Sign In");
                signin_signup_btn.setText("Sign In");
                nama.setVisibility(View.GONE);
                signin_signup_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                        requestLogin();
                    }
                });
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup.setTextColor(Color.parseColor("#FFFFFF"));
                signup.setBackgroundColor(Color.parseColor("#FF2729C3"));
                signin.setTextColor(Color.parseColor("#FF2729C3"));
                signin.setBackgroundResource(R.drawable.bordershape);
//                circleImageView.setImageResource(R.drawable.sigup_boy_img);
                signin_signup_txt.setText("Sign Up");
                signin_signup_btn.setText("Sign Up");
                nama.setVisibility(View.VISIBLE);
                signin_signup_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loading = ProgressDialog.show(login_siswa.this, null, "Harap Tunggu...", true, false);

                        mApiService.regis(
                                user.getText().toString(), nama.getText().toString(), password.getText().toString())
                                .enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()) {
                                            loading.dismiss();
                                            Toast.makeText(login_siswa.this, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(add_kegiatan.this, ContentFragment.class)
//                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                        } else {
                                            loading.dismiss();
                                            Toast.makeText(login_siswa.this, response.message().toString(), Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        loading.dismiss();
                                        Toast.makeText(login_siswa.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            }
        });
    }


    private void requestLogin(){
        mApiService.loginRequest(user.getText().toString(), password.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 200) {
                            loading.dismiss();
                            Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, user.getText().toString());
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_nis, user.getText().toString());
//                                sharedPrefManager.saveSPString(SharedPrefManager.SP_jab, jabatan);
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                            Intent in = new Intent(login_siswa.this, MainActivity.class);
                            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                            finish();
                            Log.d("hasilnya ", response.message().toString());
                        } else if (response.code() == 400) {
                            loading.dismiss();
                            response.message().toString();
                            Log.d("hasilnyacode ", response.message().toString());
                            Toast.makeText(mContext, "GAGAL LOGIN PERIKSA USERNAME DAN PASSWORD", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                        Log.d("hasilnya ",t.getMessage().toString());
                    }
                });
    }
}
