package infrastructure.framework

import org.koin.dsl.module
import infrastructure.repositoryimpl.MemoRepositoryImpl
import interfaces.controller.MemoController
import interfaces.repository.MemoRepository
import org.koin.logger.SLF4JLogger
import usecase.MemoService
import javax.validation.Validation
import javax.validation.Validator

val koinModules = module {
    single { MemoController() }
    single { MemoService() }
    factory<MemoRepository> { MemoRepositoryImpl() }
    factory { SLF4JLogger() }
    factory<Validator> { Validation.buildDefaultValidatorFactory().validator }
}
