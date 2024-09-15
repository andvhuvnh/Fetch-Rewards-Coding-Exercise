package andvhuvnh.itemlist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import andvhuvnh.itemlist.ui.theme.ItemListTheme
import andvhuvnh.itemlist.viewmodel.ItemViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ItemViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe the data from the ViewModel
        viewModel.items.observe(this, Observer { itemList ->
            if(itemList!= null && itemList.isNotEmpty()){
                Log.d("MainActivity", "Received items: ${itemList.size}")
                // Update the adapter once data is available
                adapter = ItemAdapter(itemList)
                recyclerView.adapter = adapter
            } else{
                Log.d("MainActivity", "No activities found")
            }
        })

        // Fetch the items in the ViewModel
        viewModel.fetchItems()
    }
}