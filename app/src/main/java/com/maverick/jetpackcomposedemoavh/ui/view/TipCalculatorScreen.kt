package com.maverick.jetpackcomposedemoavh.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maverick.jetpackcomposedemoavh.ui.theme.JetpackComposeDemoAVHTheme
import java.text.DecimalFormat

@Composable
fun TipCalculatorScreen() {

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

        TipCalculator()

    }

}

@Composable
fun TipCalculator() {

    val amount = remember {
        mutableStateOf("")
    }

    val percentCounter = remember {
        mutableStateOf(1)
    }

    val tipPercentage = remember {
        mutableStateOf(0f)
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        TotalHeader(
            formatTwoDecimalPoints(
                getTotalHeaderAmount(
                    amount.value,
                    personCounter = percentCounter.value,
                    tipPercentage = tipPercentage.value
                )
            )
        )

        UserInputArea(
            amount = amount.value,
            amountChange = {
                amount.value = it
            },
            personCounter = percentCounter.value,
            onAddOrReducePerson = {
                if (it < 0) {
                    if (percentCounter.value != 1) {
                        percentCounter.value--
                    }
                } else {
                    percentCounter.value++
                }
            },
            tipPercentage.value,
            {
                tipPercentage.value = it
            }
        )

    }
}

@Composable
fun TotalHeader(amount: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        color = Color.Gray,
        shape = RoundedCornerShape(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Total per Person", style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "$$amount", style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            )

        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserInputArea(
    amount: String,
    amountChange: (String) -> Unit,
    personCounter: Int,
    onAddOrReducePerson: (Int) -> Unit,
    tipPercentage: Float,
    tipPercentageChange: (Float) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 12.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = amount,
                onValueChange = {
                    amountChange.invoke(it)
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Enter Your Amount")
                },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                })
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (amount.isNotBlank()) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = "Split", style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.fillMaxWidth(.50f))

                    CustomRoundButton(Icons.Default.KeyboardArrowUp) {
                        onAddOrReducePerson.invoke(+1)
                    }

                    Text(
                        text = personCounter.toString(),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    CustomRoundButton(Icons.Default.KeyboardArrowDown) {
                        onAddOrReducePerson.invoke(-1)
                    }

                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = "Tip", style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.fillMaxWidth(0.70f))
                    Text(
                        text = "$${formatTwoDecimalPoints(getTipAmount(amount, tipPercentage))}",
                        style = MaterialTheme.typography.body1
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${formatTwoDecimalPoints(tipPercentage.toString())}%",
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.height(8.dp))

                Slider(
                    value = tipPercentage,
                    onValueChange = {
                        tipPercentageChange.invoke(it)
                    },
                    valueRange = 0f..10f,
                    steps = 9,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                )

            }

        }
    }
}

@Composable
fun CustomRoundButton(imageVector: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp)
            .clickable {
                onClick.invoke()
            }, shape = CircleShape
    ) {
        Icon(
            imageVector = imageVector, contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .padding(4.dp)
        )
    }
}

fun getTipAmount(userAmount: String, tipPercentage: Float): String {
    return when {
        userAmount.isEmpty() -> {
            "0"
        }
        else -> {
            val amount = userAmount.toFloat()
            (amount * tipPercentage.div(100)).toString()
        }
    }
}

fun getTotalHeaderAmount(amount: String, personCounter: Int, tipPercentage: Float): String {
    return when {
        amount.isEmpty() -> {
            "0"
        }
        else -> {
            val userAmount = amount.toFloat()
            val tipAmount = userAmount * tipPercentage.div(100)
            val perHeadAmount = (userAmount + tipAmount).div(personCounter)
            perHeadAmount.toString()
        }
    }
}

fun formatTwoDecimalPoints(str: String): String {
    return if (str.isEmpty()) {
        ""
    } else {
        val format = DecimalFormat("###############.##")
        format.format(str.toFloat())
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorScreenPreview() {
    JetpackComposeDemoAVHTheme {
        TipCalculatorScreen()
    }
}