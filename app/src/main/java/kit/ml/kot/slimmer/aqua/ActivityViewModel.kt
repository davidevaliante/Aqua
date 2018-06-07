package kit.ml.kot.slimmer.aqua

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ActivityViewModel : ViewModel(){

    var primo : MutableLiveData<Int> = MutableLiveData()
    var secondo : MutableLiveData<Int> = MutableLiveData()
    var tot : MutableLiveData<Int> = MutableLiveData()

    fun incrementaPrimoNumero(){
        primo.postValue(primo.value?.plus(1)) // primo++
        aggiornaTotale()
    }

    fun incrementaSecondoNumero(){
        secondo.postValue(secondo.value?.plus(1)) // secondo++
        aggiornaTotale()
    }

    private fun aggiornaTotale() = tot.postValue(primo?.value?.times(secondo.value!!)) // primo * secondo


    init {
        primo.postValue(0)
        secondo.postValue(0)
    }

















    fun setPrimoNumero(newValue : Int) = primo.postValue(newValue)
    fun setSecondoNumero(newValue : Int) = secondo.postValue(newValue)

}