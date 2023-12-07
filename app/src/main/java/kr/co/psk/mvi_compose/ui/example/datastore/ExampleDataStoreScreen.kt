package kr.co.psk.mvi_compose.ui.example.datastore

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kr.co.psk.common.R
import org.orbitmvi.orbit.compose.collectAsState

@Composable
internal fun ExampleDataStoreScreen(viewModel : ExampleDataStoreViewModel) {
    val state by viewModel.collectAsState()
    val testString = state.testString
    PreferencesSection(
        testString = testString,
        onClickUpdate = viewModel::setPreferencesTestString
    )
}

@Composable
private fun PreferencesSection(
    testString : String,
    onClickUpdate : (String) -> Unit
) {
    var updateString by remember{ mutableStateOf(testString) }
    Column {
        Text(
            text = "${stringResource(id = R.string.preferences)} : $testString"
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = updateString,
            onValueChange = {text ->
                updateString = text
            }
        )
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = {
                onClickUpdate(updateString)
            }
        ) {
            Text(text = stringResource(id = R.string.update))
        }

    }
}