package infrastructure.framework

import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction

fun <T> transactionWrapper(statement: Transaction.() -> T): T {
    return transaction {
        try {
            statement()
        }
        catch (ex: Exception) {
            rollback()
            throw ex 
        }
    }
}
