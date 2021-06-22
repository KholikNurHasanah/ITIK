package com.example.aplikasiitik

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //Untuk Ganti Profil ( Ambil dari Galeri )
        foto_sampul.setOnClickListener {
            // Untuk memeriksa Runtime Permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    // Permission Denied
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //Munculkan popup untuk merequest Runtime Permission
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    AksesGaleri()
                }
            } else {
                // Jika system OS lebih besar dari Lolipop
                AksesGaleri()
            }
        }

        foto_profil.setOnClickListener {
            // Untuk memeriksa Runtime Permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    // Permission Denied
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //Munculkan popup untuk merequest Runtime Permission
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    AksesGaleri()
                }
            } else {
                // Jika system OS lebih besar dari Lolipop
                AksesGaleri()
            }
        }

        isi_username.setOnClickListener{
            edit_username.setVisibility(View.VISIBLE)
            isi_username.setVisibility(View.INVISIBLE)
        }
        isi_password.setOnClickListener {
            edit_password.setVisibility(View.VISIBLE)
            isi_password.setVisibility(View.INVISIBLE)
        }
        isi_email.setOnClickListener {
            edit_email.setVisibility(View.VISIBLE)
            isi_email.setVisibility(View.INVISIBLE)
        }
        isi_tgl_lahir.setOnClickListener {
            edit_tgl_lahir.setVisibility(View.VISIBLE)
            isi_tgl_lahir.setVisibility(View.INVISIBLE)
        }
    }

    fun SaveData(view: View){
        isi_username.setText(edit_username.getText().toString())
        edit_username.setVisibility(View.INVISIBLE)
        isi_username.setVisibility(View.VISIBLE)

        isi_password.setText(edit_password.getText().toString())
        edit_password.setVisibility(View.INVISIBLE)
        isi_password.setVisibility(View.VISIBLE)

        isi_email.setText(edit_email.getText().toString())
        edit_email.setVisibility(View.INVISIBLE)
        isi_email.setVisibility(View.VISIBLE)

        isi_tgl_lahir.setText(edit_tgl_lahir.getText().toString())
        edit_tgl_lahir.setVisibility(View.INVISIBLE)
        isi_tgl_lahir.setVisibility(View.VISIBLE)

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AksesGaleri()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //Akses Galeri ( Start )
    private fun AksesGaleri() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, 1)
        }
    }
    companion object {
        private val PERMISSION_CODE = 110
    }
}