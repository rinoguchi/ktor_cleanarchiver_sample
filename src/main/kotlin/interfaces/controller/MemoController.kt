package interfaces.controller

import domain.Memo
import interfaces.repository.MemoRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.logger.SLF4JLogger
import javax.validation.constraints.Size

class MemoController : KoinComponent {

    private val memoRepository: MemoRepository by inject()
    private val logger: SLF4JLogger by inject()
    
    data class MemoInput(val id: Int)
    
    data class MemoPostInput(@field:Size(max = 20) val body: String)
    
    data class MemoPutInput(val id: Int, @field:Size(max = 20) val body: String)

    data class MemoOutput(val id: Int, val body: String, val keywords: List<String>)
    
    private fun memoToMemoOutput(memo: Memo): MemoOutput {
        return MemoOutput(memo.id, memo.body, memo.keywords)
    }
    
    
    fun get(input: MemoInput): MemoOutput {
        logger.info("MemoController.get called.")
        val memo: Memo = memoRepository.findById(input.id)
        return memoToMemoOutput(memo)
    }

    fun post(input: MemoPostInput): MemoOutput {
        logger.info("MemoController.post called.")
        val memo: Memo = memoRepository.create(input.body)
        return memoToMemoOutput(memo)
    }
    
    fun put(input: MemoPutInput): MemoOutput {
        logger.info("MemoController.put called.")
        val memo: Memo = memoRepository.update(input.id, input.body)
        return memoToMemoOutput(memo)
    }

    fun delete(input: MemoInput) {
        logger.info("MemoController.delete called.")
        memoRepository.delete(input.id)
    }
}

