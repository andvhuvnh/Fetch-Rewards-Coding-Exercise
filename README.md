## Fetch Rewards Coding Exercise
This Android app retrieves data from a remote API and displays it grouped by listId, filtering out items without a name.

### Features of this app includes:
- Fetching data from the provided API
- Groups items by `listId`
- Filters out items with null or blank names
- Displays data in a RecyclerView with headers for an easy-to-read format

### Technologies Used:
- Kotlin
- Retrofit (for networking)
- LiveData and ViewModel (for lifecycle management)
- RecyclerView (for displaying data)

### Installation Instructions
1. Clone the repository to your local machine
2. Open the project in Android Studio
3. Sync the Gradle Project
4. Run the app on an emulator or physical device.

### API Documentation
The API endpoint used is the one provided for the exercise `https://fetch-hiring.s3.amazonaws.com/hiring.json`,
in which we fetched the data using Retrofit and the data is then filtered, grouped, and displayed.

### Testing
This project includes unit tests to validate the functionality of the `ItemViewModel`, specifically focusing on how the `ViewModel` fetches, filters, and sorts data from the repository, ensuring the app's core logic behaves as expectedly. In doing so, we created a MockItemRepository manually to simulate the ItemRepository logic with fake data allowing us to test scenarios where names are null or blank and items are sorted on `listId` and then item names lexographically.

You can run both ItemViewModelTest and ItemRepositoryTest in AndroidStudios navigating to the app/kotlin+java/test folder.

