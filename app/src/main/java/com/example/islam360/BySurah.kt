package com.example.islam360

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.Adapters.SurahAdapter
import com.example.islam360.dataAccess.DbHelper
import com.example.islam360.models.SurahModel



class BySurahActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_by_surah)

        // You can set a dynamic title if needed
        title = "By Surah Activity"
    }
}
