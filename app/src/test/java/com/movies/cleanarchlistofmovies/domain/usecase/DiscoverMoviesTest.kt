package com.movies.cleanarchlistofmovies.domain.usecase

import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesRepoFacade
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DiscoverMoviesTest {

    @Mock
    private lateinit var repo: DiscoverMoviesRepoFacade
    @InjectMocks
    private lateinit var useCase: DiscoverMovies

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when use case is invoked, invoke the repository to get the discover movies`() {
        whenever(repo()).thenReturn(Single.just(listOf()))
        useCase()
        verify(repo).invoke()
    }
}