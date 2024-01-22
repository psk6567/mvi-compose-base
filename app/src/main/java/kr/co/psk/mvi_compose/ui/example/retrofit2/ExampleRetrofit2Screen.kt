package kr.co.psk.mvi_compose.ui.example.retrofit2

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kr.co.psk.common.R
import kr.co.psk.domain.ui_model.Retrofit2TestUiModel
import kr.co.psk.domain.ui_model.UiStatus
import kr.co.psk.mvi_compose.common.LoadingIndicator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun ExampleRetrofit2Screen(viewModel: ExampleRetrofit2ViewModel) {
    val context = LocalContext.current
    val state by viewModel.collectAsState()

    Log.e("Test Recomposition","Parent")
    viewModel.collectSideEffect{ sideEffect ->
        when (sideEffect) {
            is ExampleRetrofit2SideEffect.ErrorToast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonSection(
            onClickGetData = viewModel::getExampleData,
            onClickSideEffect =  viewModel::testSideEffect,
            onClickTestRecomposition = viewModel::testRecomposition
        )

        when (state.status) {
            UiStatus.Loading -> {
                LoadingIndicator()
            }
            UiStatus.IDLE -> {
//                ExampleRetrofit2ListBodyMVI(state.list)
                ExampleRetrofit2ListBody(
                    viewModel = viewModel
                )
            }

            is UiStatus.Failed -> {
            }
        }
    }
}

@Composable
private fun ButtonSection(
    onClickGetData : () -> Unit,
    onClickSideEffect : () -> Unit,
    onClickTestRecomposition : () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(0.6f),
        onClick = onClickGetData
    ) {
        Text(text = stringResource(id = R.string.get_data))
    }
    Button(
        modifier = Modifier.fillMaxWidth(0.6f),
        onClick = onClickSideEffect
    ) {
        Text(text = stringResource(id = R.string.sideffect_test))
    }

    Button(
        modifier = Modifier.fillMaxWidth(0.6f),
        onClick = onClickTestRecomposition
    ) {
        Text(text = stringResource(id = R.string.recomposition_test))
    }
}

@Composable
private fun ExampleRetrofit2ListBody(
    viewModel : ExampleRetrofit2ViewModel
) {
    val recompositionTestList by viewModel.recompositionTestList.collectAsState()
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = recompositionTestList,
            key = {item ->
                item.index
            }
        ) {item ->
            ExampleRetrofit2ListItem(item)
        }
    }
}

@Composable
private fun ExampleRetrofit2ListBodyMVI(
    list : ImmutableList<Retrofit2TestUiModel>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = list,
            key = {item ->
                item.index
            }
        ) {item ->
            ExampleRetrofit2ListItem(item)
        }
    }
}


@Composable
private fun ExampleRetrofit2ListItem(item : Retrofit2TestUiModel) {
    Log.e("Test recomposition Start",item.index)
    Column {
        Text(text = "${item.index} : ${item.title}")
        Text(text = item.body)
    }
    Divider(
        modifier= Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    )
    Log.e("Test recomposition End",item.index)
}