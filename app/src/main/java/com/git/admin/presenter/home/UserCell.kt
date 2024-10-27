package com.git.admin.presenter.home

import android.text.Highlights
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.git.admin.domain.model.User
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.git.admin.presenter.component.AppImageLoader
import com.git.admin.presenter.component.AppText
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.Dimens

@Composable
fun UserCell(
    modifier: Modifier = Modifier,
    avatarUrl: String?,
    name: String?,
    highlights: @Composable (() -> Unit)? = null,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(AppColor.White)
            .padding(vertical = Dimens.dp10),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColor.White,
            contentColor = AppColor.White
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .background(AppColor.White)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar placeholder
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(AppColor.GrayLight)
            ) {
                AppImageLoader(imageUrl = avatarUrl ?: "")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                AppText(
                    rawText = name ?: "",
                    size = Dimens.sp20,
                    color = AppColor.Black,
                    weight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (highlights != null) {
                    highlights()
                }
            }
        }
    }
}