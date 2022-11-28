package com.keepcoding.lifecycle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlin.random.Random
import com.keepcoding.lifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding //Crear variable del tipo binging
    companion object {const val TAG_NUM = "MyName"}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Recogemos el string o null si no hubiera
        val textoAnterior = savedInstanceState?.getString(TAG_NUM)
        //Si es null creamos un nuevo valor aleatorio, sino utilizamos el valor leido anteriormente
        binding.tvView.text = textoAnterior ?: Random(System.nanoTime()).nextInt().toString()

        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val preferencesText = sharedPreferences.getString(TAG_NUM, "")
        Log.d(MainActivity::class.java.simpleName, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(MainActivity::class.java.simpleName, "onstart")
    }
    override fun onResume() {
        super.onResume()
        Log.d(MainActivity::class.java.simpleName, "onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d(MainActivity::class.java.simpleName, "onPause")
    }
    override fun onStop() {
        super.onStop()
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val editPrefs = sharedPreferences.edit()
        editPrefs.putString(TAG_NUM, binding.tvView.text.toString())
        editPrefs.apply()
        Log.d(MainActivity::class.java.simpleName, "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) { //Clase que permite guardar valores en ella antes de que sean destruidos
        super.onSaveInstanceState(outState)              // La variable se recupera en el onCreate
        outState.putString(TAG_NUM, binding.tvView.text.toString())
    }


    override fun onRestart() {
        super.onRestart()
        Log.d(MainActivity::class.java.simpleName, "onRestart")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(MainActivity::class.java.simpleName, "onDestroy")
    }

}