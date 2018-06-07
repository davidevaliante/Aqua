package aqua.database

import android.util.Log
import aqua.contracts.EmailRegistrationContracts
import com.google.firebase.auth.AdditionalUserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserInfo

class Repository{
    companion object {
        private val LOCK = Any()
        private var singleInstance : Repository? = null
        private val auth = FirebaseAuth.getInstance()

        @Synchronized
        fun getInstance(): Repository? {
            Log.d("REPO_INSTANCE", "Getting the repository")
            if (singleInstance == null) {
                synchronized(LOCK) {
                    singleInstance = Repository()
                    Log.d("REPO_INSTANCE", "Made new repository")
                }
            }
            return singleInstance
        }
    }

    fun checkConnectionWithBrain() : String = "Connection Working"

    fun submitEmailRegistration(email:String,password:String, caller:EmailRegistrationContracts){
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            authResult ->
            val user : FirebaseUser = authResult.user
            val additionalUserInfo : AdditionalUserInfo = authResult.additionalUserInfo
            caller.onEmailRegistrationSuccess(user, additionalUserInfo)
        }.addOnFailureListener {
            exception ->  caller.onEmailRegistrationFailure()
        }
    }
}