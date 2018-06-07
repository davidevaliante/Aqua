package kit.ml.kot.slimmer.mvvp_test

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class BasicRegistrationViewModel : ViewModel(){

    var numero : MutableLiveData<Int> = MutableLiveData()
    fun setNum(newValue : Int) = numero.postValue(newValue)
    fun increment(){
        val x = numero.value?.plus(1)
        numero.postValue(x)
    }


    var stringa  :MutableLiveData<String> = MutableLiveData()

    fun setString(newValue : String) = stringa.postValue(newValue)


}