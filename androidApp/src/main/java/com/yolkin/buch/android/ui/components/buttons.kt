package com.yolkin.buch.android.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    textButton: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            disabledBackgroundColor = MaterialTheme.colors.primaryVariant
        ),
        enabled = enabled,
        modifier = Modifier.widthIn(min=87.dp).heightIn(min = 33.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = textButton,
            fontSize = 16.sp,
            color = MaterialTheme.colors.background
        )
    }
}

@Preview
@Composable
fun Preview_DefaultButton() {
    DefaultButton(
        textButton = "Войти"
    ) {

    }
}