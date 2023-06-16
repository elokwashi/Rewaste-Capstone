package com.example.capstoneduasatu

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.capstoneduasatu.databinding.ActivityDeteksiBinding
import com.example.capstoneduasatu.ml.Pretrainedmodel
import com.example.capstoneduasatu.viewmodel.InRecycleViewModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class DeteksiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeteksiBinding
    lateinit var selectButton: CardView
    lateinit var predButton: Button
    lateinit var resView: TextView
    lateinit var imageView: ImageView
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeteksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        selectButton = binding.buttonCamera
        predButton = binding.buttonDeteski
        resView = binding.textView9
        imageView = binding.imageView2

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(150, 150, ResizeOp.ResizeMethod.BILINEAR))
            .build()

        selectButton.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        predButton.setOnClickListener {

            var tensorImage = TensorImage(DataType.FLOAT32)
            tensorImage.load(bitmap)

            tensorImage = imageProcessor.process(tensorImage)

            val model = Pretrainedmodel.newInstance(this)

            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 150, 150, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(tensorImage.buffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

            var maxIdx = 0
            outputFeature0.forEachIndexed { index, fl ->
                if (outputFeature0[maxIdx] < fl) {
                    maxIdx = index
                }
            }

            resView.setText(maxIdx.toString())
            when (maxIdx) {
                0 ->
                {
                    resView.setText("Non-Recyclable")
                    val intent = Intent(this, InNonRecycleActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    resView.setText("Organic")
                    val intent = Intent(this, InOrganikActivity::class.java)
                    startActivity(intent)
                }
                2 ->
                {
                    resView.setText("Recyclable")
                    val intent = Intent(this, InRecycleActivity::class.java)
                    startActivity(intent)
                }
                else -> resView.setText("Unknown")
            }
            model.close()

        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100) {
            var uri = data?.data;
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            imageView.setImageBitmap(bitmap)
        }
    }
}