package com.example.firerealtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {

    private lateinit var employeeRecy:RecyclerView
    private lateinit var tvloadingData :TextView
    private lateinit var emplist : ArrayList<Employee>
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        employeeRecy = findViewById(R.id.rvDrugs)
        employeeRecy.layoutManager = LinearLayoutManager(this)
        employeeRecy.setHasFixedSize((true))
        tvloadingData = findViewById(R.id.tvLoadingData)

        emplist = arrayListOf<Employee>()

        getEmployeesData()

    }

    private fun getEmployeesData() {
        employeeRecy.visibility = View.GONE
        tvloadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Employee")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                emplist.clear()
                if(snapshot.exists()){
                    //children means we get all data
                    for(empSnap in snapshot.children){
                        val empData = empSnap.getValue(Employee::class.java)
                        emplist.add(empData!!)
                    }
                    val mAdapter = EmployeeAdapter(emplist)
                    employeeRecy.adapter = mAdapter

                    employeeRecy.visibility = View.VISIBLE
                    tvloadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}