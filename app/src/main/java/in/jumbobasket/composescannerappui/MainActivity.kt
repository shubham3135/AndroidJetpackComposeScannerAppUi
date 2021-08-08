package `in`.jumbobasket.composescannerappui

import `in`.jumbobasket.composescannerappui.ui.theme.ComposeScannerAppUiTheme
import `in`.jumbobasket.composescannerappui.ui.theme.ItemBackgroundColor
import `in`.jumbobasket.composescannerappui.ui.theme.ScreenColor
import `in`.jumbobasket.composescannerappui.ui.theme.SubtitleColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeScannerAppUiTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .padding(start = 20.dp, end = 20.dp,
                top = 20.dp, bottom = 50.dp)
            .fillMaxHeight()
        ) {
            HeaderTitle(text = "Find Anything")
            HeaderTitle(text = "With Image")
            HeaderTitle(text = "Let's Go")
            Spacer(modifier = Modifier.height(20.dp))
            OptionSection()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Recent Scan",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Box(modifier = Modifier.fillMaxHeight()){
                ScanBody()
            }
        }
        BottomMenu(items = listOf(
            BottomMenuContent(R.drawable.ic_home),
            BottomMenuContent(R.drawable.ic_location),
            BottomMenuContent(R.drawable.ic_file),
            BottomMenuContent(R.drawable.ic_settings),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun HeaderTitle(text: String) {
    Text(text = text, fontWeight = FontWeight.Bold, fontSize = 26.sp)
}

@Composable
fun OptionItem(icon: Int, text: String) {
    val isSelected = remember{ mutableStateOf(false)}
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = if (isSelected.value) ItemBackgroundColor
            else ScreenColor)
            .clickable { isSelected.value = !isSelected.value }
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                Modifier.size(36.dp),
                tint = if (isSelected.value) ScreenColor
                else Color.Black
            )
            Text(
                text = text,
                color = if (isSelected.value) ScreenColor
                else Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Composable
fun ScanBody() {
    Box{
        LazyColumn{
            items(10){
                ScanItem(image = R.drawable.pic6)
            }
        }
    }
}

@Composable
fun ScanItem(image: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { }
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp))
        ){
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "453 Result",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 2.dp)
            )
            Text(
                text = "More than 95% match result for this image",
                color = SubtitleColor,
                fontSize = 13.sp
            )
            Text(
                text = "Scan again",
                color = ItemBackgroundColor,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                modifier = Modifier.padding(vertical = 2.dp),
            )
        }
    }
}

@Composable
fun OptionSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OptionItem(
            icon = R.drawable.ic_scanner,
            text = "Scan"
        )
        OptionItem(icon = R.drawable.ic_camera, text = "Camera")
        OptionItem(icon = R.drawable.ic_gallery, text = "Gallery")
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OptionItem(icon = R.drawable.ic_file, text = "File")
        OptionItem(icon = R.drawable.ic_location, text = "Location")
        OptionItem(icon = R.drawable.ic_store, text = "Store")
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    onItemClick: ()->Unit
) {
    Box(
        modifier = Modifier
            .width(30.dp)
            .clickable {
                onItemClick()
            }
    ){
        Column(
            modifier = Modifier.width(30.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .width(30.dp)
                    .background(color = if (isSelected)
                        ItemBackgroundColor else Color.White)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = "",
                tint = if (isSelected) ItemBackgroundColor
                else SubtitleColor,
                modifier =  Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .background(ScreenColor)
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeScannerAppUiTheme {
        HomeScreen()
    }
}