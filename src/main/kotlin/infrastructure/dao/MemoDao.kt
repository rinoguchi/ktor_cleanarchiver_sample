package infrastructure.dao

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

// テーブル定義
object MemoTable : IntIdTable("memo") {
    val body = text("body")
}

// エンティティ（1レコード）定義
class MemoEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MemoEntity>(MemoTable)
    var body by MemoTable.body
}
