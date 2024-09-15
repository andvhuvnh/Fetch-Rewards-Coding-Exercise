package andvhuvnh.itemlist

import kotlinx.coroutines.runBlocking
import andvhuvnh.itemlist.data.api.ApiService
import andvhuvnh.itemlist.data.model.Item
import andvhuvnh.itemlist.data.model.ItemRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ItemRepositoryTest {

    @Mock
    lateinit var apiService: ApiService

    private lateinit var repository: ItemRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = ItemRepository(apiService)
    }

    @Test
    fun `getItems should return expected data from ApiService`(): Unit = runBlocking {
        // Arrange: Mock the API response
        val mockItems = listOf(
            Item(1, 1, "Item 1"),
            Item(2, 1, "Item 2")
        )
        Mockito.`when`(apiService.getItems()).thenReturn(mockItems)

        // Act: Call the repository method
        val result = repository.getItems()

        // Assert: Verify the result
        assert(result == mockItems)
        Mockito.verify(apiService).getItems()
    }
}