package com.git.admin.presenter.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.git.admin.R
import com.git.admin.presenter.theme.AppColor
import com.git.admin.util.AppFont
import com.git.admin.util.Dimens
import com.git.admin.util.ScreenUtils
import com.git.admin.util.getRulePassword
import com.git.admin.util.isValidEmail
import com.git.admin.util.isValidPassword
import com.git.admin.util.isValidPhoneNumber

enum class ValidationRule {
    NONE, EMAIL, PASSWORD, PHONE, REQUIRED;

    fun validateError(value: String): String? {
        val input = value.trim()
        return when(this) {
            NONE -> null
            EMAIL -> if(input.isValidEmail()) null else "Invalid email"
            PASSWORD -> if(input.isValidPassword()) null else input.getRulePassword()
            PHONE -> if(input.isValidPhoneNumber()) null else "Invalid phone number"
            REQUIRED -> if(input.isNotEmpty()) null else "Required"
        }
    }
}

enum class AppTextFieldStyle {
    FILL, BORDER;

    @Composable
    fun getLabelColor(): Color {
        return when(this) {
            FILL -> AppColor.White
            BORDER -> AppColor.Dark
        }
    }

    @Composable
    fun getTextFieldColors(): TextFieldColors {
        return OutlinedTextFieldDefaults.colors(
            focusedTextColor = AppColor.Black,
            focusedPlaceholderColor = AppColor.Dark,
            unfocusedPlaceholderColor = AppColor.Dark,
            focusedBorderColor = AppColor.Clear,
            unfocusedBorderColor = AppColor.Clear,
            disabledBorderColor = AppColor.Clear,
            disabledTextColor = AppColor.Gray,
            cursorColor = AppColor.Dark,
            errorTextColor = AppColor.RedExtra,
            errorBorderColor = AppColor.RedExtra
        )
    }
}

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    style: AppTextFieldStyle = AppTextFieldStyle.FILL,
    label: Int,
    initValue: String? = null,
    onValueChange: (String) -> Unit,
    placeHolder: Int = R.string.empty,
    validateRules: List<ValidationRule> = emptyList(),
    error: String? = null,
    secure: Boolean = false,
    enable: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val borderColor = if (style == AppTextFieldStyle.FILL) AppColor.Clear else AppColor.Light
    val tablet = ScreenUtils.isTablet()
    val height = if(tablet) Dimens.dp57 else Dimens.dp46
    val textSize = if(tablet) Dimens.sp14 else Dimens.sp12
    val spacing = if(tablet) Dimens.dp10 else Dimens.dp4

    // State Flow
    var passwordVisible by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf(initValue ?: "") }
    var errorMessage: String? by remember { mutableStateOf(error) }
    var isError by remember { mutableStateOf(error != null) }

    LaunchedEffect(initValue) {
        initValue?.let {
            value = it
        }
    }

    fun onValueChanged(input: String) {
        value = input
        onValueChange(input)

        validateRules.forEach {
            val err = it.validateError(input)
            isError = err != null
            if (isError) {
                errorMessage = err
                return
            }
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        if (label != R.string.empty) {
            AppText(
                text = label,
                color = style.getLabelColor(),
                size = Dimens.sp13,
                weight = FontWeight.SemiBold
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {
                    onValueChanged(it)
                },
                enabled = enable,
                colors = style.getTextFieldColors(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType
                ),
                visualTransformation = getVisualTransformation(secure, passwordVisible),
                trailingIcon = {
                    PasswordVisibilityButton(
                        secure = secure,
                        visible = passwordVisible,
                        click = { passwordVisible = !passwordVisible }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .background(AppColor.White, RoundedCornerShape(Dimens.dp15))
                    .border(
                        BorderStroke(Dimens.dp1, borderColor),
                        shape = RoundedCornerShape(Dimens.dp15)
                    ),
                textStyle = TextStyle(
                    fontFamily = AppFont.fontFamily,
                    fontSize = textSize,
                )
            )
            if (value.isEmpty()) {
                AppText(
                    text = placeHolder,
                    color = AppColor.Dark,
                    weight = FontWeight.ExtraLight,
                    size = textSize,
                    modifier = Modifier.padding(start = Dimens.dp16)
                )
            }
        }
        if (isError) {
            Spacer(modifier = Modifier.padding(top = Dimens.dp4))
            AppText(
                rawText = errorMessage,
                color = AppColor.RedExtra,
                size = textSize,
                weight = FontWeight.Light
            )
        }
    }
}

@Composable
private fun PasswordVisibilityButton(
    secure: Boolean = false,
    visible: Boolean,
    click: () -> Unit
) {
    if (secure) {
        IconButton(onClick = click) {
            AppIcon(
                iconDrawable = if (visible) R.drawable.ic_password_visible else R.drawable.ic_password_invisible,
                modifier = Modifier.size(Dimens.dp16),
            )
        }
    }
}


private fun getVisualTransformation(secure: Boolean, passwordVisible: Boolean): VisualTransformation {
    if (!secure) return VisualTransformation.None
    return if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
}

@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
fun AppTextFieldPreview() {
    AppTextField(
        modifier = Modifier.padding(horizontal = Dimens.dp20),
        style = AppTextFieldStyle.BORDER,
        enable = false,
        validateRules = listOf(ValidationRule.PASSWORD),
        label = R.string.empty,
        placeHolder = R.string.signin_email,
        onValueChange = {}
    )
}