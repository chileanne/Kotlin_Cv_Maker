package cub.sys360.kotlincvmaker.presentation.templates.cv_template

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CvTemplateOne(){

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        /** name **/

        Text(
            text = "Sanjana Singh" )

        Spacer(modifier = Modifier.height(8.dp))

        /** Address, phone no, email **/

        Text(
            text = "567 Toronto Street ·Toronto, ON M4Y 2T9 Canada ·  Tel: (416) 123-4567 · email: s.singh@utoronto.ca",


        )

//        Row {
//            Text("567 Toronto Street ·Toronto, ON M4Y 2T9 Canada · ")
//            Text(text = "Tel: (416) 123-4567 ·")
//            Text(text = "email: s.singh@utoronto.ca")
//
//        }

        Spacer(modifier = Modifier.height(14.dp))

        
        /** Education **/
        Row {
            Text("Doctor of Engineering, ")
            Text(text = "University of Benin")
            Spacer(modifier = Modifier.fillMaxWidth(0.2f))
            Text(text = "2020")

        }

    }

}