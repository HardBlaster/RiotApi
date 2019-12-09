package hu.unideb.riotapi

data class Summoner(
    val id: String, val accountId: String,
    val puuid: String, val name: String,
    val profileIconId: Int, val revisionDate: Long,
    val summonerLevel: Int
)