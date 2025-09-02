package com.example.phpandroidstudiomysql.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.phpandroidstudiomysql.R
import com.example.phpandroidstudiomysql.modeldata.DataSiswa
import com.example.phpandroidstudiomysql.uicontroller.DestinasiNavigasi
import com.example.phpandroidstudiomysql.viewmodel.HomeViewModel
import com.example.phpandroidstudiomysql.viewmodel.PenyediaViewModel
import com.example.phpandroidstudiomysql.viewmodel.StatusUiSiswa


object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.app_name
}

    @Composable
    fun DataSiswaApp(navController: NavController = rememberNavController(), modifier: Modifier = Modifier) {
        HostNavigasi(
            navController = navController
        )
    }

    @Composable
    fun HostNavigasi(
        navController: NavController,
        modifier: Modifier = Modifier
    ) {
        NavHost(navController = navController, startDestination = DestinasiHome.route, modifier = modifier) {
            composable (DestinasiHome.route) {
                HomeScreen()
        }
    }
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreen(
        modifier: Modifier = Modifier,
        viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ) {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold (
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(DestinasiHome.titleRes)) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    scrollBehavior = scrollBehavior
                )
            },
            floatingActionButton = {
                FloatingActionButton1(
                    onClick = { },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(
                        dimensionResource(id = R.dimen.padding_large)
                    )
                )
                {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = stringResource(R.string.entry_siswa)
                    )
                }
            },
        ) { innerPadding ->
            HomeBody(
                statusUiSiswa = viewModel.statusUiSiswa,
                retryAction = viewModel::loadSiswa,
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
        }
    }

    @Composable
    fun HomeBody(
        statusUiSiswa: StatusUiSiswa,
        //onSiswaClick: (Int) -> Unit,
        retryAction: () -> Unit,
        modifier: Modifier = Modifier
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ){
            when(statusUiSiswa){
                is StatusUiSiswa.Loading -> LoadingScreen()
                is StatusUiSiswa.Success -> DaftarSiswa(itemSiswa = statusUiSiswa.siswa) //,
                //onSiswaClick = {onSiswaClick(it.id)} )
                is StatusUiSiswa.Error -> ErrorScreen(
                    retryAction,
                    modifier = modifier.fillMaxSize()
                )
            }
        }
    }

    @Composable
    fun LoadingScreen(modifier: Modifier = Modifier) {
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }

    @Composable
    fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.loading_failed))
            Button(onClick = retryAction) {
                Text(stringResource(R.string.retry))
            }
        }
    }


    @Composable
    fun DaftarSiswa(
        itemSiswa: List<DataSiswa>,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(modifier = modifier) {
            items(items = itemSiswa, key = { it.id }) { person ->
                ItemSiswa(
                    siswa = person,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }

    @Composable
    fun ItemSiswa(
        siswa: DataSiswa,
        modifier: Modifier = Modifier,

        ) {
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = siswa.nama,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                    )
                    Text(
                        text = siswa.telepon,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Text(
                    text = siswa.alamat,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }

