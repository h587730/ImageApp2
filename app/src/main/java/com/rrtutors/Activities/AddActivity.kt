package com.rrtutors.Activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rrtutors.Person
import com.rrtutors.R
import com.rrtutors.Viewmodel.QuizViewModel
import com.rrtutors.Viewmodel.ViewModelFactory
import org.jetbrains.anko.doAsync

private const val TAG1 = "openGalleryForImage"
private const val TAG2 = "setOnClickListener"
private const val TAG3 = "convertToBitmap"
private const val TAG4 = "ImageReturned"
private const val TAG5 = "PersonAddedToDB"
private const val TAG6 = "showIntent"

class AddActivity : AppCompatActivity() {

    private lateinit var btnSubmit: Button
    private lateinit var btnSelectImage: Button
    private lateinit var ivPreviewImage: ImageView
    private lateinit var etName: EditText

    private lateinit var viewmodel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val modelfactory = ViewModelFactory(application)
        viewmodel = ViewModelProvider(this, modelfactory).get(QuizViewModel::class.java)

        btnSubmit = findViewById(R.id.btnSubmit)
        btnSelectImage = findViewById(R.id.btnSelectImage)
        ivPreviewImage = findViewById(R.id.IvPreviewImage)
        etName = findViewById(R.id.etName)

        btnSelectImage.setOnClickListener{
            openGalleryForImage()
            Log.i(TAG3, "image converted")
        }

        btnSubmit.setOnClickListener {

            val name = etName.text.toString()
            val img = convertToBitmap(ivPreviewImage)

            doAsync{
                viewmodel.insert(Person(name, img))
                Log.i(TAG5, "Person added")
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 32) {
            ivPreviewImage.setImageURI(data?.data)
            Log.i(TAG4, "Image returned")

        }
    }

    private fun convertToBitmap(imageView: ImageView): Bitmap {
        val bm = (imageView.drawable as BitmapDrawable).bitmap
        return bm
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 32)
    }








}