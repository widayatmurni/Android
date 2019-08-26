package com.uploadfile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnUpload, btnBrowseFile;
    private TextView tvFileName;
    private String full_file_path;
    private String URL_ = "http://192.168.0.103/Android/Lat2/upload.php";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFileName = findViewById(R.id.tvFilename);
        btnBrowseFile = findViewById(R.id.btnBrowse);
        btnUpload = findViewById(R.id.btnUpload);

        btnBrowseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    browseFile();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                        return;
                    }
                }
            }
        });

        //upload action
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cek file selected
                uploadFile(full_file_path, URL_);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            browseFile();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK){
            full_file_path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            tvFileName.setText(full_file_path.substring(full_file_path.lastIndexOf("/")+1));
            btnUpload.setEnabled(true);
        }
    }

    private void browseFile() {
        new MaterialFilePicker()
                .withActivity(MainActivity.this)
                .withRequestCode(10)
                .start();
    }

    private void uploadFile(String pathFile, String url) {

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();


        File file = new File(pathFile);
        RequestBody fileBody = RequestBody.create(MediaType.parse(getMimeType(pathFile)+ " ; charset=utf-8"),file);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("upload_file_name", pathFile.substring(pathFile.lastIndexOf("/")+1),fileBody)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        OkHttpClient sendingToSvr = new OkHttpClient();

        sendingToSvr.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (!response.isSuccessful()){
                    System.out.println("Error" + response.body());
                }else{
                    Toast.makeText(MainActivity.this, "File Uploaded",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });

    }

    private String getMimeType(String filePath){
        String mimeType=null;

        String extension = MimeTypeMap.getFileExtensionFromUrl(filePath);
        if (extension!=null){
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return mimeType;
    }
}
