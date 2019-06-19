package com.movies.cleanarchlistofmovies.data.repositories

import com.movies.cleanarchlistofmovies.RxImmediateSchedulerRule
import com.movies.cleanarchlistofmovies.data.datasource.ResultTVMovieDataSourceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesAndTVServiceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverMoviesService
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DiscoverMoviesRepoTest {
    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var discoverService: DiscoverMoviesService

    @Mock
    private lateinit var dataSource: ResultTVMovieDataSourceFacade

    @Mock
    private lateinit var resultMovies1: ResultTVMovies
    @Mock
    private lateinit var resultMovies2: ResultTVMovies

    @InjectMocks
    private lateinit var repo: DiscoverMoviesRepo

    @Test
    fun `When there are data on service, save in the database a combination of the two observables`() {
        val list = listOf(resultMovies1, resultMovies2)
        whenever(discoverService(DiscoverMoviesAndTVServiceFacade.TYPE_OF_SHOW_TV, MOCK_CATEGORY)).thenReturn(Single.just(list))
        whenever(discoverService(DiscoverMoviesAndTVServiceFacade.TYPE_OF_SHOW_MOVIE, MOCK_CATEGORY)).thenReturn(Single.just(list))

        repo(MOCK_CATEGORY).test().assertOf {
            assertEquals(2, it.values().getOrNull(0)?.size)
            assertEquals(2, it.values().getOrNull(1)?.size)
            verify(dataSource).createOrUpdate(any())
        }
    }

    companion object {
        const val MOCK_CATEGORY = "popularity"
    }
}