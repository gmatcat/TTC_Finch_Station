
<img src="/app_preview.png?raw=true" alt="Shimmer" /><br />
# App for displaying TTC Finch Station Stops/Routes/Schedules

Libraries used 
Coroutines/Retrofit2 for asynchronous api calls
Dagger+Hilt for dependency injection
MockK and Junit for Unit Testing
ReactiveNetwork realtime network connection/disconnection
JodaTime for datetime manipulation

Architecture flow (MVVM Clean Architecture + Repository Pattern)
View <-> ViewModel <-> Interactors/Use Cases <-> Repository <-> Local/Remote Data Source 

Additional features:
Stops with available routes are prioritized in listing
Displayed departure date/time in Toronto, Canada time
Has data object mapper that converts Network models to Business models (StationDataMapper.kt)
Activity sends out network connection/disconnection broadcasts to Fragments that need to know network connection state
eg. If network state is from disconnected to connected, fragment will automatically reload data
