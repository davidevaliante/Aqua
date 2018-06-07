package aqua.activity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import aqua.contracts.EmailRegistrationContracts
import aqua.database.Repository
import aqua.enums.RegistrationStage
import aqua.extensions.log
import com.google.firebase.auth.AdditionalUserInfo
import com.google.firebase.auth.FirebaseUser

class EmailRegistrationViewModel : ViewModel(), EmailRegistrationContracts{

    val brain = Repository.getInstance()!!

    private lateinit var userMail:String
    private lateinit var userPass:String




    var emailRegistrationStage : MutableLiveData<RegistrationStage> = MutableLiveData()
        set(value) {emailRegistrationStage.postValue(value as RegistrationStage)}




    fun startEmailRegistrationFlow(){
        emailRegistrationStage.postValue(RegistrationStage.EMAIL_AND_PASSWORD_INPUT)
    }


    fun submitEmailRegistrationFields(email:String?, password :String?){
        fun isClean(dirtyMail:String?):Boolean{
            return android.util.Patterns.EMAIL_ADDRESS.matcher(dirtyMail).matches()
                    && dirtyMail != null
                    && dirtyMail.isNotEmpty()
        }

        fun hasPassedTest(password:String?):Boolean{
            return password != null
                    && password.length >= 8
        }

        if (email != null && isClean(email)) {
            this.userMail = email
            if (password != null && hasPassedTest(password)) {
                this.userPass = password
                emailRegistrationStage.postValue(RegistrationStage.OK_EMAIL_AND_PASS)
            }else emailRegistrationStage.postValue(RegistrationStage.ILLEGAL_PASSWORD_FIELD)
        }else emailRegistrationStage.postValue(RegistrationStage.ILLEGAL_EMAIL_FIELD)
    }

    fun performEmailRegistration(){
        emailRegistrationStage.postValue(RegistrationStage.WAITING_FOR_RESPONSE)
        brain.submitEmailRegistration(userMail, userPass, this)
    }





    override fun onEmailRegistrationSuccess(user: FirebaseUser?, additionalUserInfo: AdditionalUserInfo?) {
        user?.let { it.uid log "LOGGED_UID" }
        additionalUserInfo?.let { it.providerId log "LOGGED_PROVIDER" }
        emailRegistrationStage.postValue(RegistrationStage.EMAIL_REGISTRATION_SUCCESS)
    }

    override fun onEmailRegistrationFailure() {
    }


}