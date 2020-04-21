package by.bsuir.imagefromgallery

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.*

const val REQUEST_IMAGE_GET = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonGallery = findViewById<Button>(R.id.buttonOpenGallery) as Button
        buttonGallery.setOnClickListener {
            Toast.makeText(applicationContext, "clicked on button", Toast.LENGTH_SHORT).show()
            selectImage()
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        if (intent.resolveActivity(packageManager) != null){
            startActivityForResult(intent, REQUEST_IMAGE_GET)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_GET && resultCode == Activity.RESULT_OK) {
            val fullPhotoUri: Uri? = data!!.data
            val imageView = findViewById<ImageView>(R.id.imageViewFromGallery)
            imageView.setImageURI(fullPhotoUri)
            val textImageDescription = findViewById<TextView>(R.id.textImageDescription)
            textImageDescription.text = ""
        }
    }
}
