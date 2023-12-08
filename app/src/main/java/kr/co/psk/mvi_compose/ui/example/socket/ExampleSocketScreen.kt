package kr.co.psk.mvi_compose.ui.example.socket

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.psk.common.CommonUtils
import kr.co.psk.common.R
import kr.co.psk.mvi_compose.common.OnLifecycleEvent
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun ExampleSocketScreen(viewModel: ExampleSocketViewModel) {
    OnLifecycleEvent(viewModel)
    val context = LocalContext.current
    val state by viewModel.collectAsState()
    val isConnectedSocket = state.isConnectedSocket
    val messageList by viewModel.messageList.collectAsState()
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is ExampleSocketSideEffect.ShowToast -> {
                CommonUtils.toast(context, sideEffect.message)
            }
        }
    }
    Column {
        MessageSection(
            isConnectedSocket = isConnectedSocket,
            onClickSendMessage = viewModel::sendMessage
        )
        MessageList(
            modifier = Modifier.weight(1f),
            messageList = messageList
        )
    }
}

@Composable
private fun MessageSection(
    isConnectedSocket: Boolean,
    onClickSendMessage: (String) -> Unit,
) {
    var message by remember { mutableStateOf("") }

    Text(text = stringResource(id = if (isConnectedSocket) R.string.connected else R.string.diconnected))
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(0.7f),
            value = message,
            onValueChange = { text ->
                message = text
            }
        )
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            modifier = Modifier.weight(0.3f),
            onClick = {
                onClickSendMessage(message)
                message = ""
            }
        ) {
            androidx.compose.material3.Text(text = stringResource(id = R.string.send))
        }
    }
}

@Composable
private fun MessageList(
    modifier : Modifier = Modifier,
    messageList : List<String>
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(messageList) { index, message ->
            Divider(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )
            Text(text = "${index + 1}. $message")
        }
    }
}