package com.git.admin.presenter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.git.admin.R
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens
import com.git.admin.util.ScreenUtils.isTablet
import com.git.admin.util.alpha

data class Segment(
    val text: String,
    val icon: Int? = null
)

enum class SegmentState {
    SELECTED, UNSELECTED;

    fun textColor(): Color {
        return when (this) {
            SELECTED -> AppColor.Dark
            UNSELECTED -> AppColor.Dark.alpha(0.4f)
        }
    }

    fun iconColor(): Color {
        return when (this) {
            SELECTED -> AppColor.GreenLight
            UNSELECTED -> AppColor.Dark.alpha(0.4f)
        }
    }

    fun bgColor(): Color {
        return when (this) {
            SELECTED -> AppColor.White
            UNSELECTED -> AppColor.Clear
        }
    }
}

@Composable
fun AppSegmentControl(
    modifier: Modifier = Modifier,
    options: List<Segment>,
    selectedOption: Segment,
    onOptionSelected: (Int) -> Unit
) {
    var selected by remember { mutableStateOf(selectedOption) }

    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(Dimens.dp10))
            .fillMaxWidth()
            .background(AppColor.GrayExtraLight)
            .padding(Dimens.dp10),
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp10),
    ) {
        for ((index, option) in options.withIndex()) {
            val state = if (option == selected) SegmentState.SELECTED else SegmentState.UNSELECTED
            val m: Modifier = if (isTablet()) Modifier else Modifier.weight(1f)
            SegmentItem(
                modifier = m.clickable {
                    selected = option
                    onOptionSelected(index)
                },
                option = option,
                state = state
            )
        }
    }
}
@Composable
private fun SegmentItem(
    modifier: Modifier = Modifier,
    option: Segment,
    state: SegmentState
) {
    val m: Modifier = if (isTablet()) {
        modifier
            .clip(shape = RoundedCornerShape(Dimens.dp10))
            .width(Dimens.dp240)
            .background(state.bgColor())
            .padding(Dimens.dp15)
    } else {
        modifier
            .clip(shape = RoundedCornerShape(Dimens.dp10))
            .background(state.bgColor())
            .padding(Dimens.dp10)
    }

    Box(
        modifier = m,
        contentAlignment = Alignment.Center
    ) {
        SegmentItemContent(option, state)
    }
}

@Composable
private fun SegmentItemContent(
    option: Segment,
    state: SegmentState
) {
    val textSize = if (isTablet()) Dimens.sp16 else Dimens.sp14
    val iconSize = if (isTablet()) Dimens.dp20 else Dimens.dp12

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp4)
    ) {
        option.icon?.let {
            AppIcon(
                iconDrawable = it,
                customColor = state.iconColor()
            )
        }
        AppText(
            rawText = option.text,
            color = state.textColor(),
            size = textSize,
            weight = FontWeight.Bold,
            align = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
private fun AppSegmentControlPreview() {
    val options = listOf(
        Segment("Option 1", icon = R.drawable.ic_info),
        Segment("Option 2", icon = R.drawable.ic_love),
    )
    AppSegmentControl(modifier = Modifier.padding(Dimens.dp30), options = options, selectedOption = options.first()) {

    }
}
