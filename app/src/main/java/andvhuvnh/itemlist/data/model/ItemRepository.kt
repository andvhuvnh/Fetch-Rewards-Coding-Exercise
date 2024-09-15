package andvhuvnh.itemlist.data.model

import andvhuvnh.itemlist.data.api.ApiService

open class ItemRepository(private val apiService: ApiService) {

    // Fetch items from the API
    open suspend fun getItems(): List<Item> {
        return apiService.getItems()
    }
}
