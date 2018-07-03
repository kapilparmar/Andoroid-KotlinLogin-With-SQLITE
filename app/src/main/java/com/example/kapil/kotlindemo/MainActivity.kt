package com.example.kapil.kotlindemo

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.CursorAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.view.ViewGroup



/**
 * Created by Kapil on 3/31/2018.
 */
class MainActivity : AppCompatActivity() {
    var userName : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView : TextView = findViewById(R.id.tv_welcome) as TextView
        var textViewName : TextView = findViewById(R.id.text_name) as TextView
        var textViewAge : TextView = findViewById(R.id.text_age) as TextView
        var textViewGender : TextView = findViewById(R.id.text_gender) as TextView


        userName = intent.getStringExtra("userName").toString()
        textView.setText("WelCome $userName!!")

        var databaseHelper = DataBaseHelper(this)
        var lQuerry : String ="Select * from "+ databaseHelper.tableName+" where "+databaseHelper.USERNAME+" = '"+userName+"'"
        var cursor : Cursor = databaseHelper.GetDetaislCursor(lQuerry)


        if ( cursor.count>0){
            cursor.moveToFirst()
            do {
                textViewName.setText(" : " + cursor.getString(cursor.getColumnIndex(databaseHelper.FNAME))+" " + cursor.getString(cursor.getColumnIndex(databaseHelper.LNAME)))
                textViewAge.setText(" : " + cursor.getString(cursor.getColumnIndex(databaseHelper.AGE )))
                textViewGender.setText(" : " + cursor.getString(cursor.getColumnIndex(databaseHelper.GENDER)))

            }
            while (cursor.moveToNext())

        }

    }
}