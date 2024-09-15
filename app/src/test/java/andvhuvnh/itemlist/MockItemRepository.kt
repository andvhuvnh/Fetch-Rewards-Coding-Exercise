package andvhuvnh.itemlist

import andvhuvnh.itemlist.data.model.Item
import andvhuvnh.itemlist.data.api.ApiService
import andvhuvnh.itemlist.data.model.ItemRepository

// A fake implementation of ItemRepository for testing
class MockItemRepository : ItemRepository(ApiService.instance) {

    // Predefined data for testing purposes
    private val fakeItems = listOf(
        Item(1, 1, "Item 1"),  // Valid
        Item(2, 1, null),      // Invalid: name is null, should be filtered out
        Item(3, 1, ""),        // Invalid: name is blank, should be filtered out
        Item(4, 2, "Item 4"),  // Valid
        Item(5, 2, "Item 2"),  // Valid, out of order for sorting
        Item(6, 3, "Item 3"),  // Valid
    )

    override suspend fun getItems(): List<Item> {
        // Return the predefined data for testing
        return fakeItems
    }
}
