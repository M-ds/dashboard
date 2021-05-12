package polar.bear.dashboard.person.impl

import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.usecase.SignInUseCase

class SignInUseCaseImpl(
    private val personRepository: PersonRepository
): SignInUseCase {

    override fun signIn(request: SignInUseCase.SignInRequest): SignInUseCase.SignInResponse {
        TODO("Not yet implemented")
    }
}