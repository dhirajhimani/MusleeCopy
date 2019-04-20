package com.example.museleecopy

import android.os.Bundle
import com.example.topartists.view.TopArtistsFragment
import dagger.android.support.DaggerAppCompatActivity

class MuseleeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment, TopArtistsFragment())
            commit()
        }
    }
}
