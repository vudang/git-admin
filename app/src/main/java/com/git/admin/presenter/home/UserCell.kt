package com.git.admin.presenter.home

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
    user: User,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(AppColor.White)
            .padding(horizontal = Dimens.dp20)
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
                AppImageLoader(imageUrl = user.avatarUrl ?: "")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                AppText(
                    rawText = user.login ?: "",
                    size = Dimens.sp24,
                    color = AppColor.Black,
                    weight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                AppText(
                    rawText = user.htmlUrl,
                    size = 16.sp,
                    color = AppColor.Blue
                )
            }
        }
    }
}