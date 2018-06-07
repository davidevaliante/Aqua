package aqua.activity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.net.MailTo
import aqua.auth.R.id.userMail
import aqua.auth.R.id.userPass
import aqua.contracts.EmailRegistrationContracts
import aqua.database.Repository
import aqua.enums.RegistrationStage
import aqua.enums.RegistrationType
import aqua.extensions.log
import com.google.firebase.auth.AdditionalUserInfo
import com.google.firebase.auth.FirebaseUser

class LoginActivityViewModel : ViewModel(){



    val brain = Repository.getInstance()!!
    private var typeOfRegistration = mutableListOf<RegistrationType>()



    var stage : MutableLiveData<RegistrationStage> = MutableLiveData()
        fun sStage(newStage:RegistrationStage) = {stage.postValue(newStage)}

    var user: MutableLiveData<FirebaseUser> = MutableLiveData()
        fun sUser(newUser: FirebaseUser)= {user.postValue(newUser)}


    fun startEmailRegistrationFlow(){
        stage.postValue(RegistrationStage.EMAIL_AND_PASSWORD_INPUT)
    }

    init {
        "Viewmodel created" log "STAGE_FROM_"+this.javaClass.simpleName
    }
}