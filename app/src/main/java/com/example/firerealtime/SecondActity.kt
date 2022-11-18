package com.example.firerealtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SecondActity : AppCompatActivity() {

    private lateinit var etname : EditText
    private lateinit var etage : EditText
    private lateinit var etsalary : EditText
    private lateinit var etbtn : Button


    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_actity)

        etname = findViewById(R.id.empname)
        etage = findViewById(R.id.empage)
        etsalary = findViewById(R.id.empsal)
        etbtn = findViewById(R.id.btnsave)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        etbtn.setOnClickListener {
            //call a method
            saveEmployeeData()
        }
    }

    private fun saveEmployeeData(){
        //getting values

        val empName = etname.text.toString()
        val empage = etage.text.toString()
        val empsal = etsalary.text.toString()

        if(empName.isEmpty()){
            etname.error = "Please enter name"
        }

        if(empage.isEmpty()){
            etage.error = "Please enter age"
        }
        if(empsal.isEmpty()){
            etsalary.error = "Please enter salary"
        }

        //create unique id
        val empId = dbRef.push().key!!

        val employee = Employee( empId, empName, empage, empsal)

        dbRef.child(empId).setValue(employee)

            .addOnCompleteListener {
                Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { err->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
               // Log.d("TAG---->","response: ${err.message}")
            }

    }
}