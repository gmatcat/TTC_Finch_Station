
<img src="/app_preview.png?raw=true" alt="App Preview" /><br />
# App for displaying TTC Finch Station Stops/Routes/Schedules
</br>
Libraries used </br>
Coroutines/Retrofit2 for asynchronous api calls</br>
Dagger+Hilt for dependency injection</br>
MockK and Junit for Unit Testing</br>
ReactiveNetwork realtime network connection/disconnection</br>
JodaTime for datetime manipulation</br>
DataBinding</br>
Jetpack Navigation</br>
</br>
Architecture flow (MVVM Clean Architecture + Repository Pattern)</br>
View <-> ViewModel <-> Interactors/Use Cases <-> Repository <-> Local/Remote Data Source </br>
</br>
Additional features: </br>
Stops with available routes are prioritized in listing</br>
Data persistence on orientation change</br>
SwipeRefreshLayout in StopListView to reload data </br>
Displayed departure date/time in Toronto, Canada time</br>
Has data object mapper that converts Network models to Business models (StationDataMapper.kt)</br>
Activity sends out network connection/disconnection broadcasts to Fragments that need to know network connection state</br>
eg. If network state is from disconnected to connected, fragment will automatically reload data
