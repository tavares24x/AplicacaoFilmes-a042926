package pt.ismai.aplicacaofilmes

sealed class Destino(val route: String, val icon: Int, val title: String) {
    object Filmes : Destino(route = "filmes", icon = R.drawable.ic_movies, title = "Filmes")
    object Vistos : Destino(route = "vistos", icon = R.drawable.ic_watched, title = "Vistos")
    object Favoritos : Destino(route = "favoritos", icon = R.drawable.ic_favorite, title = "Favoritos")
    object PorVer : Destino(route = "porver", icon = R.drawable.ic_watchlist, title = "Por Ver")
    
    companion object {
        val toList = listOf(Filmes, Vistos, Favoritos, PorVer)
    }
}
