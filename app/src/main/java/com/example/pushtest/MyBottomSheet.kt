package com.example.pushtest

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet1 by remember { mutableStateOf(false) }
    var showBottomSheet2 by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                showBottomSheet1 = true
            }
        ) {
            Text(
                text = "1번 바텀 시트 클릭",
                color = Color.White
            )
        }
    }

    HorizontalExample (
        sheetNum = 1,
        showBottomSheet = showBottomSheet1,
        showInnerBottomSheet = showBottomSheet2,
        sheetState = sheetState,
        content = "1번 바텀 시트",
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = {
            showBottomSheet1 = false
        }
    )

//    BottomSheet(
//        sheetNum = 2,
//        showBottomSheet = showBottomSheet2,
//        showInnerBottomSheet = null,
//        sheetState = sheetState,
//        content = "2번 바텀 시트",
//        modifier = Modifier.fillMaxWidth(),
//        onDismissRequest = {
//            showBottomSheet2 = false
//        }
//    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HorizontalExample(
    sheetNum: Int,
    showBottomSheet: Boolean,
    showInnerBottomSheet: Boolean?,
    content: String,
    sheetState: SheetState,
    modifier: Modifier,
    onDismissRequest: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    if (showBottomSheet) {
        coroutineScope.launch {
            sheetState.show()
        }

        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            containerColor = Color.Black,
            dragHandle = null
        ) {
            val pages = listOf(R.drawable.ic_example, R.drawable.ic_example, R.drawable.ic_example)
            val pagerState = rememberPagerState(
                pageCount = { 3 }
            )
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                }
            ) {
                pages.forEachIndexed { index, _ ->
                    Image(
                        painter = painterResource(pages[index]),
                        contentDescription = "탭 사진",
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .clickable {
                            coroutineScope.launch {
                                pagerState.scrollToPage(index)
                                Log.d("몇번", pagerState.currentPage.toString())
                            }
                        }
                    )

                }
            }

            HorizontalPager(
                state = pagerState
            ) { page ->
                Column(
                    modifier = Modifier.fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Log.d("page", page.toString())
                    Text(text = page.toString(),
                        color = Color.Black,
                        fontSize = 50.sp)
                }
            }
        }

    }
}

//@SuppressLint("CoroutineCreationDuringComposition")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BottomSheet(
//    sheetNum: Int,
//    showBottomSheet: Boolean,
//    showInnerBottomSheet: Boolean?,
//    content: String,
//    sheetState: SheetState,
//    modifier: Modifier,
//    onDismissRequest: () -> Unit
//) {
//    val coroutineScope = rememberCoroutineScope()
//    if (showBottomSheet) {
//        coroutineScope.launch {
//            sheetState.show()
//        }
//        Column(
//            modifier = modifier
//                .fillMaxWidth()
//                .background(color = Color.DarkGray)
//                .padding(20.dp),
//            verticalArrangement = Arrangement.Center
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = content,
//                    color = Color.White
//                )
//                Text(
//                    text = "DONE",
//                    modifier = Modifier.clickable {
//                        onDismissRequest
//                    },
//                    color = Color.White
//                )
//            }
//            Button(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(top = 10.dp),
//                onClick = {
//                }
//            ) {
//                Text(
//                    "2번 바텀 시트 클릭"
//                )
//            }
//            Spacer(modifier = Modifier.weight(1f))
//        }
//    }
//
//}

@Preview
@Composable
fun MyBottomSheetPreview() {
    MyBottomSheet()
}