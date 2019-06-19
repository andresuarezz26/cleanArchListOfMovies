package com.movies.cleanarchlistofmovies.data.datasource

import com.movies.cleanarchlistofmovies.data.entity.RealmResultTVMovies
import com.movies.cleanarchlistofmovies.data.mapper.RealmMapperResultTVMovies
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

interface ResultTVMovieDataSourceFacade {
    fun createOrUpdate(list: List<ResultTVMovies>,
                       category: String,
                       typeOfShow: String)

    fun getById(id: Int): ResultTVMovies?
    fun getAllByCategory(typeOfShow: String, category: String): List<ResultTVMovies>
}

class RealmResultTVMovieDataSource @Inject constructor(
        private val mapper: RealmMapperResultTVMovies
) : ResultTVMovieDataSourceFacade {

    override fun createOrUpdate(
            list: List<ResultTVMovies>,
            category: String,
            typeOfShow: String
    ) {
        Realm.getDefaultInstance().executeTransaction { realm ->
            list.forEach {
                realm.copyToRealmOrUpdate(mapper.transform(it, category, typeOfShow))
            }
        }
    }

    override fun getById(id: Int): ResultTVMovies? =
            Realm.getDefaultInstance().use { realmInstance ->
                val output = realmInstance.where(RealmResultTVMovies::class.java)
                        .equalTo(RealmResultTVMovies.PRIMARY_KEY, id)
                        .findFirst()
                return output?.let { mapper.transform(output) }
            }

    override fun getAllByCategory(typeOfShow: String, category: String): List<ResultTVMovies> {
        Realm.getDefaultInstance().use { realmInstance ->
            val output: RealmResults<RealmResultTVMovies> = realmInstance
                    .where(RealmResultTVMovies::class.java)
                    .equalTo(RealmResultTVMovies.CATEGORY, category)
                    .equalTo(RealmResultTVMovies.TYPE_OF_SHOW, typeOfShow)
                    .findAll()
            return mapper.transform(output)
        }
    }
}