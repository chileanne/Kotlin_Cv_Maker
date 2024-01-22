package cub.sys360.kotlincvmaker.test


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.graphics.pdf.PdfRenderer
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cub.sys360.kotlincvmaker.R
import cub.sys360.kotlincvmaker.ui.theme.Purple40
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34

private fun foregroundPermissionApproved(context: Context): Boolean {
    val writePermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val readPermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, Manifest.permission.READ_EXTERNAL_STORAGE
    )

    return writePermissionFlag && readPermissionFlag
}

private fun requestForegroundPermission(context: Context) {
    val provideRationale = foregroundPermissionApproved(context = context)
    if (provideRationale) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    } else {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pdd(){
    val context = LocalContext.current
    requestForegroundPermission(context)
    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Generate PDF File",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier.padding(it).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        generatePDF2(context, getDirectory())
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(60.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(Purple40)
                ) {
                    Text(
                        text = "Submit",
                        color = Color.Blue,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}


private fun getDirectory(): File {


    return File(Environment.getExternalStorageDirectory(), "GFG.pdf");


//    val mediaDir = extern.firstOrNull()?.let {
//        File(it, resources.getString(R.string.app_name)).apply { mkdir }
//    }
//    return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
}


fun generatePDF(context: Context, directory: File) {
    val pageHeight = 1120
    val pageWidth = 792
    val pdfDocument = PdfDocument()
    val paint = Paint()
    val title = Paint()
    val myPageInfo = PageInfo.Builder(pageWidth, pageHeight, 1).create()
    val myPage = pdfDocument.startPage(myPageInfo)
    val canvas: Canvas = myPage.canvas
    val bitmap: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.kl))
    val scaleBitmap: Bitmap? = Bitmap.createScaledBitmap(bitmap!!, 120, 120, false)
    canvas.drawBitmap(scaleBitmap!!, 40f, 40f, paint)
    title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    title.textSize = 15f
    title.color = ContextCompat.getColor(context, R.color.purple_200)
    canvas.drawText("Jetpack Compose", 400f, 100f, title)
    canvas.drawText("Make it Easy", 400f, 80f, title)
    title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
    title.color = ContextCompat.getColor(context, R.color.purple_200)
    title.textSize = 15f
    title.textAlign = Paint.Align.CENTER
    canvas.drawText("This is sample document which we have created.", 396f, 560f, title)
    pdfDocument.finishPage(myPage)

   // val file = File(directory, "sample.pdf")

    val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "GFG.pdf");

    try {
       // pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.writeTo(FileOutputStream(file))
        Toast.makeText(context, "PDF file generated successfylly", Toast.LENGTH_SHORT).show()
    } catch (ex: IOException) {
        ex.printStackTrace()
        Log.d("Permission prob",  ex.message.toString())
        Toast.makeText(context, "${ex.message.toString()}", Toast.LENGTH_SHORT).show()
    }
    pdfDocument.close()



}


fun generatePDF2(context: Context, directory: File) {
    val pageHeight = 1120
    val pageWidth = 800
    val pdfDocument = PdfDocument()
    val paint = Paint()
    val title = Paint()
    val myPageInfo = PageInfo.Builder(pageWidth, pageHeight, 1).create()
    val myPage = pdfDocument.startPage(myPageInfo)
    val canvas: Canvas = myPage.canvas
   // val bitmap: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.kl))
   // val scaleBitmap: Bitmap? = Bitmap.createScaledBitmap(bitmap!!, 120, 120, false)
   // canvas.drawBitmap(scaleBitmap!!, 40f, 40f, paint)

    title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    title.textSize = 15f
    title.color = ContextCompat.getColor(context, R.color.purple_200)
//    canvas.drawText("Sunsing Koshak", 400f, 20f, title)
//    canvas.drawText("567 Toronto Street ·Toronto, ON M4Y 2T9 Canada ·", 50f, 40f, title)
//    canvas.drawText("Tel: (416) 123-4567 · email: s.singh@utoronto.ca", 100f, 40f, title)
//    canvas.drawText("email: s.singh@utoronto.ca", 70f, 40f, title)

     canvas.drawText("567 Toronto Street ·Toronto, ON M4Y 2T9 Canada ·  Tel: (416) 123-4567 · email: s.singh@utoronto.ca", 100f, 40f, title)

    title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
    title.color = ContextCompat.getColor(context, R.color.purple_200)
    title.textSize = 15f
    title.textAlign = Paint.Align.CENTER
    canvas.drawText("This is sample document which we have created.", 396f, 560f, title)


    title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
    title.color = ContextCompat.getColor(context, R.color.black)
    title.textSize = 15f
    title.textAlign = Paint.Align.CENTER

    canvas.drawText(".", 10f, 20f, title)
    canvas.drawText("University of benin", 60f, 60f, title)
    pdfDocument.finishPage(myPage)

    // val file = File(directory, "sample.pdf")

    val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "kGFG.pdf");

    try {
        // pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.writeTo(FileOutputStream(file))
        Toast.makeText(context, "PDF file generated successfylly", Toast.LENGTH_SHORT).show()
    } catch (ex: IOException) {
        ex.printStackTrace()
        Log.d("Permission prob",  ex.message.toString())
        Toast.makeText(context, "${ex.message.toString()}", Toast.LENGTH_SHORT).show()
    }
    pdfDocument.close()
}

fun drawableToBitmap(drawable: Drawable): Bitmap? {
    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

















