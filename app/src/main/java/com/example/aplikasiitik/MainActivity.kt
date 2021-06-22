package com.example.aplikasiitik


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.prefs.Preferences


class MainActivity : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance()
    var myref = database.reference
    private val relay = myref.child("Relay_Status")
    private val servo = myref.child("Servo_Status")
    var value : Int = 0
    private var valpakan = 35
    private var valirigasi = 0
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.aplikasiitik.R.layout.activity_main)

        fab_pakanikan.setOnClickListener {
            servo.setValue(valpakan)
            Toast.makeText(this, "Ikan telah diberi makan", Toast.LENGTH_LONG).show()
        }

        fab_irigasiAir.setOnClickListener {
            relay.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    value = dataSnapshot.value.toString().toInt()
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
            if(value == 0){
                relay.setValue(1)
                fab_irigasiAir.setBackgroundResource(R.drawable.irigasi_off)
            }else{
                relay.setValue(0)
                fab_irigasiAir.setBackgroundResource(R.drawable.irigasi_on)
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_refresh -> {
                //code refresh
                finish()
                true
            }
            R.id.nav_profile -> {
                val pindahkeProfile = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(pindahkeProfile)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
