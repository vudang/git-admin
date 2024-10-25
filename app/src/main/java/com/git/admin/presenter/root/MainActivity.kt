package com.git.admin.presenter.root

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.git.admin.presenter.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                Surface {
                    val windowSize = calculateWindowSizeClass(this)
                    requestedOrientation = when(windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        else -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    }

                    MainApp(windowSize = windowSize.widthSizeClass)
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun Preview() {
    AppTheme {
        Surface {
            MainApp(windowSize = WindowWidthSizeClass.Compact)
        }
    }
}