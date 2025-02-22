package dev.korryr.bongesha.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.korryr.bongesha.R
import dev.korryr.bongesha.ui.theme.gray01
import dev.korryr.bongesha.ui.theme.orange28

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bongatextfield(
    label: String,
    isPassword: Boolean = false,
    showPassword: Boolean = false,
    boldLabel: Boolean = true,
    fieldDescription: String,
    isValid: Boolean = true,
    isLongText: Boolean = false,
    input: String,
    trailing: Painter? = null,
    leading: Painter? = null,
    onTrailingIconClicked: (() -> Unit)? = null,
    hint: String,
    onChange: (String) -> Unit,
    errorMessage: String = "Error occurred",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onDone: () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions? = null
) {
    val current = LocalFocusManager.current
    val softwareKeyboard = LocalSoftwareKeyboardController.current
    var passwordVisible by rememberSaveable { mutableStateOf(showPassword) }
    var passwordField by rememberSaveable { mutableStateOf(input) }

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = label,
            color = Color.DarkGray,
            fontWeight = if (boldLabel) FontWeight.Bold else FontWeight.Normal,
        )
        if (fieldDescription.isNotEmpty()) {
            Text(
                text = fieldDescription,
                color = if (isValid) Color.Green else Color.Red
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                singleLine = !isLongText,
                value = input,
                enabled = enabled,
                readOnly = readOnly,
                isError = !isValid,
                onValueChange = {
                    onChange(it)
                },
                modifier = Modifier
                    .weight(1f)
                    .height(if (isLongText) 150.dp else TextFieldDefaults.MinHeight)
                    .fillMaxWidth()
                    .testTag(label + "1"),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledPlaceholderColor = Color.Red,
                    focusedBorderColor = orange28,
                    unfocusedBorderColor = Color.Transparent,
                    containerColor = Color.White,
                    unfocusedLabelColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Blue,
                    disabledTextColor = Color.Red,
                    errorTextColor = Color.Red,
                    errorContainerColor = Color.Red,
                    cursorColor = Color.Black,
                    errorCursorColor = Color.Red,
                    disabledBorderColor = Color.Red,
                    errorBorderColor = Color.Red,
                    focusedLeadingIconColor = Color.Gray,
                    unfocusedLeadingIconColor = Color.LightGray,
                    disabledLeadingIconColor = Color.Red,
                    errorLeadingIconColor = Color.Red,
                    focusedTrailingIconColor = Color.Red,
                    unfocusedTrailingIconColor = Color.Red,
                    disabledTrailingIconColor = Color.Red,
                    errorTrailingIconColor = Color.Red,
                    focusedLabelColor = Color.Red,
                    disabledLabelColor = Color.Red,
                    errorLabelColor = Color.Red,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.DarkGray,
                    errorPlaceholderColor = Color.Red,
                    focusedSupportingTextColor = Color.Red,
                    unfocusedSupportingTextColor = Color.Red,
                    disabledSupportingTextColor = Color.Red,
                    errorSupportingTextColor = Color.Red,
                    focusedPrefixColor = Color.Red,
                    unfocusedPrefixColor = Color.Red,
                    disabledPrefixColor = Color.Red,
                    errorPrefixColor = Color.Red,
                    focusedSuffixColor = Color.Red,
                    unfocusedSuffixColor = Color.Red,
                    disabledSuffixColor = Color.Red,
                    errorSuffixColor = Color.Red
                ),
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = if (isPassword && passwordVisible) {
                    keyboardOptions.copy(keyboardType = KeyboardType.Text)
                } else if (isPassword) {
                    keyboardOptions.copy(keyboardType = KeyboardType.Password)
                } else {
                    keyboardOptions
                },
                visualTransformation = if (isPassword && !passwordVisible) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                placeholder = {
                    Text(
                        text = hint,
                        textAlign = TextAlign.Center,
                        color = Color.LightGray
                    )
                },
                keyboardActions = keyboardActions ?: KeyboardActions(
                    onNext = {
                        current.moveFocus(FocusDirection.Next)
                    },
                    onDone = {
                        softwareKeyboard?.hide()
                        onDone()
                    }
                ),
                trailingIcon = if (isPassword) {
                    {
                        IconButton(
                            onClick = {
                                passwordVisible = !passwordVisible
                            }
                        ) {
                            Icon(
                                painter = if (passwordVisible) {
                                    painterResource(id = R.drawable.hide_icon)
                                } else {
                                    trailing!!
                                },
                                contentDescription = if (passwordVisible) {
                                    "Hide password"
                                } else {
                                    "Show password"
                                }
                            )
                        }
                    }
                } else null,
                leadingIcon =
                if (leading == null) null else {
                    {
                        Image(
                            painter = leading,
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    onTrailingIconClicked?.invoke()
                                },
                            colorFilter = ColorFilter.tint(orange28),
                        )
                    }
                }
            )
        }
    }
}
