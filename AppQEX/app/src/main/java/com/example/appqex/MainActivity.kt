package com.example.appqex





import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.appqex.presenters.AdapterForHistory
import com.example.appqex.use_cases.data.Calcul

import com.example.appqex.use_cases.data.database.Calculations
import com.example.appqex.use_cases.data.database.CalculationsDatabase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    lateinit var mBtn1: Button
    lateinit var txt:TextView
    lateinit var builderForView:StringBuilder
    lateinit var db: CalculationsDatabase
    lateinit var recycler:RecyclerView
    lateinit var adapter: AdapterForHistory
    lateinit var list:List<Calculations>
    lateinit var mCalcul: Calcul
    val TAG ="com.example.appqex.TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        val bottomFragment = BottomSheetDialogFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container_bottom_sheet, bottomFragment)
            .commit()
        bottomFragment.isCancelable= false
        db = Room.databaseBuilder(
            applicationContext,
            CalculationsDatabase::class.java, "database").build()

   CoroutineScope(Dispatchers.IO).launch {
       val request = async { db.calculationsDao()?.getFromDb()}
       val result = request.await()
        list = result!!
       withContext(Dispatchers.Main ) {
           recycler.layoutManager =
                   LinearLayoutManager(applicationContext,
                            LinearLayoutManager.VERTICAL, false)
                            adapter = AdapterForHistory(list)
                            recycler.adapter = adapter
       }
      }
    }

    fun onClick(view: View){
        var btn = view as Button
        if(!(btn.text=="remove"||btn.text=="result")) {
            builderForView.append(view.text)
            txt.text = builderForView
        }
        if(btn.text=="result"){
            mCalcul.getResult(builderForView, db, list, adapter, txt)
        }
        if(btn.text == "remove"){
            txt.text = ""
            builderForView.setLength(0)
        }
    }
    fun init(){
        mCalcul  = Calcul()
        txt = findViewById(R.id.calc_txt)
        recycler = findViewById(R.id.history_recycler)
        builderForView = StringBuilder()
    }

}