package com.vhuthu.until

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vhuthu.until.ui.theme.UntilTheme

class MainActivity : ComponentActivity() {
    val vm = TodoViewModel()
    val mainViewModel by viewModels<TodoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UntilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // TodoView(vm)
                    MovieList(movieList = mainViewModel.todoList)
                    mainViewModel.getTodoList()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoView(vm: TodoViewModel) {

    LaunchedEffect(Unit, block = {
        vm.getTodoList()
    })




    LazyColumn(){
        items(vm.todoList){tod ->

Column(modifier = Modifier.fillMaxWidth()) {


    val farmz = tod.data.farms[0].incomeStatements
    
    Text(text = farmz.toString())

//    Text(text = farmz.id.toString())
//    Text(text = tod.data.farms[0].incomeStatements[0].incomeStatementItems[0].description)
//    Text(text = tod.data.farms[0].incomeStatements[0].incomeStatementItems[0].category)
//    Text(text = tod.data.farms[0].incomeStatements[0].incomeStatementItems[0].date)
//    Text(text = tod.data.farms[0].incomeStatements[0].incomeStatementItems[0].amount.toString())
    Divider()
}
        }
    }
}

@Composable
fun MovieList(movieList: List<FarmerResponse>) {
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyColumn {
        itemsIndexed(items = movieList) { index, item ->
            MovieItem(farmerResponse = item, index, selectedIndex) { i ->
                selectedIndex = i
            }
        }
    }

}

@Composable
fun MovieItem(farmerResponse: FarmerResponse, index: Int, selectedIndex: Int, onClick: (Int) -> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .clickable {
                onClick(index)
                Log.d("item", index.toString())
            }
            .height(200.dp)
        , shape = RoundedCornerShape(8.dp)
    ) {
        Surface() {

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 15.dp, end = 4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {

                   for (farm in farmerResponse.data.farms){
                       for (incomeStatement in farm.incomeStatements){
                           for (incomeStatementItem in incomeStatement.incomeStatementItems) {

                               val info = incomeStatementItem


                               Text(
                                   text = info.description,
                                   fontSize = 16.sp,
                                   fontWeight = FontWeight.Bold
                               )
                               Text(
                                   text = info.date,
                                   fontSize = 13.sp,color= Color.Black,
                                   modifier = Modifier
                                       .background(
                                           Color.Green
                                       )
                                       .padding(4.dp)
                               )
                               Text(
                                   text = info.amount.toString(),
                                   fontSize = 14.sp,
                                   maxLines = 4,
                               )

                               Divider()
                           }
                       }
                   }


                }

        }
    }

}