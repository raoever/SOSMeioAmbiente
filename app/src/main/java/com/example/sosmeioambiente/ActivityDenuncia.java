package com.example.sosmeioambiente;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ActivityDenuncia extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private EditText editTextEndereco, editTextDescricao;
    private TextView textViewLatitude, textViewLongitude;
    private LocationManager locationManager;
    private String tipo;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;
    private ImageView ivImagem;
    private AppDatabase db;
    private static final int PERMISSAO_REQUEST = 3;
    private static final int GALERIA_IMAGENS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }
        editTextEndereco = findViewById(R.id.editTextEndereco);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        ivImagem = findViewById(R.id.ivImagem);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterSninner = ArrayAdapter.createFromResource(this, R.array.tipos, android.R.layout.simple_list_item_1);
        adapterSninner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSninner);
        spinner.setOnItemSelectedListener(this);

        textViewLatitude = findViewById(R.id.textViewLatitude);
        textViewLongitude = findViewById(R.id.textViewLongitude);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ivImagem.setImageBitmap(imageBitmap);

            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                File file = new File("/sdcard/Pictures/" + "minhaFoto.jpg");
                OutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();

                } catch (IOException e) {

                }
            }
        }
        if(requestCode == GALERIA_IMAGENS && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap imagemGaleria = (BitmapFactory.decodeFile(picturePath));
            ivImagem.setImageBitmap(imagemGaleria);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSAO_REQUEST){
            if(grantResults.length < 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            } else {

            }
            return;
        }
    }

    public void denunciar(View view) {
        Denuncia d = new Denuncia();
        ControleSessao controleSessao = new ControleSessao(ActivityDenuncia.this);
        d.setIdUsuario(controleSessao.pegaSessao());
        d.setTipo(tipo);
        d.setEndereco(editTextEndereco.getText().toString());
        d.setLatitude(textViewLatitude.getText().toString());
        d.setLongitude(textViewLongitude.getText().toString());
        d.setDescricao(editTextDescricao.getText().toString());
        Long tsLong = System.currentTimeMillis() / 1000;
        d.setProtocolo(tsLong.toString());
        Log.i("protocolo: ", tsLong.toString());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap imagemTela = ((BitmapDrawable) ivImagem.getDrawable()).getBitmap();
        imagemTela.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte imagemBytes[] = stream.toByteArray();
        d.setImagem_denuncia(imagemBytes);
        db.denunciaDao().insertAll(d);

        Intent intent = new Intent(ActivityDenuncia.this, ActivityDenunciaProt.class);
        intent.putExtra("protocolo", tsLong.toString());
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tipo = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        tipo = "Não Selecionado";
    }

    public void pegaGps(View view) {
        //Pegar GPS
        if (ContextCompat.checkSelfPermission(ActivityDenuncia.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(ActivityDenuncia.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ActivityDenuncia.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                textViewLatitude.setText(String.valueOf(location.getLatitude()));
                textViewLongitude.setText(String.valueOf(location.getLongitude()));
            }
        });
    }

    public void tiraFoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    public void pegaFoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALERIA_IMAGENS);
    }
}