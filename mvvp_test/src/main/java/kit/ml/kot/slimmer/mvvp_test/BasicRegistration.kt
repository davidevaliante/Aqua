package kit.ml.kot.slimmer.mvvp_test

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import aqua.extensions.showMessage
import assignViewModel
import kit.ml.kot.slimmer.mvvp_test.Marcatori.EmailRegistrationButton

open class BasicRegistration : AppCompatActivity() {

    val viewModel = assignViewModel<BasicRegistrationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.numero.postValue(0)

        viewModel.numero.observe(this, Observer {
            newValue -> showMessage(newValue.toString())
        })


    }

    override fun setContentView(layoutResID: Int) {

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val inflatedLayout = inflater.inflate(layoutResID, null) as ViewGroup
        searchForEmailRegistrationButton(inflatedLayout)
        setContentView(inflatedLayout)

    }

    private fun searchForEmailRegistrationButton(viewGroup : ViewGroup){
        val numeroDiFigli = viewGroup.childCount

        for (index in 0 until numeroDiFigli) {
            val currentView = viewGroup.getChildAt(index)
            when(currentView){
                is EmailRegistrationButton -> currentView.setOnClickListener { viewModel.increment() }
            }
        }
    }
}
