package usecase

import domain.Memo
import java.util.*


class MemoService {
    fun attachKeywords(memo: Memo): Memo {
        val keywords = StringTokenizer(memo.body).toList().map { v -> v.toString() }.filter { v -> v.length > 5 }
        return memo.copy(keywords = keywords)
    }
}