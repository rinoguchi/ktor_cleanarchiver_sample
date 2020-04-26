package domain

data class Memo(val id: Int, val body: String, val keywords: List<String> = listOf())