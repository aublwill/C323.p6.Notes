Collaboration with Kuzey Bektas

# C323.p6.Notes
This project functions as a notes app.

## Functionality 
The following **required** functionality is completed:
* [] user sees a screen with (maybe) notes on it
* [] user can either press on an existing note to edit/view it
* [] or user can press "Add Note" to create a new note
* [] user can then press on the new note to name and edit the contents
* [] user can press save to save the note and naviagte back to home screen
* [] user can press the red "X" on a note for a confirmation to pop up
* [] if user presses "no", nothing happens, if user presses "yes", the selected note will be deleted

The folowing **extensions** are implemented:

* import android.app.AlertDialog
* import android.app.Dialog
* import android.content.Context
* import android.os.Bundle
* import android.util.Log
* import androidx.fragment.app.DialogFragment
* import android.os.Bundle
* import android.view.LayoutInflater
* import android.view.View
* import android.view.ViewGroup
* import androidx.fragment.app.Fragment
* import androidx.lifecycle.Observer
* import androidx.lifecycle.ViewModelProvider
* import androidx.navigation.findNavController
* import com.example.c323p6notes.databinding.FragmentEditNoteBinding
* import androidx.lifecycle.LiveData
* import androidx.lifecycle.MutableLiveData
* import androidx.lifecycle.ViewModel
* import androidx.lifecycle.viewModelScope
* import kotlinx.coroutines.launch
* import java.lang.IllegalArgumentException
* import androidx.appcompat.app.AppCompatActivity
* import androidx.room.ColumnInfo
* import androidx.room.Entity
* import androidx.room.PrimaryKey
* import androidx.room.Dao
* import androidx.room.Delete
* import androidx.room.Insert
* import androidx.room.Query
* import androidx.room.Update
* import androidx.room.Database
* import androidx.room.Room
* import androidx.room.RoomDatabase
* import androidx.recyclerview.widget.DiffUtil
* import androidx.recyclerview.widget.ListAdapter
* import androidx.recyclerview.widget.RecyclerView
* import com.example.c323p6notes.databinding.NoteItemBinding
  
## Video Walkthrough 



https://github.com/aublwill/C323.p4.Translator/assets/143005409/a19ba106-04c1-4917-be9d-9aab867180d1



## Notes
* Sometimes crashes

## License
Copyright [2023] [Aubrey Williams]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
