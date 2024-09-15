package andvhuvnh.itemlist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import andvhuvnh.itemlist.data.api.ApiService
import andvhuvnh.itemlist.data.model.Item
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel(){
    private val _items = MutableLiveData<List<Item>>()
    val items : LiveData<List<Item>> get() = _items

    fun fetchItems(){
        viewModelScope.launch{
            try{
                val itemList = ApiService.instance.getItems() // Fetch data from your API service
                val filteredAndSortedItems = itemList
                    .filter { !it.name.isNullOrBlank() }  // Filter out blank or null names
                    .sortedWith(compareBy({ it.listId }, { it.name }))  // Sort by listId and name

                Log.d("ItemViewModel", "fetched items: ${filteredAndSortedItems.size}")
                _items.postValue(filteredAndSortedItems)  // Post the sorted and filtered data
            } catch (e: Exception) {
                Log.e("ItemViewModel", "Error fetching items: ${e.message}")
            }
        }
    }
}