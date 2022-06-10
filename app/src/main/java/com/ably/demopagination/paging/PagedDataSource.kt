package com.ably.demopagination.paging

import com.ably.demopagination.network.ApiService
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ably.demopagination.model.ApiResponseItem

class PagedDataSource(private val api: ApiService) : PagingSource<Int, ApiResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiResponseItem> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = api.getPassengersData(nextPageNumber,10)
            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (response.isEmpty()) null  else nextPageNumber+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ApiResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}