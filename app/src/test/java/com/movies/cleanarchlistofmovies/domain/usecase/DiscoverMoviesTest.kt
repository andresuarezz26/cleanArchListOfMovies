package com.movies.cleanarchlistofmovies.domain.usecase

import com.movies.cleanarchlistofmovies.domain.repositories.DiscoverMoviesTVRepoFacade
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
    private lateinit var repo: DiscoverMoviesTVRepoFacade
    @InjectMocks
    private lateinit var useCase: DiscoverTVShowAndMoviesByCategory

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when use case is invoked, invoke the repository to get the discover movies`() {
        whenever(repo(MOCK_CATEGORY)).thenReturn(Single.just(listOf()))
        useCase(DiscoverTVShowAndMoviesByCategory.Param(MOCK_CATEGORY))
        verify(repo).invoke(MOCK_CATEGORY)
    }

    companion object {
        const val MOCK_CATEGORY = "popular"
    }
}