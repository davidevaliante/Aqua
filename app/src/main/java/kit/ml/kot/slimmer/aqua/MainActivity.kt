package kit.ml.kot.slimmer.aqua

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var tot = 0
    var primo = 0
    var secondo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        incrementaPrimoNumero.setOnClickListener {
            primo++
            updateUi()
        }
        incrementaSecondoNumero.setOnClickListener {
            secondo++
            updateUi()
        }
    }

    fun updateUi(){
        primoNumero.text = primo.toString()
        secondoNumero.text = secondo.toString()
        risultato.text = (primo*secondo).toString()
    }




}
