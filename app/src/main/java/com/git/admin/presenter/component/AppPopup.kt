package com.git.admin.presenter.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens

enum class AppPopupStyle {
    Warning,
    Error,
    Success;

    fun getTitle(): String {
        return when (this) {
            Warning -> "Warning"
            Error -> "OOP! Something went wrong"
            Success -> "Success"
        }
    }
}

@Composable
fun AppPopup(
    modifier: Modifier = Modifier,
    style: AppPopupStyle = AppPopupStyle.Warning,
    visible: Boolean,
    title: String? = null,
    message: String? = null,
    customActions: @Composable (() -> Unit)? = null,
    onDismiss: () -> Unit
) {
    if (visible) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 8.dp
            ) {
                Column(
                    modifier = modifier
                        .padding(vertical = Dimens.dp20)
                        .padding(horizontal = Dimens.dp30),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Dimens.dp10)
                ) {
                    AppText(
                        rawText = title ?: style.getTitle(),
                        size = Dimens.sp18,
                        color = AppColor.Black,
                        weight = FontWeight.Bold
                    )

                    AppText(
                        rawText = message,
                        size = Dimens.sp14,
                        color = AppColor.RedExtra,
                        weight = FontWeight.Normal
                    )

                    if (customActions != null) {
                        customActions()
                    } else {
                        AppButtonRegular(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = Dimens.dp30),
                            rawText = "Dismiss",
                            style = AppButtonStyle.BORDER,
                            onClick = onDismiss
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPopupOverlay() {
    var isPopupVisible by remember { mutableStateOf(true) }
    AppPopup(
        title = "Warning",
        message = "Buyer offer already active",
        visible = isPopupVisible,
        customActions = {
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.dp10)) {
                AppButtonRegular(
                    modifier = Modifier.weight(1f),
                    rawText = "Close",
                    style = AppButtonStyle.BORDER,
                    onClick = { isPopupVisible = false }
                )
                AppButtonRegular(
                    modifier = Modifier.weight(1f),
                    rawText = "Dismiss",
                    onClick = { isPopupVisible = false }
                )
            }
        },
    ) {
        isPopupVisible = false
    }
}
