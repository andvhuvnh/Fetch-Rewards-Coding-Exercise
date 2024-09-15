package andvhuvnh.itemlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import andvhuvnh.itemlist.data.model.Item
import andvhuvnh.itemlist.viewmodel.ItemViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ItemViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<List<Item>>

    private lateinit var viewModel: ItemViewModel

    // Test dispatcher for coroutines
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this)

        // Set Main dispatcher for testing
        Dispatchers.setMain(testDispatcher)

        // Initialize the ViewModel with the MockItemRepository
        viewModel = ItemViewModel(MockItemRepository())
    }

    @After
    fun tearDown() {
        // Reset Main dispatcher to the original one after test
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchItems should return filtered and sorted items`() = runTest {
        // Act: Observe the LiveData and call fetchItems
        viewModel.items.observeForever(observer)
        viewModel.fetchItems()

        // Advance time to ensure the coroutines execute
        testScheduler.advanceUntilIdle()

        // Assert: Verify that the LiveData emitted the correct filtered and sorted values
        val expected = listOf(
            Item(1, 1, "Item 1"),
            Item(5, 2, "Item 2"),
            Item(4, 2, "Item 4"),
            Item(6, 3, "Item 3")
        )

        verify(observer).onChanged(expected)
        verifyNoMoreInteractions(observer)
    }
}
