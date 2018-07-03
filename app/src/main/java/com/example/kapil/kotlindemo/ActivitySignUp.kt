package com.example.kapil.kotlindemo

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.*

class ActivitySignUp : AppCompatActivity() {

    var fName :String=""
    var lName : String =""
    var userName :String =""
    var password : String =""
    var age : Int? = null
    var gender :String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var databaseHelper : DataBaseHelper = DataBaseHelper(this)
        var contentValues :ContentValues = ContentValues()
        var editFName = findViewById(R.id.user_fName) as EditText
        var editLName = findViewById(R.id.user_lName) as EditText
        var editUsername = findViewById(R.id.user_name) as EditText
        var editPassword = findViewById(R.id.user_pass) as EditText
        var editAge = findViewById(R.id.user_age) as EditText

        var radioGroup = findViewById(R.id.rg_gender) as RadioGroup
        var rgGender = findViewById(R.id.rgMale) as RadioButton


        var btnSignUp : Button = findViewById(R.id.btn_login) as Button

        btnSignUp.setOnClickListener { v: View? ->

            fName = editFName.text.toString()
            lName = editLName.text .toString()
            userName = editUsername.text .toString()
            password = editPassword.text .toString()
            if (rgGender.isChecked)
            rgGender = findViewById(radioGroup.checkedRadioButtonId) as RadioButton

            if (!fName.isEmpty()  && !userName.isEmpty() && !password.isEmpty() && !editAge.text.toString().isEmpty() && !rgGender.text.toString().isEmpty() ){

                var lQuerry : String = "select ${databaseHelper.USERNAME} from ${databaseHelper.tableName}"
                var cursor : Cursor = databaseHelper.GetDetaislCursor(lQuerry)
                if (cursor.count>0)
                    editUsername.setError("Username is already registered")
                else {
                    contentValues.put(databaseHelper.FNAME, fName)
                    contentValues.put(databaseHelper.LNAME, lName)
                    contentValues.put(databaseHelper.USERNAME, userName)
                    contentValues.put(databaseHelper.PASSWORD, password)
                    contentValues.put(databaseHelper.AGE, editAge.text.toString().toInt())
                    contentValues.put(databaseHelper.GENDER, rgGender.text.toString())

                    databaseHelper.insertIntoTable(databaseHelper.tableName, contentValues)
                    Toast.makeText(this, "Registerd Succesfully", Toast.LENGTH_LONG).show()

                    var intent: Intent = Intent(this, ActivityLogin::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            else Toast.makeText(this, "fill all field", Toast.LENGTH_LONG).show()

        }
    }

    fun onRadioButtonClicked(view: View) {
        // Is the button now checked?
        val checked = (view as RadioButton).isChecked

        // Check which radio button was clicked
        when (view.getId()) {
            R.id.rgMale -> {
                if (checked)
                    gender = (view as RadioButton).text as String

            }
            R.id.rgFemale -> if (checked)
                gender = (view as RadioButton).text as String
        }
    }
}

