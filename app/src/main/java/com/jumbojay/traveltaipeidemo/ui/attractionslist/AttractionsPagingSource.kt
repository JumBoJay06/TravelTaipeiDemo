package com.jumbojay.traveltaipeidemo.ui.attractionslist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jumbojay.traveltaipeidemo.api.services.AttractionsService
import com.jumbojay.traveltaipeidemo.data.Attractions

class AttractionsPagingSource(private val attractionsService: AttractionsService, private val language: String) : PagingSource<Int, Attractions>() {
    override fun getRefreshKey(state: PagingState<Int, Attractions>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Attractions> {
        val page = params.key ?: 1
        return try {
            val attractions = attractionsService.getAttractions(language, page).data as List<Attractions>
            LoadResult.Page (attractions, null, if (attractions.isEmpty()) null else page + 1)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}