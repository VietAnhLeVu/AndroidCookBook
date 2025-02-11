package com.example.androidcookbook.ui.features.recipedetail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcookbook.R
import com.example.androidcookbook.ui.features.newsfeed.PostHeader
import com.example.androidcookbook.ui.features.search.SamplePosts

enum class DetailState {
    Description,
    Ingredient,
    Recipe
}

@Composable
fun RecipeDetailScreen() {
    var state by remember { mutableStateOf(DetailState.Description) }
    var checkedStates = remember { mutableStateListOf(false, false, false) }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 15.dp),
    ) {
        item {
            PostHeader(
                author = SamplePosts.posts[0].author,
                createdAt = SamplePosts.posts[0].createdAt
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_4),
                    contentDescription = null,
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(5)),
                    contentScale = ContentScale.Crop,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedIconButton(icon = Icons.Outlined.FavoriteBorder) {

                }
                OutlinedIconButton(icon = Icons.Outlined.Email) {

                }
                Spacer(modifier = Modifier.weight(1f))
                OutlinedIconButton(icon = Icons.Outlined.Share) {

                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                LobsterTextButton(onclick = { state = DetailState.Description }, text = "Description")
                LobsterTextButton(onclick = { state = DetailState.Ingredient }, text = "Ingredient")
                LobsterTextButton(onclick = { state = DetailState.Recipe }, text = "Recipe")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 40.dp)
            ) {
                when (state) {
                    DetailState.Description -> {
                        Text(
                            text = SamplePosts.posts[0].description,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                    DetailState.Ingredient -> {
                        checkedStates.forEachIndexed { index, checked ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Canvas(modifier = Modifier.size(12.dp)) {
                                    drawCircle(Color.Black)
                                }
                                Text(text = "Ingredient ${index + 1}", fontSize = 20.sp)
                                Checkbox(
                                    checked = checked,
                                    onCheckedChange = { isChecked ->
                                        checkedStates[index] = isChecked
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color(101,85,143)
                                    )
                                )
                            }
                        }
                    }
                    DetailState.Recipe -> {
                        Text(
                            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not.",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OutlinedIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onclick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onclick
    ) {
        Icon(
            icon,
            contentDescription = null
        )
    }
}

@Composable
fun LobsterTextButton(
    modifier: Modifier = Modifier,
    onclick: () -> Unit,
    text: String
) {
    Button(
        modifier = modifier
            .wrapContentHeight()
            .width(110.dp)
            .padding(5.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFFF7F63)),
        shape = RoundedCornerShape(size = 999.dp),
        onClick = onclick
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.lobster_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFBFB),
            )
        )
    }
}

@Preview
@Composable
fun RecipePreview() {
    RecipeDetailScreen()
}