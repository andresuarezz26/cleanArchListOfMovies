package com.movies.cleanarchlistofmovies.data.mapper

import com.movies.cleanarchlistofmovies.data.ImageUtils
import com.movies.cleanarchlistofmovies.data.responses.ResultsMovieTVShowResponse
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ResultMovieTVShowResponseMapperTest {

    @Mock
    private lateinit var input: ResultsMovieTVShowResponse

    @Mock
    private lateinit var imageUtil: ImageUtils

    @InjectMocks
    private lateinit var mapper: ResultMovieTVShowResponseMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        whenever(imageUtil.getUrl(any())).thenReturn(MOCK_URL)
    }

    @Test
    fun `when input response is null return null output`() {
        val output = mapper.transform(null)
        assertNull(output)
    }

    @Test
    fun `when input response is not null return output`() {
        whenever(input.voteCount).thenReturn(2)
        whenever(input.id).thenReturn(1)
        whenever(input.video).thenReturn(true)
        whenever(input.voteAverage).thenReturn(1.0)
        whenever(input.title).thenReturn("title")
        whenever(input.posterPath).thenReturn("overview")
        whenever(input.overview).thenReturn("overview")

        val output = mapper.transform(input)

        assertNotNull(output)
        compare(input, output)
    }

    private fun compare(input: ResultsMovieTVShowResponse, output: ResultTVMovies?) {
        if (output == null) {
            fail()
            return
        }
        assertEquals(input.voteCount, output.voteCount)
        assertEquals(input.id, output.id)
        assertEquals(input.video, output.video)
        assertEquals(input.title, output.title)
        assertEquals(input.voteAverage, output.voteAverage, 0.1)
        assertEquals(MOCK_URL, output.posterPath)
        assertEquals(input.overview, output.overview)
    }

    @Test
    fun `when there is an input list with null objects, return an output list with non null objects`() {
        val inputList = listOf(null, null, null, null)
        val output = mapper.transform(inputList)
        assertNotNull(output)
        assertEquals(0, output.size)
    }

    companion object {
        private const val MOCK_URL = "URL"
    }
}