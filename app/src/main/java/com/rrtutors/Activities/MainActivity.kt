package com.rrtutors.Activities

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rrtutors.Adapter.PersonAdapter
import com.rrtutors.Person
import com.rrtutors.R
import com.rrtutors.Viewmodel.QuizViewModel
import com.rrtutors.Viewmodel.ViewModelFactory
import org.jetbrains.anko.doAsync

private const val TAG1 = "onCreateOptionsMenu"
private const val TAG2 = "onOptionsItemSelected"

class MainActivity : AppCompatActivity() {

    private var ADD_REQ: Int = 42
    private var QUIZ_REQ: Int = 43


    lateinit var recyclerView: RecyclerView;
    private lateinit var viewmodel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        // Get the view model

        val modelfactory = ViewModelFactory(application)
        viewmodel = ViewModelProvider(this, modelfactory).get(QuizViewModel::class.java)


        // Specify layout for recycler view
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        recyclerView.layoutManager = linearLayoutManager

        doAsync {
            viewmodel.insert(Person("Andrew", BitmapFactory.decodeResource(resources, R.drawable.andrew)))
            viewmodel.insert(Person("Ari", BitmapFactory.decodeResource(resources, R.drawable.ari)))
            viewmodel.insert(Person("Billy", BitmapFactory.decodeResource(resources, R.drawable.billy)))
            viewmodel.insert(Person("Dana", BitmapFactory.decodeResource(resources, R.drawable.dana)))
            viewmodel.insert(Person("Eric", BitmapFactory.decodeResource(resources, R.drawable.eric)))
            viewmodel.insert(Person("Johnny Drama", BitmapFactory.decodeResource(resources, R.drawable.johnnydrama)))
            viewmodel.insert(Person("Lloyd", BitmapFactory.decodeResource(resources, R.drawable.lloyd)))
            viewmodel.insert(Person("Mrs. Ari", BitmapFactory.decodeResource(resources, R.drawable.mrsari)))
            viewmodel.insert(Person("Scott", BitmapFactory.decodeResource(resources, R.drawable.scott)))
            viewmodel.insert(Person("Shauna", BitmapFactory.decodeResource(resources, R.drawable.shauna)))
            viewmodel.insert(Person("Sloan", BitmapFactory.decodeResource(resources, R.drawable.sloan)))
            viewmodel.insert(Person("Turtle", BitmapFactory.decodeResource(resources, R.drawable.turtle)))
            viewmodel.insert(Person("Vince", BitmapFactory.decodeResource(resources, R.drawable.vince)))

        }


        // Data bind the recycler view
        viewmodel.allPersons.observe(this, Observer { persons ->
            // Data bind the recycler view
            recyclerView.adapter = PersonAdapter(persons)
        })




    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        Log.i(TAG1, "Menu created")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Add Picture to database
        if (item.itemId == R.id.miAdd) {
            // Navigate to AddActivity
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, ADD_REQ)
            Log.i(TAG2, "Add picture selected")
            return true
        }
        //Go to quiz
        if (item.itemId == R.id.miQuiz) {
            // Navigate to QuizActivity
            val intent = Intent(this, QuizActivity::class.java)
            startActivityForResult(intent, QUIZ_REQ)
            Log.i(TAG2, "Quiz item selected")
            return true
        }
        return super.onOptionsItemSelected(item)
    }




}


