package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        replaceFragment(note_fragment())

        binding.bottonNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.tareas -> replaceFragment(note_fragment())
                R.id.completado -> replaceFragment(completed_fragment())

                R.id.nueva_nota->replaceFragment(new_note())


                else -> {

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment:Fragment) {
        var fragmentManager=supportFragmentManager
        var fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}