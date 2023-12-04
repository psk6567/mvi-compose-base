package kr.co.psk.mvi_compose.ui.example.room

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.psk.common.R
import kr.co.psk.domain.ui_model.RoomTestSampleListUiModel
import kr.co.psk.domain.ui_model.RoomTestUiModel
import kr.co.psk.domain.ui_model.UiStatus
import kr.co.psk.mvi_compose.common.LoadingIndicator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun ExampleRoomScreen(viewModel: ExampleRoomViewModel) {
    val context = LocalContext.current
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect{ sideEffect ->
        when (sideEffect) {
            is ExampleRoomSideEffect.ShowToast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = viewModel::insertTest

        ) {
            Text(text = stringResource(id = R.string.insert))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = viewModel::selectTest
        ) {
            Text(text = stringResource(id = R.string.select))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = viewModel::deleteTest
        ) {
            Text(text = stringResource(id = R.string.delete))
        }
        when (state.status) {
            UiStatus.Loading -> {
                LoadingIndicator()
            }

            UiStatus.IDLE -> {
                ExampleRoomListBody(state.uiModel)
            }

            is UiStatus.Failed -> {
            }
        }
    }
}

@Composable
private fun ExampleRoomListBody(roomTestUiModel: RoomTestUiModel) {
    Text(text = roomTestUiModel.count)
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(roomTestUiModel.sampleList) {item ->
            ExampleRoomListItem(item)
        }
    }
}


@Composable
private fun ExampleRoomListItem(item: RoomTestSampleListUiModel) {
    Column {
        Text(text = "id : ${item.id}")
        Text(text = "name : ${item.userName}")
        Text(text = "age : ${item.age}")
    }
    Divider(
        modifier= Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    )
}