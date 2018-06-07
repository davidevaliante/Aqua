package kit.ml.kot.slimmer.aqua

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityConViewModel : AppCompatActivity() {

    // creare istanza del ViewModel
    val viewModel = ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)
            .create(ActivityViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.tot.observe(this, Observer {
            totaleAggiornato ->
            risultato.text = totaleAggiornato.toString()
            primoNumero.text = viewModel.primo.value.toString()
            secondoNumero.text = viewModel.secondo.value.toString()
        })



        primoNumero.setOnClickListener { viewModel.incrementaPrimoNumero() }
        secondoNumero.setOnClickListener { viewModel.incrementaSecondoNumero() }
    }


}
