# Pokemon Directory
A small piece of work to show off my Android development skills

## Technical skills used
- Built with :heart: in `Kotlin`
- Follows `Model View Presenter` architecture
- `Retrofit` for networking
- `Glide` for image downloading and caching
- `Repository pattern` to access the data
- `EventBus` for event based messaging between the components
- `Dagger2` for DI
- `Room` Data Persistance library for data storage
- `Unit Tests` included
- `Instrumentation Tests` included

## API used
- [pokeapi.co](https://pokeapi.co) for getting the data about the Pokemons

## Recommendations for improvements
- List should be paginated. When scrolled down, it should fetch new items
- Making all data available locally by storing in Sqlite DB using Room so as to make the app work offline as well
- Implementing Sync operations using WorkManager to keep the data up-todate all time
- Testing using Firebase Test Lab to improve the quality of the app
- Writing more tests

## `main` source directory structure
Due to screen size constraints adding only the structure of hte `main` source directory here
<img src="https://raw.githubusercontent.com/jaydeepw/pokemon-directory/master/main-tree.png"
     alt="Main source tree" />