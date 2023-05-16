package com.example.bookingnow.view.activities

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract.DisplayPhoto
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.bookingnow.R
import com.example.bookingnow.databinding.ActivityRegestrationBinding
import com.example.bookingnow.model.Consts
import com.example.bookingnow.model.Consts.EMPTY_STRING_FIELD
import com.example.bookingnow.model.database.UserItem
import com.example.bookingnow.view.activities.MainActivity.Companion.ADMIN_LOGGED
import com.example.bookingnow.view.activities.MainActivity.Companion.GUEST_LOGGED
import com.example.bookingnow.view.activities.MainActivity.Companion.USER_LOGGED
import com.example.bookingnow.viewmodel.RegisterActivityViewModel
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.System.exit


class RegestrationActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivityRegestrationBinding
    private var imageUri: String = EMPTY_STRING_FIELD
    private val viewModel: RegisterActivityViewModel by lazy {
        ViewModelProvider(this)[RegisterActivityViewModel::class.java]
    }
    var sharedPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegestrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Запрашиваем разрешение на возможность считывать файлы
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            Consts.REQUEST_CODE
        )

        sharedPref = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        binding.FirstVisitTextView.setOnClickListener(this)
        binding.ContinueLikeGuest.setOnClickListener(this)
        binding.ButtonSelectImageForUser.setOnClickListener(this)
        binding.ButtonRegister.setOnClickListener(this)
        binding.ButtonLogin.setOnClickListener(this)



        //Сразу срабатывает
        val editor = sharedPref!!.edit()
        var justCount = 0;
        viewModel.listOfUsers.observe(this) {
            if(justCount > 0){
                    editor.putInt("user_id", it[it.size - 1].id)
                    editor.apply()
                    Toast.makeText(this, "Успешно", Toast.LENGTH_LONG).show()
                    toMainActivity("user")
            }
            justCount++
        }
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.FirstVisitTextView -> {
                binding.apply {
                    FirstVisitTextView.visibility = View.GONE
                    InputLayoutName.visibility = View.VISIBLE
                    InputLayoutPhone.visibility = View.VISIBLE
                    ButtonRegister.visibility = View.VISIBLE
                    ButtonLogin.visibility = View.GONE
                    ButtonSelectImageForUser.visibility = View.VISIBLE
                }
            }
            R.id.ButtonRegister -> {


                if (fieldsIsCorrectRegister()) {
                    val name = binding.EditTextName.text.toString()
                    val password = binding.EditTextPassword.text.toString()
                    val photo = imageUri
                    val role = "user"
                    val phone = binding.EditTextPhone.text.toString()
                    val email = binding.EditTextEmail.text.toString()

                    val user = UserItem(0, name, password, photo, true, role, phone, email)
                    viewModel.userRegister(user)

                }else{
                    Snackbar.make(binding.root,"Заполните поля корректно",Snackbar.LENGTH_LONG).show()
                }
            }
            R.id.ButtonLogin -> {

                if (fieldsIsCorrectLogin()) {
                    var listOfUsers: List<UserItem>
                    viewModel.listOfUsers.observe(this) {
                        listOfUsers = it
                        listOfUsers.forEach {
                            if (it.email ==parseEmail( binding.EditTextEmail.text.toString()) && (it.password == parsePassword(binding.EditTextPassword.text.toString()))) {
                                val editor = sharedPref!!.edit()
                                editor.putInt("user_id", it.id)
                                editor.apply()
                                toMainActivity("user")
                            }else{
                                Snackbar.make(binding.root,"Пользователь не найден",Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }
                }else{
                    Snackbar.make(binding.root,"Заполните поля корректно",Snackbar.LENGTH_LONG).show()
                }
            }

            R.id.ContinueLikeGuest -> {
                toMainActivity(GUEST_LOGGED)
            }
            R.id.ButtonSelectImageForUser -> {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, Consts.REQUEST_CODE)
            }
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Consts.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageUri = data.data.toString()
        }
    }

    fun toMainActivity(role: String) {
        when (role) {
            ADMIN_LOGGED -> startActivity(MainActivity.newIntentAdminLogged(this))
            USER_LOGGED -> startActivity(MainActivity.newIntentUserLogged(this))
            GUEST_LOGGED -> startActivity(MainActivity.newIntentGuestLogged(this))
            else -> throw RuntimeException("Unknown user status from DB")
        }
    }


    //функция проверки полей на допустимые значения
    fun fieldsIsCorrectRegister(): Boolean {
        binding.apply {
            var name = parseName(EditTextName.text.toString())
            var phone = parsePhone(EditTextPhone.text!!.toString())
            var image = imageUri
            var email = parseEmail(EditTextEmail.text!!.toString())
            var password = parsePassword(EditTextPassword.text!!.toString())

            return name.isNotEmpty() && phone.isNotEmpty() && image.isNotEmpty()
                    && email.isNotEmpty() && password.isNotEmpty()
        }
    }

    private fun parsePhone(InputPhone: String): String {
        return InputPhone.trim()
    }

    fun fieldsIsCorrectLogin(): Boolean {
        var email = parseEmail(binding.EditTextEmail.text!!.toString())
        var password = parsePassword(binding.EditTextPassword.text!!.toString())

        return email.isNotEmpty() && password.isNotEmpty()
    }

    //Блок приведения значений к нормальному виду

    fun parseEmail(inputEmail: String): String {
        return inputEmail.trim()
    }

    fun parseName(InputName: String): String {
        return InputName.trim()
    }

    fun parsePassword(InputPassword: String): String {
        return InputPassword.trim()
    }


}




